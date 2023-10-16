package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Product {
    public String name;
    int id;
    public int company_id;
    public int retail_price;
    public int wholesale_Price;
    public int wholesale_units;
    public static String dbPath = "/data/product.csv";
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

    private Product(String line) {
        String[] contents = line.split(",");
        this.id = Integer.parseInt(contents[0]);
        this.name = contents[1];
        this.company_id = Integer.parseInt(contents[2]);
        this.retail_price = Integer.parseInt(contents[3]);
        this.wholesale_Price = Integer.parseInt(contents[4]);
        this.wholesale_units = Integer.parseInt(contents[5]);
    }

    public Boolean checkAvailability(int amount) {
        return Stock.countAvailability(this.id, amount);
    }

    public static void viewDetails(int _id) {
        Product p = Product.findById(_id);
        p.printInfo();
    }

    public void printInfo() {
        System.out.println("Id: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Retail price: " + this.retail_price);
        System.out.println("Retail price: " + this.wholesale_Price);
        System.out.println("Retail price: " + this.wholesale_units);
        System.out.println("Produced by: " + Company.searchById(this.company_id));
    }

    public static void showProducts() {
        ArrayList<String> contents = Model.getLines(dbPath);
        for (String line : contents) {
            Product p = new Product(line);
            p.printInfo();
        }
    }

    public void purchase(int customerId, int amount, int invoiceId, String date) {
        Stock.updateStockOnPurchase(this.id, amount);
        Sales_log.createAndStore(this.id, amount, date);
    }

    public static Product findById(int _id) {
        try {
            FileReader fileReader = new FileReader(Model.base + Product.dbPath);

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

    public static void createProduct(Scanner scanner) {
        System.out.println("Product name: ");
        String name = scanner.nextLine();
        System.out.println("Company Id: ");

        int companyId = scanner.nextInt();

        System.out.println("Retail price: ");

        int retail_price = scanner.nextInt();
        System.out.println("Wholesale price: ");

        int wholesale_Price = scanner.nextInt();
        System.out.println("Wholesale units: ");

        int wholesale_units = scanner.nextInt();
        Product p = new Product(name, companyId, retail_price, wholesale_Price, wholesale_units);
        p.printInfo();
    }
}