package com.lozhnikov.shops.view;

import com.lozhnikov.shops.sql.SQLExecutor;

import javax.swing.*;

public class CreateTablesPanel {
    private final JFrame mainFrame;
    private final MenuPanel menuPanel;
    private final SQLExecutor sqlExecutor;

    public CreateTablesPanel(JFrame mainFrame, MenuPanel menuPanel, SQLExecutor sqlExecutor) {
        this.mainFrame = mainFrame;
        this.menuPanel = menuPanel;
        this.sqlExecutor = sqlExecutor;
    }

    private void init() {

    }

    public void start() {

    }

    private void close() {
        menuPanel.start();
    }
}
