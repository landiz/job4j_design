package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private static Properties config;
    private final String dump;

    public ImportDB(Properties config, String dump) {
        ImportDB.config = config;
        this.dump = dump;
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("spammer.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "./data/dump.txt");
        dataBase.createTable();
        dataBase.save(dataBase.load());
    }

    public List<User> load() throws IOException {
        List<String> list = new ArrayList<>();
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().forEach(s -> list.add(String.valueOf(s)));
            for (String line : list) {
                if (!line.isBlank()) {
                    if (!line.startsWith("#")) {
                        checkLine(line, list);
                        String key = line.substring(0, line.indexOf(";"));
                        String value = line.substring((line.indexOf(";") + 1), line.lastIndexOf(";"));
                        users.add(new User(key, value));
                    }
                }
            }
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(config.getProperty("jdbc.url"), config.getProperty("jdbc.username"), config.getProperty("jdbc.password"))) {
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    public void createTable() throws Exception {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(config.getProperty("jdbc.url"), config.getProperty("jdbc.username"), config.getProperty("jdbc.password"))) {
            try (PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name TEXT, email TEXT);")) {
                statement.executeUpdate();
            }
        }
    }

    private void checkLine(String line, List<String> list) {
        if (";".equals(line)) {
            throw new IllegalArgumentException("Line " + (list.indexOf(line) + 1) + "'" + line + "'" + " is missing Key and Value");
        }
        if (!line.contains(";")) {
            throw new IllegalArgumentException("Line " + (list.indexOf(line) + 1) + " '" + line + "'" + " is missing \";\"");
        }
        if ((line.substring(line.indexOf(";") + 1)).isBlank()) {
            throw new IllegalArgumentException("Line " + (list.indexOf(line) + 1) + " '" + line + "'" + " is missing Value");
        }
        if ((line.substring(0, line.indexOf(";"))).isBlank()) {
            throw new IllegalArgumentException("Line " + (list.indexOf(line) + 1) + " '" + line + "'" + " is missing Key");
        }
        if (!(line.endsWith(";"))) {
            throw new IllegalArgumentException("Line " + (list.indexOf(line) + 1) + " '" + line + "'" + " is missing symbol ';' at the end");
        }
    }

    private static class User {
        final String name;
        final String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }
}