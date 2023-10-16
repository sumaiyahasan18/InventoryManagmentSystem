package models;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Cart {
    public ArrayList<Integer> products;
    public ArrayList<Integer> amounts;

    public Cart() {
        this.products = new ArrayList<Integer>();
        this.amounts = new ArrayList<Integer>();

    }

    public void addItem(Integer productId, int amount) {
        this.products.add(productId);
        this.amounts.add(amount);

    }

    public void confirm(int customerId) {
        LocalDate currentDate = LocalDate.now();

        Locale locale = Locale.US; // Replace with the desired locale

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);

        String localizedDate = currentDate.format(formatter);
        Invoice invoice = new Invoice(customerId, localizedDate);
        for (int i = 0; i < amounts.size(); i++) {
            Product p = Product.findById(products.get(i));
            p.purchase(customerId, amounts.get(i));

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
