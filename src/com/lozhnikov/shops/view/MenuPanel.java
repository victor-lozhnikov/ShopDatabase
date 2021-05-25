package com.lozhnikov.shops.view;

import com.lozhnikov.shops.SecretProperties;
import com.lozhnikov.shops.sql.SQLExecutor;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Callable;

public class MenuPanel extends JPanel {
    private final JFrame mainFrame;
    private final SQLExecutor sqlExecutor;
    private JLabel infoLabel;
    private LoginPanel loginPanel;

    public MenuPanel(JFrame mainFrame, SQLExecutor sqlExecutor, LoginPanel loginPanel) {
        this.mainFrame = mainFrame;
        this.sqlExecutor = sqlExecutor;
        this.loginPanel = loginPanel;
    }

    private void init() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(8, 8, 8, 8);

        JButton createButton = new JButton("Создать таблицы");
        if (sqlExecutor.getLogin().equals(SecretProperties.DB_ADMIN_LOGIN)) {
            add(createButton, gbc);
        }

        gbc.gridy++;
        JButton dropButton = new JButton("Удалить таблицы");
        if (sqlExecutor.getLogin().equals(SecretProperties.DB_ADMIN_LOGIN)) {
            add(dropButton, gbc);
        }

        gbc.gridy++;
        JButton insertButton = new JButton("Заполнить таблицы");
        if (sqlExecutor.getLogin().equals(SecretProperties.DB_ADMIN_LOGIN)) {
            add(insertButton, gbc);
        }

        gbc.gridy++;
        JButton viewButton = new JButton("Просмотреть таблицы");
        add(viewButton, gbc);


        gbc.gridy++;
        JButton exitButton = new JButton("Выход");
        add(exitButton, gbc);

        gbc.gridy++;
        infoLabel = new JLabel("\n");
        add(infoLabel, gbc);

        createButton.addActionListener(e -> {
            if (buttonFunction(sqlExecutor::createTables)) {
                infoLabel.setForeground(Color.BLACK);
                infoLabel.setText("Таблицы созданы");
            }
        });

        dropButton.addActionListener(e -> {
            if (buttonFunction(sqlExecutor::dropTables)) {
                infoLabel.setForeground(Color.BLACK);
                infoLabel.setText("Таблицы удалены");
            }
        });
        insertButton.addActionListener(e -> {
            infoLabel.setForeground(Color.BLACK);
            infoLabel.setText("Запрос выполняется...");
            mainFrame.revalidate();
            update(getGraphics());
            String error = sqlExecutor.insertValues();
            if (error.isEmpty()) {
                infoLabel.setText("Данные добавлены");
            }
            else {
                infoLabel.setForeground(Color.RED);
                infoLabel.setText(error);
            }
        });
        viewButton.addActionListener(e -> {
            ChooseTablePanel chooseTablePanel = new ChooseTablePanel(mainFrame, this,
                    sqlExecutor);
            chooseTablePanel.start();
        });
        exitButton.addActionListener(e -> {
            close();
        });
    }

    public void start() {
        removeAll();
        init();
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(this);
        update(getGraphics());
        mainFrame.revalidate();
    }

    private boolean buttonFunction(Callable<String> function) {
        infoLabel.setForeground(Color.BLACK);
        infoLabel.setText("Запрос выполняется...");
        mainFrame.revalidate();
        update(getGraphics());
        String error = "\n";
        try {
            error = function.call();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (error.isEmpty()) {
            return true;
        }
        infoLabel.setForeground(Color.RED);
        infoLabel.setText(error);
        return false;
    }

    private void close() {
        sqlExecutor.close();
        loginPanel.start();
    }
}
