package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Analysis {

    private static final Map<String, String> VALUES = new TreeMap<>();

    public static void unavailable(String source, String target) {
        boolean serverDown = false;
        List<String> list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(s -> list.add(String.valueOf(s)));
            for (String line : list) {
                String[] words = (line.split(" "));
                VALUES.put(words[1], words[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String logLine = null;
        List<String> listOut = new ArrayList<>();
        for (Map.Entry<String, String> entry : VALUES.entrySet()) {

            if ("400".equals(entry.getValue()) || "500".equals(entry.getValue())) {
                if (!serverDown) {
                    logLine = entry.getKey() + ";";
                    serverDown = true;
                }
            } else {
                if (serverDown) {
                    listOut.add(logLine.concat(entry.getKey()));
                    logLine = "";
                    serverDown = false;
                }
            }
        }
        try (FileOutputStream out = new FileOutputStream(target)) {
            for (String line : listOut) {
                out.write(line.getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("./data/source.log", "./data/target.log");
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}