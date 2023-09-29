package models;

public class Invoice {
    private int id;
    private int customer_id;
    private int date;

    public Invoice(int id, int customer_id, int date) {
        this.id = id;
        this.customer_id = customer_id;
        this.date = date;
    }

}
