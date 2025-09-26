package com.troyecto.converter;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.Map;

public class MainGui extends Application {

    private File selectedJsonFile;
    private final Label statusLabel = new Label("Status: Please select a JSON file to begin.");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JSON to CSV Converter");

        // --- 1. Create the UI elements ---
        Label instructionLabel = new Label("Step 1: Select your JSON file");
        Button selectFileButton = new Button("Select File...");

        Label instructionLabel2 = new Label("Step 2: Convert and save the file");
        Button convertButton = new Button("Convert to CSV");

        // --- Asignar un ID a la etiqueta de estado para el CSS ---
        statusLabel.setId("statusLabel");

        // --- 2. Define button actions ---
        selectFileButton.setOnAction(e -> selectJsonFile(primaryStage));
        convertButton.setOnAction(e -> convertFile());

        // --- 3. Organize the layout ---
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20)); // Padding interno, el CSS maneja el externo
        layout.getChildren().addAll(instructionLabel, selectFileButton, instructionLabel2, convertButton, statusLabel);

        // --- 4. Set the scene and apply the stylesheet ---
        Scene scene = new Scene(layout, 400, 250);

        // --- LÍNEA CLAVE: Cargar y aplicar el archivo CSS ---
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // (El resto de tus métodos 'selectJsonFile' y 'convertFile' se quedan exactamente igual)

    private void selectJsonFile(Stage ownerStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open JSON File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        selectedJsonFile = fileChooser.showOpenDialog(ownerStage);

        if (selectedJsonFile != null) {
            statusLabel.setText("File selected: ".concat(selectedJsonFile.getName()));
        }
    }

    private void convertFile() {
        if (selectedJsonFile == null) {
            statusLabel.setText("Status: ERROR - Please select a JSON file first!");
            return;
        }

        try {
            JsonReader reader = new JsonReader();
            CsvWriter writer = new CsvWriter();

            String downloadsPath = System.getProperty("user.home").concat(File.separator).concat("Downloads").concat(File.separator);
            String outputCsvName = selectedJsonFile.getName().replace(".json", ".csv");
            String outputCsvPath = downloadsPath.concat(outputCsvName);

            statusLabel.setText("Status: Converting...");
            Map<?, ?> data = reader.readJsonFile(selectedJsonFile.getAbsolutePath());
            writer.writeCsvFile(outputCsvPath, data);
            statusLabel.setText("Status: SUCCESS! File saved as '".concat(outputCsvName).concat("' in your Downloads folder."));

        } catch (Exception ex) {
            statusLabel.setText("Status: ERROR - ".concat(ex.getMessage()));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}