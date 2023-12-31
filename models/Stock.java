package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Stock {
    private int product_id;
    private int units;
    private int inventory_id;
    public static String dbPath = "/data/stock.csv";

    public String toString() {
        return this.product_id + "," + this.inventory_id + "," + this.units;
    }

    private Stock(int product_id, int units, int inventory_id) {
        this.product_id = product_id;
        this.units = units;
        this.inventory_id = inventory_id;

    }

    public static void addToInventory(Scanner scanner) {
        System.out.println("Adding product:");
        System.out.println("Product Id: ");
        int productId = scanner.nextInt();
        System.out.println("Units: ");

        int units = scanner.nextInt();
        System.out.println("Inventory Id: ");
        Product p = Product.findById(productId);

        int companyId = p.company_id;

        LocalDate currentDate = LocalDate.now();

        Locale locale = Locale.US;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);

        String localizedDate = currentDate.format(formatter);

        Purchase_log.createAndStore(productId, companyId, units, p.wholesale_Price * units, localizedDate);
        int inventoryId = scanner.nextInt();
        Stock stock = Stock.find(productId, inventoryId);
        if (stock != null) {
            stock.update(units);
        } else {
            stock = new Stock(productId, units, inventoryId);
            Model.storedata(stock.toString() + "\n", dbPath);
        }
    }

    public static Stock find(int productId, int inventoryId) {
        try {
            FileReader fileReader = new FileReader(Model.base + Stock.dbPath);

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

    public static int getProductStock(int productId) {
        ArrayList<String> lines = Model.getLines(Stock.dbPath);
        int totalUnits = 0;
        for (int n = 0; n < lines.size(); n++) {
            String[] contents = lines.get(n).split(",");
            if (Integer.parseInt(contents[0]) == productId) {
                totalUnits += Integer.parseInt(contents[2]);

            }
        }
        return totalUnits;
    }

    public static void updateStockOnPurchase(int productId, int amount) {
        ArrayList<String> lines = Model.getLines(Stock.dbPath);
        for (int i = 0; i < lines.size(); i++) {
            String[] line = lines.get(i).split(",");

            int _productId = Integer.parseInt(line[0]);
            int _amount = Integer.parseInt(line[2]);
            if (productId == _productId) {
                if (amount >= _amount) {
                    _amount = 0;
                    amount -= _amount;
                    line[2] = "0";
                    lines.set(i, String.join(",", line));
                } else {
                    _amount -= amount;
                    line[2] = "" + _amount;
                    lines.set(i, String.join(",", line));
                    break;
                }
            }
        }
        Model.writeData(dbPath, lines);

    }

    public static Boolean countAvailability(int productId, int amount) {
        return Stock.getProductStock(productId) >= amount;
    }

    public static Stock createAndStore(int product_id, int units, int inventory_id) {
        Stock st = new Stock(product_id, units, inventory_id);
        Model.storedata(st + "\n", Stock.dbPath);
        return st;
    }
}
