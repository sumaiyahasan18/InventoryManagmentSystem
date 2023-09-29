package models;

public class Purchase_log {
    private int products_id;
    private int Product_units;
    private int total_price;
    private int date;

    public Purchase_log(int products_id, int Product_units, int total_price, int date) {
        this.products_id = products_id;
        this.Product_units = Product_units;
        this.total_price = total_price;
        this.date = date;
    }

}
