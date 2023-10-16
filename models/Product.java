package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Product {
    private String name;
    int id;
    private int company_id;
    private int retail_price;
    private int wholesale_Price;
    private int wholesale_units;
    public static String dbPath = "./data/product.csv";
    public static int counter = Model.count(dbPath);

    private Product(int id, String name, int company_id, int retail_price, int wholesale_Price, int wholesale_units) {
        this.name = name;
        this.id = id;
        this.company_id = company_id;
        this.retail_price = retail_price;
        this.wholesale_Price = wholesale_Price;
        this.wholesale_units = wholesale_units;

    }

    public String toString() {
        return this.id + "," + this.name + "," + this.company_id + "," + this.retail_price + "," + this.wholesale_Price
                + "," + this.wholesale_units + "\n";
    }

    public Product(String name, int company_id, int retail_price, int wholesale_Price, int wholesale_units) {
        this.name = name;
        this.id = ++counter;
        this.company_id = company_id;
        this.retail_price = retail_price;
        this.wholesale_Price = wholesale_Price;
        this.wholesale_units = wholesale_units;
        Model.storedata(this + "", Product.dbPath);
    }

    public Boolean checkAvailability(int amount) {
        return Stock.countAvailability(this.id, amount);
    }

    public void purchase(int customerId, int amount) {

    }

    public static Product findById(int _id) {
        try {
            FileReader fileReader = new FileReader(Company.dbpath);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] contents = line.split(",");
                if (Integer.parseInt(contents[0]) == _id) {
                    bufferedReader.close();
                    fileReader.close();
                    return new Product(_id, contents[1],
                            Integer.parseInt(contents[2]),
                            Integer.parseInt(contents[3]),
                            Integer.parseInt(contents[4]),
                            Integer.parseInt(contents[5]));
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