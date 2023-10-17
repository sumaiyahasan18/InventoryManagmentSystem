package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerInfo {
    public String customer_name;
    public int id;
    public static String dbPath = "/data/customer.csv";
    public static int counter = Model.count(dbPath);

    public String toString() {
        return this.id + "," + this.customer_name + "\n";
    }

    private CustomerInfo(int _id, String customer_name) {
        this.id = _id;
        this.customer_name = customer_name;
    }

    public CustomerInfo(String customer_name) {
        this.customer_name = customer_name;
        this.id = ++CustomerInfo.counter;
        Model.storedata(this + "", CustomerInfo.dbPath);
    }

    public static CustomerInfo findById(int _id) {
        try {
            FileReader fileReader = new FileReader(Model.base + Company.dbPath);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] contents = line.split(",");
                if (Integer.parseInt(contents[0]) == _id) {
                    bufferedReader.close();
                    fileReader.close();
                    return new CustomerInfo(Integer.parseInt(contents[0]), contents[1]);
                }
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void viewAll() {
        ArrayList<String> users = Model.getLines(dbPath);
        System.out.println("id\tname");
        for (String string : users) {
            String[] line = string.split(",");
            System.out.println(line[0] + "\t" + line[1]);
        }
    }

    public static void createFromInput(Scanner scanner) {
        System.out.println("Creating customer");
        System.out.println("Customer Name");
        String name = scanner.nextLine();
        new CustomerInfo(name);

    }

}
