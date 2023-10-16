package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Inventory {

    private int id;
    private String location;
    public static String dbPath = "./data/inventory.csv";
    public static int counter = Model.count(Inventory.dbPath);

    private Inventory(int id, String location) {
        this.id = id;
        this.location = location;
    }

    public Inventory(String location) {
        this.location = location;
        this.id = ++Inventory.counter;
        Model.storedata(this + "", Inventory.dbPath);
    }

    public static Inventory findById(int _id) {
        try {
            FileReader fileReader = new FileReader(Company.dbpath);

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
