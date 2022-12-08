package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private Map<String, String> values = new HashMap<>();

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("");
        }
        for (String line : args) {
            if (!line.contains("=")) {
                throw new IllegalArgumentException();
            }

            if (!line.contains("-")) {
                throw new IllegalArgumentException();
            }
            String key = line.substring(line.indexOf("-") + 1, line.indexOf("="));
            String value = line.substring(line.indexOf("=") + 1);
            if (key.isBlank() || value.isBlank()) {
                throw new IllegalArgumentException();
            }
            values.put(key, value);
        }
    }
}