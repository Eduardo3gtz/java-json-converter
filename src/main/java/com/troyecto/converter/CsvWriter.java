package com.troyecto.converter;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CsvWriter {

    @SuppressWarnings("unchecked")
    public void writeCsvFile(String filePath, Map<?, ?> data) throws IOException {
        List<Map<String, Object>> prestamos = (List<Map<String, Object>>) data.get("prestamos");

        if (prestamos == null) {
            throw new IOException("The JSON file does not contain a 'prestamos' list.");
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // CAMBIO 1: La cabecera ahora dice "ano"
            String[] header = {"socio", "titulo", "ano", "devuelto"};
            writer.writeNext(header);

            for (Map<String, Object> prestamo : prestamos) {
                String[] row = {
                        String.valueOf(prestamo.get("socio")),
                        String.valueOf(prestamo.get("titulo")),
                        // CAMBIO 2: Ahora busca la clave "ano"
                        String.valueOf(prestamo.get("ano")),
                        String.valueOf(prestamo.get("devuelto"))
                };
                writer.writeNext(row);
            }
        } catch (IOException e) {
            System.err.println("Error writing the CSV file: " + e.getMessage());
            throw e;
        }
    }
}