package com.lozhnikov.shops;

import com.lozhnikov.shops.sql.SQLExecutor;

import java.sql.*;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        try {
            SQLExecutor executor = new SQLExecutor();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
