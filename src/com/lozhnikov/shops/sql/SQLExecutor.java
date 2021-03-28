package com.lozhnikov.shops.sql;

import com.lozhnikov.shops.SecretProperties;
import com.lozhnikov.shops.entities.Field;
import com.lozhnikov.shops.entities.Table;

import java.sql.*;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

public class SQLExecutor {
    private final Connection connection;
    private boolean connected = false;

    public SQLExecutor(String url, String login, String password) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        Properties props = new Properties();
        props.setProperty("user", login);
        props.setProperty("password", password);

        TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
        TimeZone.setDefault(timeZone);
        Locale.setDefault(Locale.ENGLISH);

        connection = DriverManager.getConnection(url, props);
        connected = true;
        System.out.println("SQL connected!");
    }

    public ResultSet runSQLRequest(String request) throws SQLException {
        PreparedStatement preStatement = connection.prepareStatement(request);
        return preStatement.executeQuery();
    }

    public boolean isConnected() {
        return connected;
    }

    public String createTables() {
        for (Table table : Model.tables) {
            StringBuilder request = new StringBuilder("create table " + table.getName() + " (\n");
            for (Field field : table.getFields()) {
                request.append(field.getName()).append(" ").append(field.getType());
                if (field.isNotNull()) {
                    request.append(" not null");
                }
                request.append(",\n");
            }
            request.append("primary key (");
            boolean isFirst = true;
            for (Field field : table.getFields()) {
                if (field.isPrimary()) {
                    if (!isFirst) {
                        request.append(", ");
                    }
                    else {
                        isFirst = false;
                    }
                    request.append(field.getName());
                }
            }
            request.append("))");
            System.out.println(request.toString());
            try {
                runSQLRequest(request.toString());
            }
            catch (SQLException ex) {
                return ex.getMessage();
            }
        }
        return "\n";
    }

    public String dropTables() {
        for (Table table : Model.tables) {
            StringBuilder request = new StringBuilder("drop table " + table.getName());
            System.out.println(request.toString());
            try {
                runSQLRequest(request.toString());
            }
            catch (SQLException ex) {
                return ex.getMessage();
            }
        }
        return "\n";
    }
}
