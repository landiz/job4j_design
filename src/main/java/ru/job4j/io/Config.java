package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public static void main(String[] args) {
        System.out.println(new Config("./data/pairwithoutcomment.properties"));
    }

    public void load() {
        List<String> list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(s -> list.add(String.valueOf(s)));
            for (String line : list) {
                if (line.equals("") || line.equals("=") || line.substring(0, 1).equals("#")) {
                    continue;
                }
                Map.Entry<String, String> entry = getEntry(line);
                values.put(entry.getKey(), entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map.Entry<String, String> getEntry(String line) {
        if (!line.contains("=")) {
            throw new IllegalArgumentException();
        }
        if (line.substring(line.indexOf("=") + 1).equals("")) {
            throw new IllegalArgumentException();
        }
        if (line.substring(0, line.indexOf("=")).equals("")) {
            throw new IllegalArgumentException();
        }
        String key = line.substring(0, line.indexOf("="));
        String value = line.substring(line.indexOf("=") + 1);

        return new AbstractMap.SimpleEntry<>(key, value);
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

}