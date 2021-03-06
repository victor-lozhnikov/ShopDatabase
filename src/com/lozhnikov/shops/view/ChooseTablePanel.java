package com.lozhnikov.shops.view;

import com.lozhnikov.shops.SecretProperties;
import com.lozhnikov.shops.entities.Table;
import com.lozhnikov.shops.sql.Model;
import com.lozhnikov.shops.sql.SQLExecutor;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChooseTablePanel extends JPanel {
    private final JFrame mainFrame;
    private final MenuPanel menuPanel;
    private final SQLExecutor sqlExecutor;

    public ChooseTablePanel(JFrame mainFrame, MenuPanel menuPanel, SQLExecutor sqlExecutor) {
        this.mainFrame = mainFrame;
        this.menuPanel = menuPanel;
        this.sqlExecutor = sqlExecutor;
    }

    private void init() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(8, 8, 8, 8);

        add(new JLabel("Выберите таблицу:"), gbc);

        JPanel tables = new JPanel(new GridBagLayout());

        JLabel infoLabel = new JLabel("\n");

        for (Table table : Model.tables) {
            if (sqlExecutor.getLogin().equals(SecretProperties.DB_BUYER_LOGIN) &&
                    table.getAccess() > 0) {
                continue;
            }
            if (sqlExecutor.getLogin().equals(SecretProperties.DB_MANAGER_LOGIN) &&
                    table.getAccess() > 1) {
                continue;
            }
            JButton tableButton = new JButton(table.getTranslate());
            tables.add(tableButton, gbc);

            tableButton.addActionListener(e -> {
                ViewTablePanel viewTablePanel = new ViewTablePanel(mainFrame, this, table, sqlExecutor);
                viewTablePanel.start();
            });

            gbc.gridx++;
            if (gbc.gridx == 4) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }

        gbc.gridx = 0;
        gbc.gridy = 1;

        add(tables, gbc);

        gbc.gridy++;
        add(infoLabel, gbc);

        gbc.gridy++;
        JButton closeButton = new JButton("Вернуться в главное меню");
        closeButton.addActionListener(e -> {
            close();
        });
        add(closeButton, gbc);
    }

    public void start() {
        removeAll();
        init();
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(this);
        update(getGraphics());
        mainFrame.revalidate();
    }

    private void close() {
        menuPanel.start();
    }
}
