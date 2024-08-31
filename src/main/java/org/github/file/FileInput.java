package org.github.file;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileInput {

    private static FileInput instance;

    private FileInput() {}

    public static FileInput getInstance() {
        if (instance == null) {
            instance = new FileInput();
        }
        return instance;
    }

    public List<String[]> loadCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (IOException e) {
            throw new CSVFileReadException("Error reading the CSV file: " + filePath, e);
        }
        if (data.isEmpty()) {
            throw new CSVFileReadException("CSV file is empty or invalid format: " + filePath, null);
        }
        return data;
    }
}