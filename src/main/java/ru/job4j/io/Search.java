package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        Path startingDir = Paths.get("C:\\temp");
        String fileToSearch = ".txt";
        search(startingDir, fileToSearch);
    }

    public static List<String> search(Path startingDir, String fileToSearch) throws IOException {
        SearchFiles searcher = new SearchFiles(fileToSearch, startingDir);
        Files.walkFileTree(startingDir, searcher);
        return searcher.getPaths();
    }
}
