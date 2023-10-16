package models;

public class Purchase_log {
    private int products_id;
    private int Product_units;
    private int total_price;
    private long date;
    public static String dbPath = "./data/purchase_log.csv";

    private Purchase_log(int products_id, int Product_units, int total_price, long date) {
        this.products_id = products_id;
        this.Product_units = Product_units;
        this.total_price = total_price;
        this.date = date;
    }

    public String toString() {
        return products_id + "," + Product_units + "," + total_price + "," + date + "\n";
    }

    public static Purchase_log createAndStore(int products_id, int Product_units, int total_price, long date) {

        Purchase_log purchase_log = new Purchase_log(products_id, Product_units, total_price, date);
        Model.storedata(purchase_log.toString(), dbPath);
        return purchase_log;
    }
}
