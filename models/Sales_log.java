package models;

import java.util.ArrayList;
import java.util.Scanner;

public class Sales_log {
    int productId, amount;
    String date;
    int invoiceId;
    public static String dbPath = "/data/sales_log.csv";

    private Sales_log(int _productId, int _invoiceId, int _amount, String _date) {
        this.productId = _productId;
        this.amount = _amount;
        this.date = _date;
        this.invoiceId = _invoiceId;
    }

    public String toString() {
        return productId + "," + invoiceId + "," + amount + "," + date + "\n";
    }

    public static ArrayList<String> getProductSales(int productId) {
        ArrayList<String> lines = Model.getLines(dbPath);
        ArrayList<String> sales = new ArrayList<String>();
        for (String item : lines) {
            String[] line = item.split(",");
            int _productId = Integer.parseInt(line[0]);
            if (_productId == productId) {
                sales.add(item);
            }
        }

        return sales;
    }

    public static Sales_log createAndStore(int _productId, int invoiceId, int _amount, String _date) {
        Sales_log sales_log = new Sales_log(_productId, invoiceId, _amount, _date);
        Model.storedata(sales_log.toString(), Sales_log.dbPath);
        return sales_log;
    }

    public static void getProductOfInvoice(Scanner scanner) {
        System.out.println("Invoice id:");
        int invoiceId = scanner.nextInt();
        System.out.println("Purchases under invoice id " + invoiceId);
        System.out.println("item\tamount\tdate");
        ArrayList<String> lines = Model.getLines(dbPath);
        for (String item : lines) {
            String[] line = item.split(",");
            if (Integer.parseInt(line[1]) == invoiceId) {
                Product p = Product.findById(Integer.parseInt(line[0]));
                System.out.println(p.name + "\t" + line[2] + "\t" + line[3]);
            }
        }
    }
}
