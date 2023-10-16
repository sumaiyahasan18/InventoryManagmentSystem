package models;

import java.util.ArrayList;

public class Purchase_log {
    public int productId;
    public int units;
    public int total_price;
    public String date;
    public static String dbPath = "/data/purchase_log.csv";
    public int companyId;

    private Purchase_log(int productId, int companyId, int units, int total_price, String date) {
        this.productId = productId;
        this.units = units;
        this.total_price = total_price;
        this.date = date;
        this.companyId = companyId;
    }

    public String toString() {
        return productId + "," + this.companyId + "," + units + "," + total_price + "," + date + "\n";
    }

    public static Purchase_log createAndStore(int productId, int companyId, int units, int total_price, String date) {

        Purchase_log purchase_log = new Purchase_log(productId, companyId, units, total_price, date);
        Model.storedata(purchase_log.toString(), dbPath);
        return purchase_log;
    }

    public static ArrayList<String> getProductPurchases(int productId) {
        ArrayList<String> lines = Model.getLines(dbPath);
        ArrayList<String> taken = new ArrayList<String>();
        for (String item : lines) {
            String[] line = item.split(",");
            if (Integer.parseInt(line[0]) == productId) {
                Company c = Company.searchById(Integer.parseInt(line[1]));
                taken.add(c.name + "," + item);
            }
        }
        return taken;
    }

    public static ArrayList<String> getCompanywisePurchases(int companyId) {
        ArrayList<String> lines = Model.getLines(dbPath);
        ArrayList<String> taken = new ArrayList<String>();
        for (String item : lines) {
            String[] line = item.split(",");
            if (Integer.parseInt(line[1]) == companyId) {
                taken.add(item);
            }
        }
        return taken;
    }
}
