package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Company {
    public static String dbpath = "./data/company.csv";
    private String name;
    private int id;
    private String location;
    public static int count = 0;
    public static ArrayList<Company> companies = new ArrayList<Company>();

    public Company(String name, String location) {
        this.name = name;
        this.id = Company.count + 1;
        this.location = location;
        Company.companies.add(this);
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
        Model.storedata(this + "", Company.dbpath);

    }

    public String toString() {
        return this.id + "," + this.name + "," + this.location + "\n";
    }

    public static Company searchById(int _id) {
        try {
            FileReader fileReader = new FileReader(Company.dbpath);

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
}
