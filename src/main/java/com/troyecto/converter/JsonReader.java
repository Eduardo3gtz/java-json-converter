package com.troyecto.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

/**
 * This class is responsible for reading and parsing JSON files.
 * It uses the Gson library to convert the JSON content into a Java data structure.
 */
public class JsonReader {

    /**
     * Reads a JSON file from a given path and converts it into a Map object.
     *
     * @param filePath The path to the JSON file to be read.
     * @return A Map object representing the JSON data structure.
     * @throws FileNotFoundException If the file cannot be found at the specified path.
     * @throws JsonSyntaxException If the JSON file has formatting errors.
     */
    public Map<?, ?> readJsonFile(String filePath) throws FileNotFoundException, JsonSyntaxException {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader(filePath);
            // Gson parses the JSON file into a generic Map structure.
            return gson.fromJson(reader, Map.class);
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found at path: " + filePath);
            throw e;
        } catch (JsonSyntaxException e) {
            System.err.println("Error: The JSON file has an invalid format.");
            throw e;
        }
    }
}