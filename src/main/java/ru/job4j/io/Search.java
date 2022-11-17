package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    @SuppressWarnings("checkstyle:GenericWhitespace")
    public static void main(String[] args) throws IOException {
        Path startingDir = Paths.get("C:\\temp");
        search(startingDir, p -> p.toFile().getName().endsWith(".txt"));
    }

    public static List<String> search(Path startingDir, Predicate<Path> fileToSearch) throws IOException {
        SearchFiles searcher = new SearchFiles(startingDir, fileToSearch);
        Files.walkFileTree(startingDir, searcher);
        return searcher.getPaths();
    }
}
