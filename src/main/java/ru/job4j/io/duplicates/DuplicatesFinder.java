package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("/Users/landiz/Downloads/"), new DuplicatesVisitor());
        DuplicatesVisitor.printDuplicatedFilePaths();
    }
}