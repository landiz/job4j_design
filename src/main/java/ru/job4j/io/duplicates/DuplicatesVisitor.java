package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    public static Map<FileProperty, List<String>> paths(String path) throws IOException {
        Map<FileProperty, List<String>> duplicatedFiles = new HashMap<>();
        Files.walkFileTree(Path.of(path), new DuplicatesVisitor() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                FileProperty fileOne = new FileProperty(attrs.size(), file.getFileName().toString());
                duplicatedFiles.computeIfAbsent(fileOne, k -> new ArrayList<>()).add(file.toRealPath().toString());
                return FileVisitResult.CONTINUE;
            }
        });
        Iterator<Map.Entry<FileProperty, List<String>>> iterator = duplicatedFiles.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue().size() == 1) {
                iterator.remove();
            }
        }
        return duplicatedFiles;
    }
}