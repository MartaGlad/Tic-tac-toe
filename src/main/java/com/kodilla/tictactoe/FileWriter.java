package com.kodilla.tictactoe;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {
    public void writeFile(String message) {
        Path path = Paths.get("src/main/resources/results");

        try (BufferedWriter writer = Files.newBufferedWriter (
                path,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            writer.write(message);
            System.out.println("\nResult has been written to the results file.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }
    }
}
