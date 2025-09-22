# Project: Back-End in Java for Information Processing

**Author:** Eduardo Antonio Gutierrez Carreon
**UUID:** 3331

This is the repository for the Sprint 2 challenge, which consists of a desktop application in Java to convert JSON files to CSV.

---
## Description
The application reads a JSON file containing scientific publication data and processes it to generate a structured CSV file, ready to be used for reports.

---
## Project Structure
The project is organized into several key files. Here is a breakdown of what each component does:

* **`pom.xml`**: This is the core configuration file for Maven. It defines the project's dependencies, such as the **Gson** and **OpenCSV** libraries, and tells the IDE how to build the project.
* **`input.json`**: An example JSON file that serves as the input data for the program.
* **`src/main/java/com/troyecto/converter/`**: This is the main package containing all the Java source code.
    * **`JsonReader.java`**: This class is responsible for reading the `input.json` file. It uses the Gson library to parse the data into a structure that Java can understand.
    * **`CsvWriter.java`**: This class takes the data processed by the `JsonReader` and uses the OpenCSV library to write it into a formatted `.csv` file.
    * **`Main.java`**: This is the entry point of the application. It orchestrates the entire process by creating instances of the reader and writer and calling their methods in the correct order.

---
## Features
- Reads JSON files.
- Parses data into Java data structures.
- Writes data into CSV format.
- Handles exceptions for files that are not found or have incorrect formatting.
- Code is documented using JavaDoc.

---
## How to Review and Run the Project
To review and run this project, follow these steps:

1.  **Clone the repository:**
    ```bash
    git clone [URL_of_your_repository]
    ```
2.  **Open the project:** Import the project as a "Maven Project" in your IDE (IntelliJ). The IDE will automatically download the required dependencies (Gson and OpenCSV).

3.  **Run the program:** Locate the `Main.java` class and run it. Ensure you have an `input.json` file in the project's root directory so it can be read. The output will be generated in an `output.csv` file.

---
## Libraries Used
- **Gson (2.10.1):** For JSON manipulation.
- **OpenCSV (5.9):** For writing CSV files.