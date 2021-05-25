package com.lozhnikov.shops.sql;

import com.lozhnikov.shops.SecretProperties;
import com.lozhnikov.shops.entities.Field;
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
    private final String schema = "shop_admin.";
    private final String login;

    public SQLExecutor(String url, String login, String password) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        this.login = login;

        Properties props = new Properties();
        props.setProperty("user", login);
        props.setProperty("password", password);
        props.setProperty("characterEncoding", "utf8");

        TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
        TimeZone.setDefault(timeZone);
        Locale.setDefault(Locale.ENGLISH);

        connection = DriverManager.getConnection(url, props);
        System.out.println("SQL connected!");
    }

    public ResultSet runSQLRequest(String request, boolean close) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(request);
        if (close) statement.close();
        return resultSet;
    }

    private String runSQLScript(String fileName) {
        String toReturn = "";
        String fileContent = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream(fileName)))
                .lines().collect(Collectors.joining());
        String[] requests = fileContent.split(";");
        for (int i = 0; i < requests.length; ++i) {
            StringBuilder request = new StringBuilder(requests[i]);
            if (i != requests.length - 1 && requests[i + 1].equals("END")) {
                request.append("; ").append(requests[i + 1]).append(";");
                i++;
            }
            try {
                System.out.println(request.toString());
                runSQLRequest(request.toString(), true);
            }
            catch (SQLException ex) {
                toReturn = returnError(ex.getMessage());
            }
        }

        return toReturn;
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
        StringBuilder request = new StringBuilder("insert into " +
                schema + "\"" + table.getName() + "\"\n");
        request.append("(");
        boolean isFirst = true;
        for (Value value : row.getValues()) {
            if (!isFirst) {
                request.append(", ");
            }
            else {
                isFirst = false;
            }
            request.append("\"").append(value.getField().getName()).append("\"");
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
            if (value.getField().isString()) {
                request.append("'");
            }
            request.append(value.getValue());
            if (value.getField().isString()) {
                request.append("'");
            }
        }
        request.append(")");

        try {
            System.out.println(request.toString());
            runSQLRequest(request.toString(), true);
        }
        catch (SQLException ex) {
            return returnError(ex.getMessage());
        }
        return "";
    }

    public void updateValue(Table table, Value newValue, Row oldRow) throws SQLException {
        StringBuilder request = new StringBuilder("update " +
                schema + "\"" + table.getName() + "\"\nset ");
        request.append("\"").append(newValue.getField().getName()).append("\" = ");
        if (newValue.getField().isString()) {
            request.append("'");
        }
        request.append(newValue.getValue());
        if (newValue.getField().isString()) {
            request.append("'");
        }
        request.append("\n where \n").append(getWhereCondition(oldRow));
        System.out.println(request.toString());
        runSQLRequest(request.toString(), true);
    }

    public String deleteRow(Table table, Row row) {
        StringBuilder request = new StringBuilder("delete from " +
                schema + "\"" + table.getName() + "\" where\n");
        request.append(getWhereCondition(row));
        System.out.println(request);
        try {
            runSQLRequest(request.toString(), true);
        }
        catch (SQLException ex) {
            return returnError(ex.getMessage());
        }
        return "";
    }

    private String getWhereCondition(Row row) {
        StringBuilder request = new StringBuilder();
        boolean isFirst = true;
        for (Value value : row.getValues()) {
            if (value.getValue() == null) {
                continue;
            }
            if (!isFirst) {
                request.append(" and\n");
            }
            else {
                isFirst = false;
            }
            request.append("\"").append(value.getField().getName()).append("\"")
                    .append(" = ");
            if (value.getField().isString()) {
                request.append("'");
            }
            request.append(value.getValue());
            if (value.getField().isString()) {
                request.append("'");
            }
        }
        return request.toString();
    }

    private String returnError(String error) {
        if (error.length() > SecretProperties.MAX_ERROR_LENGTH) {
            error = error.substring(0, SecretProperties.MAX_ERROR_LENGTH) + "...";
        }
        return error;
    }

    public ResultSet getAllTableValues(Table table) throws SQLException {
        return runSQLRequest("select * from " + schema + "\"" + table.getName() + "\"", false);
    }

    public void close() {
        try {
            connection.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getLogin() {
        return login;
    }
}
