package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    static Map<FileProperty, List<String>> duplicatedFiles = new HashMap<>();

    public static void printDuplicatedFilePaths() {
        Iterator<Map.Entry<FileProperty, List<String>>> iterator = duplicatedFiles.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue().size() == 1) {
                iterator.remove();
            }
        }
        for (Map.Entry<FileProperty, List<String>> entry : duplicatedFiles.entrySet()) {
            System.out.println(String.format("%s  -  %s Kb", entry.getKey().getName(), entry.getKey().getSize() / 1024));
            for (String str : entry.getValue()) {
                System.out.println(str);
            }
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileOne = new FileProperty(attrs.size(), file.getFileName().toString());
        duplicatedFiles.computeIfAbsent(fileOne, k -> new ArrayList<>()).add(file.toRealPath().toString());
        return FileVisitResult.CONTINUE;
    }
}