package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Stock {
    private int product_id;
    private int units;
    private int inventory_id;
    public static String dbPath = "./data/stock.csv";

    public String toString() {
        return this.product_id + "," + this.inventory_id + "," + this.units + "\n";
    }

    private Stock(int product_id, int units, int inventory_id) {
        this.product_id = product_id;
        this.units = units;
        this.inventory_id = inventory_id;

    }

    public static Stock find(int productId, int inventoryId) {
        try {
            FileReader fileReader = new FileReader(Company.dbpath);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] contents = line.split(",");
                if (Integer.parseInt(contents[0]) == productId && Integer.parseInt(contents[1]) == inventoryId) {
                    bufferedReader.close();
                    fileReader.close();
                    return new Stock(productId, Integer.parseInt(contents[2]), inventoryId);
                }
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(int change) {
        this.units += change;
        ArrayList<String> lines = Model.getLines(Stock.dbPath);
        for (int n = 0; n < lines.size(); n++) {
            String[] contents = lines.get(n).split(",");
            if (Integer.parseInt(contents[0]) == this.product_id
                    && Integer.parseInt(contents[1]) == this.inventory_id) {
                lines.set(n, this.toString());
                break;
            }
        }
        Model.writeData(Stock.dbPath, lines);

    }

    public static Stock createAndStore(int product_id, int units, int inventory_id) {
        Stock st = new Stock(product_id, units, inventory_id);
        Model.storedata(st + "", Stock.dbPath);
        return st;
    }
}
