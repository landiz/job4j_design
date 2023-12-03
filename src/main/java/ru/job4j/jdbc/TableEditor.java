package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static Connection connection;
    private static Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        TableEditor.properties = properties;
        initConnection();
    }

    public static void main(String[] args) throws Exception {
        Properties props = initProperties("app.properties");
        TableEditor tableEditor = new TableEditor(props);
        String tableName = "demo_table";
        String columnName = "addedColumn";
        String renamedColumnName = "renamedColumn";
        tableEditor.createTable(tableName);
        System.out.println(getTableScheme(connection, tableName));
        tableEditor.addColumn(tableName, columnName, "text");
        System.out.println(getTableScheme(connection, tableName));
        tableEditor.renameColumn(tableName, columnName, renamedColumnName);
        System.out.println(getTableScheme(connection, tableName));
        tableEditor.dropColumn(tableName, renamedColumnName);
        System.out.println(getTableScheme(connection, tableName));
        tableEditor.dropTable(tableName);
    }

    private static void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver_class"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format("SELECT * FROM %s LIMIT 1", tableName));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n", metaData.getColumnName(i), metaData.getColumnTypeName(i)));
            }
        }
        return buffer.toString();
    }

    public static Properties initProperties(String fileConfig) {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream(fileConfig)) {
            config.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return config;
    }

    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s (id SERIAL PRIMARY KEY, name TEXT);", tableName
            );
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "DROP TABLE IF EXISTS %s;", tableName
            );
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s;", tableName, columnName, type
            );
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE IF EXISTS %s DROP COLUMN IF EXISTS %s;", tableName, columnName
            );
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE IF EXISTS %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName
            );
            statement.execute(sql);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}