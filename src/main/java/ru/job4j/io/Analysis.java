package ru.job4j.io;

import java.io.*;

public class Analysis {
    public static void unavailable(String source, String target) {
        boolean serverDown = false;
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             FileOutputStream out = new FileOutputStream(target)) {
            line = reader.readLine();
            while (line != null) {
                String[] words = (line.split(" "));
                if ("400".equals(words[0]) || "500".equals(words[0])) {
                    if (!serverDown) {
                        out.write(words[1].getBytes());
                        out.write(";".getBytes());
                        serverDown = true;
                    }
                } else {
                    if (serverDown) {
                        out.write(words[1].getBytes());
                        out.write(System.lineSeparator().getBytes());
                        serverDown = false;
                    }
                }
                line = reader.readLine();
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

