package models;

import java.util.ArrayList;

public class Purchase_log {
    private int products_id;
    private int units;
    private int total_price;
    private long date;
    private int invoiceId;
    public static String dbPath = "./data/purchase_log.csv";

    private Purchase_log(int products_id, int _invoiceId, int units, int total_price, long date) {
        this.products_id = products_id;
        this.units = units;
        this.total_price = total_price;
        this.date = date;
        this.invoiceId = _invoiceId;
    }

    public String toString() {
        return products_id + "," + this.invoiceId + "," + units + "," + total_price + "," + date + "\n";
    }

    public static Purchase_log createAndStore(int products_id, int _invoiceId, int units, int total_price, long date) {

        Purchase_log purchase_log = new Purchase_log(products_id, _invoiceId, units, total_price, date);
        Model.storedata(purchase_log.toString(), dbPath);
        return purchase_log;
    }

    public static ArrayList<String> getPurchasedList(int invoiceId) {
        ArrayList<String> lines = Model.getLines(dbPath);

        ArrayList<String> taken = new ArrayList<String>();

        for (String item : lines) {
            String[] line = item.split(",");
            if (Integer.parseInt(line[1]) == invoiceId) {
                taken.add(item);
            }
        }
        return taken;
    }
}
