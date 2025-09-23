package com.kodilla.tictactoe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {
    public void readFile() {
        Path path = Paths.get("src/main/resources/results");
        System.out.println("\nGame results from the file:\n ");

        try (Stream<String> stream = Files.lines(path)) {

            stream.forEach(System.out::println);

        } catch (IOException e){
            System.out.println("An error occurred: " + e);
        }
    }
}
