package com.lozhnikov.shops.sql;

import com.lozhnikov.shops.entities.Row;
import com.lozhnikov.shops.entities.Table;
import com.lozhnikov.shops.entities.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SQLExecutor {
    private final Connection connection;

    public SQLExecutor(String url, String login, String password) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        Properties props = new Properties();
        props.setProperty("user", login);
        props.setProperty("password", password);

        TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
        TimeZone.setDefault(timeZone);
        Locale.setDefault(Locale.ENGLISH);

        connection = DriverManager.getConnection(url, props);
        System.out.println("SQL connected!");
    }

    public ResultSet runSQLRequest(String request) throws SQLException {
        PreparedStatement preStatement = connection.prepareStatement(request);
        return preStatement.executeQuery();
    }

    private String runSQLScript(String fileName) {
        String fileContent = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream(fileName)))
                .lines().collect(Collectors.joining());
        String[] requests = fileContent.split(";");
        try {
            connection.setAutoCommit(false);
            for (String request : requests) {
                runSQLRequest(request);
            }
            connection.commit();
        }
        catch (SQLException ex) {
            try {
                connection.rollback();
            }
            catch (SQLException ex1) {
                ex.printStackTrace();
            }
            return ex.getMessage();
        }
        return "";
    }

    public String createTables() {
        return runSQLScript("/scripts/create.sql");
    }

    public String dropTables() {
        return runSQLScript("/scripts/drop.sql");
    }

    public String insertValues() {
        return runSQLScript("/scripts/insert.sql");
    }

    public String insertValue(Table table, Row row) {
        StringBuilder request = new StringBuilder("insert into " + table.getName() + "\n");
        request.append("(");
        boolean isFirst = true;
        for (Value value : row.getValues()) {
            if (!isFirst) {
                request.append(", ");
            }
            else {
                isFirst = false;
            }
            request.append(value.getField().getName());
        }
        request.append(")\nvalues\n(");
        isFirst = true;
        for (Value value : row.getValues()) {
            if (!isFirst) {
                request.append(", ");
            }
            else {
                isFirst = false;
            }
            request.append(value.getValue());
        }
        request.append(")");
        System.out.println(request.toString());

        try {
            runSQLRequest(request.toString());
        }
        catch (SQLException ex) {
            return ex.getMessage();
        }
        return "";
    }

    public ResultSet getAllTableValues(Table table) throws SQLException {
        System.out.println(table.getName());
        return runSQLRequest("select * from \"" + table.getName() + "\"");
    }
}
