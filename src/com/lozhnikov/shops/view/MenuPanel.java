package com.lozhnikov.shops.view;

import com.lozhnikov.shops.sql.SQLExecutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private final JFrame mainFrame;
    private final SQLExecutor sqlExecutor;

    public MenuPanel(JFrame mainFrame, SQLExecutor sqlExecutor) {
        this.mainFrame = mainFrame;
        this.sqlExecutor = sqlExecutor;
    }

    private void init() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(8, 8, 8, 8);

        JButton createButton = new JButton("Создать таблицы");
        add(createButton, gbc);

        gbc.gridy++;
        JButton dropButton = new JButton("Удалить таблицы");
        add(dropButton, gbc);

        gbc.gridy++;
        add(new JButton("Заполнить таблицы"), gbc);

        gbc.gridy++;
        add(new JButton("Просмотреть таблицы"), gbc);

        gbc.gridy++;
        JLabel infoLabel = new JLabel("\n");
        add(infoLabel, gbc);

        createButton.addActionListener(e -> {
            infoLabel.setForeground(Color.BLACK);
            infoLabel.setText("Запрос выполняется...");
            mainFrame.revalidate();
            String error = sqlExecutor.createTables();
            infoLabel.setForeground(Color.RED);
            infoLabel.setText(error);
        });

        dropButton.addActionListener(e -> {
            infoLabel.setForeground(Color.BLACK);
            infoLabel.setText("Запрос выполняется...");
            mainFrame.revalidate();
            String error = sqlExecutor.dropTables();
            infoLabel.setForeground(Color.RED);
            infoLabel.setText(error);
        });
    }

    public void start() {
        init();
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(this);
        update(getGraphics());
    }
}
