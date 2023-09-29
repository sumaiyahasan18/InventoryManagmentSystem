package models;

public class Product {
    private String name;
    private int id;
    private int company_id;
    private int retail_price;
    private int wholesale_Price;
    private int wholesale_units;

    public Product(String name, int id, int company_id, int retail_price, int wholesale_Price, int wholesalr_units) {
        this.name = name;
        this.id = id;
        this.company_id = company_id;
        this.retail_price = retail_price;
        this.wholesale_Price = wholesale_Price;
        this.wholesale_units = wholesalr_units;

    }
}