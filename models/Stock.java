package models;

public class Stock {
    private int product_id;
    private int units;
    private int inventory_id;
    public static String dbPath = "./data/stock.csv";

    public String toString() {
        return this.product_id + "," + this.units + "," + this.inventory_id + "\n";
    }

    public Stock(int product_id, int units, int inventory_id) {
        this.product_id = product_id;
        this.units = units;
        this.inventory_id = inventory_id;
        Model.storedata(this + "", Stock.dbPath);
    }

}
