package com.lozhnikov.shops.sql;

import com.lozhnikov.shops.SecretProperties;

import java.sql.*;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

public class SQLExecutor {
    Connection connection;

    public SQLExecutor() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        String url = SecretProperties.DB_URL;
        Properties props = new Properties();
        props.setProperty("user", SecretProperties.DB_USER_LOGIN);
        props.setProperty("password", SecretProperties.DB_USER_LOGIN);

        TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
        TimeZone.setDefault(timeZone);
        Locale.setDefault(Locale.ENGLISH);

        connection = DriverManager.getConnection(url, props);
    }

    ResultSet runSQLRequest(String request) throws SQLException {
        PreparedStatement preStatement = connection.prepareStatement(request);
        return preStatement.executeQuery();
    }
}
