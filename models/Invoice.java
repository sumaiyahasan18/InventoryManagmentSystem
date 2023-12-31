package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Invoice {
    public int id;
    public int customer_id;
    public String date;
    public static String dbPath = "/data/invoice.csv";
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

    public static void getInvoices(Scanner scanner) {
        System.out.println("User id:");
        int userId = scanner.nextInt();
        System.out.println("Purchases made by " + CustomerInfo.findById(userId).customer_name);
        System.out.println("id\tdate");
        ArrayList<String> lines = Model.getLines(dbPath);
        for (String item : lines) {
            String[] line = item.split(",");
            if (Integer.parseInt(line[1]) == userId) {
                System.out.println(Integer.parseInt(line[0]) + "\t" + line[2]);
            }
        }
    }

    public static Invoice findById(int _id) {
        try {
            FileReader fileReader = new FileReader(Model.base + Company.dbPath);

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
