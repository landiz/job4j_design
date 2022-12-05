package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    @SuppressWarnings("checkstyle:GenericWhitespace")
    public static void main(String[] args) throws IOException {
        checkArgs(args);
        search(Path.of(args[0]), p -> p.toFile().getName().endsWith(args[1]));
    }

    private static void checkArgs(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Specify the starting folder and extension of the files to be searched.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Folder not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }

    public static List<Path> search(Path startingDir, Predicate<Path> fileToSearch) throws IOException {
        SearchFiles searcher = new SearchFiles(fileToSearch);
        Files.walkFileTree(startingDir, searcher);
        return searcher.getPaths();
    }
}
