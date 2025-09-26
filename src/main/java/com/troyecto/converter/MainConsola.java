package com.troyecto.converter;

import java.util.Map;

/**
 * The main class that runs the entire JSON to CSV conversion process.
 * This version is configurable via command-line arguments.
 */
public class MainConsola {

    /**
     * The entry point of the application.
     * It requires two arguments: the input JSON file path and the output CSV file path.
     * @param args Command line arguments: args[0] is the input JSON file, args[1] is the output CSV file.
     */
    public static void main(String[] args) {
        // 1. Verify that the correct number of arguments are provided.
        if (args.length < 2) {
            System.out.println("Error: Missing arguments.");
            System.out.println("Usage: java -jar program.jar <inputFile.json> <outputFile.csv>");
            return; // Exit the program
        }

        // 2. Get file paths from the arguments.
        String jsonFilePath = args[0];
        String csvFilePath = args[1];

        JsonReader jsonReader = new JsonReader();
        CsvWriter csvWriter = new CsvWriter();

        try {
            // 3. Read the JSON file
            System.out.println("Reading JSON file from: " + jsonFilePath);
            Map<?, ?> data = jsonReader.readJsonFile(jsonFilePath);
            System.out.println("JSON read successfully.");

            // 4. Write the CSV file
            System.out.println("Writing CSV file to: " + csvFilePath);
            csvWriter.writeCsvFile(csvFilePath, data);
            System.out.println("Process completed successfully! Check the " + csvFilePath + " file.");

        } catch (Exception e) {
            System.err.println("An error occurred during the process: " + e.getMessage());
        }
    }
}