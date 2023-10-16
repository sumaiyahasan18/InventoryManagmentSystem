package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Company {
    public static String dbPath = "/data/company.csv";
    public String name;
    public int id;
    public String location;
    public static int count = Model.count(dbPath);

    public Company(String name, String location) {
        this.name = name;
        this.id = ++Company.count;
        this.location = location;
    }

    public void printDetails() {
        System.out.println("Id: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Location: " + this.location);

    }

    private Company(String line) {
        String[] contents = line.split(",");
        this.id = Integer.parseInt(contents[0]);
        this.name = contents[1];
        this.location = contents[2];
    }

    public static void showProducts() {
        ArrayList<String> contents = Model.getLines(Company.dbPath);
        for (String line : contents) {
            Company p = new Company(line);
            p.printDetails();
        }
    }

    private Company(int id, String name, String location) {
        this.name = name;
        this.id = id;
        this.location = location;
    }

    public static Company create(String name, String location) {
        Company company = new Company(name, location);
        company.store();
        return company;
    }

    private void store() {
        Model.storedata(this + "", Company.dbPath);

    }

    public String toString() {
        return this.id + "," + this.name + "," + this.location + "\n";
    }

    public static Company searchById(int _id) {
        try {
            FileReader fileReader = new FileReader(Model.base + Company.dbPath);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] contents = line.split(",");
                if (Integer.parseInt(contents[0]) == _id) {
                    bufferedReader.close();
                    fileReader.close();
                    return new Company(Integer.parseInt(contents[0]), contents[1], contents[2]);
                }
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createFromInput(Scanner scanner) {
        System.out.println("Creating company");
        System.out.println("Company name");
        String name = scanner.nextLine();
        System.out.println("Company location");
        String location = scanner.nextLine();
        Company.create(name, location).printDetails();
    }
}
