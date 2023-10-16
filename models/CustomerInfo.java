package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CustomerInfo {
    private String customer_name;
    private int id;
    public static String dbPath = "/data/customer.csv";
    public static int counter = Model.count(null);

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
            FileReader fileReader = new FileReader(Company.dbPath);

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
}
