package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {

    public static void main(String[] args) throws IOException {
        checkArgs(args);
        search(Path.of(args[0]), p -> p.toFile().getName().endsWith(args[1]));
    }

    public static void checkArgs(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Enter the folder for archiving, file extensions that will not be included in the archive and the file name for the archive.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Folder not exist: %s", args[0]));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not a directory: %s", args[0]));
        }
        Pattern pattern = Pattern.compile("^\\.\\S.+");
        Matcher matcher = pattern.matcher(args[1]);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("Not an extension of the files: %s", args[1]));
        }
        pattern = Pattern.compile(".+\\.zip$");
        matcher = pattern.matcher(args[2]);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("Not a file format like \"file.zip\": %s", args[2]));
        }
    }

    public static List<Path> search(Path startingDir, Predicate<Path> fileToSearch) throws IOException {
        SearchFiles searcher = new SearchFiles(fileToSearch);
        Files.walkFileTree(startingDir, searcher);
        return searcher.getPaths();
    }
}
