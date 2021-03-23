package com.lozhnikov.shops.sql;

import com.lozhnikov.shops.SecretProperties;

import java.sql.*;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

public class SQLExecutor {
    private final Connection connection;
    private boolean connected = false;

    public SQLExecutor() throws ClassNotFoundException, SQLException {
        this(SecretProperties.DB_URL, SecretProperties.DB_USER_LOGIN, SecretProperties.DB_USER_PASSWORD);
    }

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
    }

    public ResultSet runSQLRequest(String request) throws SQLException {
        PreparedStatement preStatement = connection.prepareStatement(request);
        return preStatement.executeQuery();
    }

    public boolean isConnected() {
        return connected;
    }
}
