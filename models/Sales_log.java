package models;

public class Sales_log {
    int productId, amount;
    String date;
    public static String dbPath = "./data/sales_log.csv";

    private Sales_log(int _productId, int _amount, String _date) {
        this.productId = _productId;
        this.amount = _amount;
        this.date = _date;

    }

    public String toString() {
        return productId + "," + amount + "," + date + "\n";
    }

    public static Sales_log createAndStore(int _productId, int _amount, String _date) {
        Sales_log sales_log = new Sales_log(_productId, _amount, _date);
        Model.storedata(sales_log.toString(), Sales_log.dbPath);
        return sales_log;
    }
}
