package com.lozhnikov.shops.view;

import com.lozhnikov.shops.entities.Field;
import com.lozhnikov.shops.entities.Row;
import com.lozhnikov.shops.entities.Table;
import com.lozhnikov.shops.entities.Value;
import com.lozhnikov.shops.sql.SQLExecutor;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddRowPanel extends JPanel {
    private final JFrame mainFrame;
    private final ChooseTablePanel chooseTablePanel;
    private final SQLExecutor sqlExecutor;
    private final Table table;

    public AddRowPanel(JFrame mainFrame, ChooseTablePanel chooseTablePanel,
                       SQLExecutor sqlExecutor, Table table) {
        this.mainFrame = mainFrame;
        this.chooseTablePanel = chooseTablePanel;
        this.sqlExecutor = sqlExecutor;
        this.table = table;
    }

    private void init() {
        setLayout(new GridBagLayout());

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(8, 8, 8, 8);

        java.util.List<JTextField> textFields = new ArrayList<>();

        for (Field field : table.getFields()) {
            gbc.gridx = 0;
            fieldsPanel.add(new JLabel(field.getTranslate() + (field.isNotNull() ? "*" : "")), gbc);
            gbc.gridx++;
            JTextField textField = new JTextField(20);
            textFields.add(textField);
            fieldsPanel.add(textField, gbc);
            gbc.gridy++;
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(fieldsPanel, gbc);
        JLabel infoLabel = new JLabel("\n");

        gbc.gridy++;
        JButton addButton = new JButton("Добавить");
        add(addButton, gbc);
        addButton.addActionListener(e -> {
            infoLabel.setForeground(Color.BLACK);
            infoLabel.setText("Запрос выполняется...");
            mainFrame.revalidate();
            update(getGraphics());
            Row row = new Row();
            int i = 0;
            for (Field field : table.getFields()) {
                String fieldText = textFields.get(i).getText();
                if (fieldText.isEmpty()) {
                    continue;
                }
                row.add(new Value(field.getName(), fieldText));
                i++;
            }
            String error = sqlExecutor.insertValue(table, row);
            if (error.isEmpty()) {
                infoLabel.setText("Данные добавлены");
            }
            else {
                infoLabel.setForeground(Color.RED);
                infoLabel.setText(error);
            }
        });

        gbc.gridy++;
        JButton addAllButton = new JButton("Добавить данные по умолчанию");
        add(addAllButton, gbc);
        addAllButton.addActionListener(e -> {
            infoLabel.setForeground(Color.BLACK);
            infoLabel.setText("Запрос выполняется...");
            mainFrame.revalidate();
            update(getGraphics());
            String error = sqlExecutor.insertValues(table);
            if (error.isEmpty()) {
                infoLabel.setText("Данные добавлены");
            }
            else {
                infoLabel.setForeground(Color.RED);
                infoLabel.setText(error);
            }
        });

        gbc.gridy++;
        add(infoLabel, gbc);

        gbc.gridy++;
        JButton closeButton = new JButton("Вернуться к выбору таблицы");
        add(closeButton, gbc);
        closeButton.addActionListener(e -> {
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

    private void close() {
        chooseTablePanel.start();
    }
}
