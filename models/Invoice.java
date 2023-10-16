package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Invoice {
    private int id;
    private int customer_id;
    private String date;
    public static String dbPath = "./data/invoice.csv";
    public static int counter = Model.count(dbPath);

    private Invoice(int id, int customer_id, String date) {
        this.id = id;
        this.customer_id = customer_id;
        this.date = date;
    }

    public String toString() {
        return this.id + "," + this.customer_id + "," + this.date + "\n";
    }

    public Invoice(int customer_id, String date) {
        this.id = ++counter;
        this.customer_id = customer_id;
        this.date = date;
        Model.storedata(this + "", Invoice.dbPath);
    }

    public static Invoice findById(int _id) {
        try {
            FileReader fileReader = new FileReader(Company.dbpath);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] contents = line.split(",");
                if (Integer.parseInt(contents[0]) == _id) {
                    bufferedReader.close();
                    fileReader.close();
                    return new Invoice(Integer.parseInt(contents[0]), Integer.parseInt(contents[1]),
                            (contents[2]));
                }
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
