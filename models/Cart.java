package models;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Cart {
    public ArrayList<Integer> products;
    public ArrayList<Integer> amounts;

    public Cart() {
        this.products = new ArrayList<Integer>();
        this.amounts = new ArrayList<Integer>();

    }

    public void printItems() {
        System.out.println("item\tid\tamount");
        for (int i = 0; i < products.size(); i++) {
            Product p = Product.findById(products.get(i));
            System.out.println(p.name + "\t" + products.get(i) + "\t" + amounts.get(i));
        }
    }

    public void addItem(int productId, int amount) {
        this.products.add(productId);
        this.amounts.add(amount);

    }

    public static void browseAndAddToCart(Scanner scanner) {
        Product.showProducts();
        Cart cart = new Cart();
        System.out.println("Customer Id");
        int customerId = scanner.nextInt();
        while (true) {
            System.out.println("1: add items");
            System.out.println("2: Confirm purchase");
            System.out.println("3: view cart");
            int flag = scanner.nextInt();
            if (flag == 1) {
                while (true) {
                    System.out.println("Product ID: (-1 to stop)");
                    int productId = scanner.nextInt();
                    if (productId == -1)
                        break;
                    System.out.println("Product amount:");
                    int amount = scanner.nextInt();
                    if (!Stock.countAvailability(productId, amount)) {
                        System.out.println("Not enough items in stock");
                    } else
                        cart.addItem(productId, amount);
                }

            }
            if (flag == 2) {
                cart.confirm(customerId);
                return;
            }
            if (flag == 3) {
                while (true) {
                    System.out.println("Added items");
                    cart.printItems();
                    System.out.println("1: exit");
                    System.out.println("2 x: remove item x");
                    int nextFlag = scanner.nextInt();
                    if (nextFlag == 1)
                        break;
                    else {
                        cart.remove(scanner.nextInt());
                    }
                }
            }
        }
    }

    public void confirm(int customerId) {
        LocalDate currentDate = LocalDate.now();

        Locale locale = Locale.US; // Replace with the desired locale

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);

        String localizedDate = currentDate.format(formatter);
        Invoice invoice = new Invoice(customerId, localizedDate);
        for (int i = 0; i < amounts.size(); i++) {
            Product p = Product.findById(products.get(i));
            p.purchase(customerId, amounts.get(i), invoice.id, localizedDate);

        }
    }

    public void remove(int productId) {
        for (int i = 0; i < amounts.size(); i++) {
            if (products.get(i) == productId) {
                amounts.remove(i);
                products.remove(i);
                return;
            }

        }
    }
}
