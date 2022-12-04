package ru.job4j.io.duplicates;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Map<FileProperty, List<String>> duplicatedFiles = DuplicatesVisitor.paths("/Users/landiz/Downloads");
    }
}