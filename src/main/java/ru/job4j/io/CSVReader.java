package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVReader {

    public static void handle(ArgsName argsName) throws IOException {
        final var LINE_BREAK = "\n";
        String delimiter = argsName.get("delimiter");
        StringBuilder result = new StringBuilder();
        String[] filterHeaders = argsName.get("filter").split(",");
        try (final Scanner scanner = new Scanner(new File(argsName.get("path")), StandardCharsets.UTF_8)) {
            if (scanner.hasNext()) {
                List<String> headers = List.of(scanner.nextLine().split(delimiter));
                if (!headers.containsAll(List.of(filterHeaders))) {
                    throw new ArrayIndexOutOfBoundsException("No any filters in csv file");
                }
                result.append(String.join(delimiter, filterHeaders)).append(LINE_BREAK);
                while (scanner.hasNext()) {
                    List<String> filteredValues = new ArrayList<>();
                    String[] values = scanner.nextLine().split(delimiter);
                    for (String header : filterHeaders) {
                        int indexOfHeader = headers.indexOf(header);
                        filteredValues.add(values[indexOfHeader]);
                    }
                    result.append(String.join(delimiter, filteredValues)).append(LINE_BREAK);
                }
            }
        }
        if ("stdout".equals(argsName.get("out"))) {
            System.out.println(result);
        } else {
            Files.writeString(Path.of(argsName.get("out")), result.toString());
        }
    }

    private static void checkArgs(ArgsName argsName) {
        File file = new File(argsName.get("path"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("File not exist: %s", argsName.get("path")));
        }
        Pattern pattern = Pattern.compile(".+\\.csv$");
        Matcher matcher = pattern.matcher(argsName.get("path"));
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("Invalid file format for input file: %s", argsName.get("path")));
        }

        pattern = Pattern.compile(".+\\..+");
        matcher = pattern.matcher(argsName.get("out"));
        if (!matcher.matches()) {
            if (!"stdout".equals(argsName.get("out"))) {
                throw new IllegalArgumentException(String.format("String not equal \"stdout\": %s", argsName.get("out")));
            }
        } else {
            pattern = Pattern.compile(".+\\.csv$");
            matcher = pattern.matcher(argsName.get("out"));
            if (!matcher.matches()) {
                throw new IllegalArgumentException(String.format("Invalid file format for output file: %s", argsName.get("out")));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Enter csv file, delimiter for csv file, output file and output columns.");
        }
        ArgsName argsName = ArgsName.of(args);
        checkArgs(argsName);
        handle(argsName);
    }
}