package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Model {
    public static String base = "/home/shahriarkabir/Desktop/InventoryManagmentSystem/models";

    public static void storedata(String content, String dbPath) {
        try {
            FileWriter fileWriter = new FileWriter(
                    Model.base + dbPath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(content);

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int count(String dbPath) {
        int lineCount = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(Model.base + dbPath));
            String line;

            while ((line = reader.readLine()) != null) {
                lineCount++;
            }

            reader.close();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        return lineCount;

    }

    public static ArrayList<String> getLines(String dbPath) {
        ArrayList<String> lines = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Model.base + dbPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void writeData(String dbPath, ArrayList<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Model.base + dbPath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
