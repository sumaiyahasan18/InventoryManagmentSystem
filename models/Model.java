package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Model {
    public String getAttributeValues() {
        Class<?> clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        List<String> attributeValues = new ArrayList<>();
        for (Field field : fields) {
            Object fieldValue = null;

            try {
                fieldValue = field.get(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            attributeValues.add(fieldValue.toString());

        }
        StringBuilder sb = new StringBuilder();
        for (String item : attributeValues) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(item);
        }

        String commaSeparatedString = sb.toString();

        System.out.println(commaSeparatedString);
        return commaSeparatedString;
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
            e.printStackTrace();
        }
        return lineCount;
    }

    public void store(String dbPath) {
        String values = this.getAttributeValues();

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dbPath, true));

            // Write the text to the file
            bufferedWriter.write(values);
            bufferedWriter.newLine(); // Add a new line if needed

            // Close the buffered writer
            bufferedWriter.close();

            System.out.println("Text appended to the file.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
