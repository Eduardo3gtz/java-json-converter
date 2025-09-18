package com.troyecto.converter;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for writing data to a CSV file.
 * It uses the OpenCSV library to handle the CSV formatting.
 */
public class CsvWriter {

    /**
     * Writes data extracted from a JSON object into a CSV file.
     *
     * @param filePath The path where the CSV file will be saved.
     * @param data The Map object containing the data (originally from JSON).
     * @throws IOException If an error occurs during the file writing process.
     */
    @SuppressWarnings("unchecked")
    public void writeCsvFile(String filePath, Map<?, ?> data) throws IOException {
        // This assumes the JSON has a key "publications" which is a list of objects.
        // We need to cast it to the correct type to work with it.
        List<Map<String, Object>> publications = (List<Map<String, Object>>) data.get("publications");

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // 1. Write the header row for the CSV
            String[] header = {"author", "title", "year", "published"};
            writer.writeNext(header);

            // 2. Write each publication as a new row in the CSV
            for (Map<String, Object> pub : publications) {
                String[] row = {
                        String.valueOf(pub.get("author")),
                        String.valueOf(pub.get("title")),
                        // JSON numbers are often read as Double, so we convert them to a clean String
                        String.valueOf(pub.get("year")),
                        String.valueOf(pub.get("published"))
                };
                writer.writeNext(row);
            }
        } catch (IOException e) {
            System.err.println("Error writing the CSV file: " + e.getMessage());
            throw e;
        }
    }
}