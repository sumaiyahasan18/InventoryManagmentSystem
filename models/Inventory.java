package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Inventory {

    private int id;
    private String location;
    public static String dbPath = "/data/inventory.csv";
    public static int counter = Model.count(Inventory.dbPath);

    public String toString() {
        return id + "," + location + "\n";
    }

    private Inventory(int id, String location) {
        this.id = id;
        this.location = location;
    }

    public Inventory(String location) {
        this.location = location;
        this.id = ++Inventory.counter;
        Model.storedata(this.toString(), Inventory.dbPath);
    }

    public void printInfo() {
        System.out.println("Id: " + this.id);
        System.out.println("Location: " + this.location);
    }

    public static Inventory findById(int _id) {
        try {
            FileReader fileReader = new FileReader(Model.base + Company.dbPath);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] contents = line.split(",");
                if (Integer.parseInt(contents[0]) == _id) {
                    bufferedReader.close();
                    fileReader.close();
                    return new Inventory(Integer.parseInt(contents[0]), contents[1]);
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
