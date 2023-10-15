package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Model {
    public static void storedata(String content, String dbPath) {
        try {
            FileWriter fileWriter = new FileWriter(Company.dbpath, true);
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
            BufferedReader reader = new BufferedReader(new FileReader(dbPath));
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
}
