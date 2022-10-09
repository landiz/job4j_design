package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }

    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            list = in.lines().filter(s -> s.matches(".*\\s404\\s\\S*$")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            log.stream().forEach(c -> out.println(c));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
