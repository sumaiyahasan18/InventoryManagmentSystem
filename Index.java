import java.util.Scanner;

import models.Company;
import models.Product;

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

        int flag = scanner.nextInt();
        scanner.nextLine();
        switch (flag) {
            case 1:
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
                break;
            case 2:
                System.out.println("Creating company");
                System.out.println("Company name");
                name = scanner.nextLine();
                System.out.println("Company location");
                String location = scanner.nextLine();
                Company.create(name, location).printDetails();
                break;
            default:
                break;
        }

        scanner.close();

    }
}
