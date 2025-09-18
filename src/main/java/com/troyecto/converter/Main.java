package com.troyecto.converter;

import java.util.Map;

/**
 * The main class that runs the entire JSON to CSV conversion process.
 * It orchestrates the reading of the JSON file and the writing of the CSV file.
 */
public class Main {

    /**
     * The entry point of the application.
     * @param args Command line arguments (not used in this project).
     */
    public static void main(String[] args) {
        // Create instances of our reader and writer classes
        JsonReader jsonReader = new JsonReader();
        CsvWriter csvWriter = new CsvWriter();

        // Define the input and output file paths
        String jsonFilePath = "input.json"; // Assumes the file is in the project's root folder
        String csvFilePath = "output.csv";  // The file that will be created

        try {
            // 1. Read the JSON file
            System.out.println("Reading JSON file...");
            Map<?, ?> data = jsonReader.readJsonFile(jsonFilePath);
            System.out.println("JSON read successfully.");

            // 2. Write the CSV file
            System.out.println("Writing CSV file...");
            csvWriter.writeCsvFile(csvFilePath, data);
            System.out.println("Process completed successfully! Check the " + csvFilePath + " file.");

        } catch (Exception e) {
            // Catches any error from the reader or writer and prints a message
            System.err.println("An error occurred during the process: " + e.getMessage());
        }
    }
}