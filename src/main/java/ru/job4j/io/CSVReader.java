package ru.job4j.io;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        final var LINE_BREAK = "\n";
        checkArgs(new String[]{argsName.get("path"), argsName.get("delimiter"), argsName.get("out"), argsName.get("filter")});
        String delimiter = argsName.get("delimiter");
        StringBuilder result = new StringBuilder();
        String[] filterHeaders = argsName.get("filter").split(",");
        try (final Scanner scanner = new Scanner(new File(argsName.get("path")), StandardCharsets.UTF_8)) {
            if (scanner.hasNext()) {
                List<String> headers = List.of(scanner.nextLine().split(delimiter));
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

    private static void checkArgs(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Enter the folder for archiving, file extensions that will not be included in the archive and the file name for the archive.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("File not exist: %s", args[0]));
        }
        Pattern pattern = Pattern.compile(".+\\.csv$");
        Matcher matcher = pattern.matcher(args[0]);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("Invalid file format for input file: %s", args[0]));
        }

        pattern = Pattern.compile(".+\\..+");
        matcher = pattern.matcher(args[2]);
        if (!matcher.matches()) {
            if (!"stdout".equals(args[2])) {
                throw new IllegalArgumentException(String.format("String not equal \"stdout\": %s", args[2]));
            }
        } else {
            pattern = Pattern.compile(".+\\.csv$");
            matcher = pattern.matcher(args[2]);
            if (!matcher.matches()) {
                throw new IllegalArgumentException(String.format("Invalid file format for output file: %s", args[2]));
            }
        }
    }
}