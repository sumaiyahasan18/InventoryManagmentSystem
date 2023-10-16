import java.util.ArrayList;
import java.util.Scanner;

import models.Cart;
import models.Company;
import models.CustomerInfo;
import models.Inventory;
import models.Product;
import models.Purchase_log;
import models.Sales_log;
import models.Stock;

public class Index {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1: Insert product");
        System.out.println("2: Insert Company");
        System.out.println("3: View all products");
        System.out.println("4 x: View product with id x");
        System.out.println("5 x: View Product sales of id x");
        System.out.println("6 x: View Product purchases of id x");
        System.out.println("7: Add product to inventory");
        System.out.println("8: Create inventory");
        System.out.println("9: Add products to cart");
        System.out.println("10: Create user");
        int flag = scanner.nextInt();
        scanner.nextLine();
        switch (flag) {
            case 1:
                Product.createProduct(scanner);
                break;
            case 2:
                Company.createFromInput(scanner);
                break;
            case 3:
                Product.showProducts();
                break;
            case 4:
                Product.viewDetails(scanner.nextInt());
                break;
            case 5:
                ArrayList<String> logs = Sales_log.getProductSales(scanner.nextInt());
                System.out.println("date\tamount");
                for (String line : logs) {
                    String[] data = line.split(",");
                    System.out.println(data[1] + "\t" + data[2]);
                }
                break;
            case 6:
                ArrayList<String> productPurchases = Purchase_log.getProductPurchases(scanner.nextInt());
                System.out.println("Company\tamount\tcost\ttime");
                for (String line : productPurchases) {
                    String[] data = line.split(",");
                    System.out.println(data[0] + "\t" + data[3] + "\t" + data[4] + "\t" + data[5]);
                }
                break;
            case 7:
                Stock.addToInventory(scanner);
                break;
            case 8:
                System.out.println("Creating Invent");
                System.out.println("Inventory location");
                String inventoryLocation = scanner.nextLine();
                Inventory i = new Inventory(inventoryLocation);
                i.printInfo();
                break;
            case 9:
                Cart.browseAndAddToCart(scanner);
                break;
            case 10:
                CustomerInfo.createFromInput(scanner);
                break;
            default:
                break;
        }

        scanner.close();

    }
}
