package models;

import java.util.ArrayList;

public class Company {

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

    public static Company create(String name, String location) {
        Company company = new Company(name, location);
        return company;
    }

    public String toString() {
        return this.name + " " + this.location + " " + this.id;
    }

    public static Company searchById(int _id) {
        for (int i = 0; i < Company.companies.size(); i++) {
            if (Company.companies.get(i).id == _id) {
                return Company.companies.get(i);
            }
        }
        return null;
    }
}
