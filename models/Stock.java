package models;

public class Stock {
    private int products_id;
    private int units;
    private int inventory_id;

    public Stock(int products_id, int units, int inventory_id) {
        this.products_id = products_id;
        this.units = units;
        this.inventory_id = inventory_id;
    }

}
