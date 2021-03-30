package com.lozhnikov.shops.view;

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
    private final ChooseGoalType chooseGoalType;

    public ChooseTablePanel(JFrame mainFrame, MenuPanel menuPanel, SQLExecutor sqlExecutor,
                            ChooseGoalType chooseGoalType) {
        this.mainFrame = mainFrame;
        this.menuPanel = menuPanel;
        this.sqlExecutor = sqlExecutor;
        this.chooseGoalType = chooseGoalType;
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
            JButton tableButton = new JButton(table.getTranslate());
            tables.add(tableButton, gbc);

            tableButton.addActionListener(e -> {
                switch (chooseGoalType) {
                    case FILL:
                        AddRowPanel addRowPanel = new AddRowPanel(mainFrame, this,
                                sqlExecutor, table);
                        addRowPanel.start();
                        break;
                    case VIEW:
                        try {
                            infoLabel.setForeground(Color.BLACK);
                            infoLabel.setText("Запрос выполняется...");
                            mainFrame.revalidate();
                            update(getGraphics());
                            ResultSet resultSet = sqlExecutor.getAllTableValues(table);
                            ViewTablePanel viewTablePanel = new ViewTablePanel(mainFrame,
                                    this, resultSet);
                            viewTablePanel.start();
                        }
                        catch (SQLException ex) {
                            infoLabel.setForeground(Color.RED);
                            infoLabel.setText(ex.getMessage());
                        }
                        break;
                }
            });

            gbc.gridx++;
            if (gbc.gridx == 3) {
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
