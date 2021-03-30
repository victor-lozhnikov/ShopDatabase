package com.lozhnikov.shops.view;

import com.lozhnikov.shops.sql.SQLExecutor;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Callable;

public class MenuPanel extends JPanel {
    private final JFrame mainFrame;
    private final SQLExecutor sqlExecutor;
    private JLabel infoLabel;

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
        JButton insertButton = new JButton("Заполнить таблицы");
        add(insertButton, gbc);

        gbc.gridy++;
        JButton viewButton = new JButton("Просмотреть таблицы");
        add(viewButton, gbc);

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
            ChooseTablePanel chooseTablePanel = new ChooseTablePanel(mainFrame, this,
                    sqlExecutor, ChooseGoalType.FILL);
            chooseTablePanel.start();
        });
        viewButton.addActionListener(e -> {
            ChooseTablePanel chooseTablePanel = new ChooseTablePanel(mainFrame, this,
                    sqlExecutor, ChooseGoalType.VIEW);
            chooseTablePanel.start();
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
}
