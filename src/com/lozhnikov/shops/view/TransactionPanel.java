package com.lozhnikov.shops.view;

import com.lozhnikov.shops.SecretProperties;
import com.lozhnikov.shops.sql.SQLExecutor;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Callable;

public class TransactionPanel extends JPanel {
    private final JFrame mainFrame;
    private final SQLExecutor sqlExecutor;
    private JLabel infoLabel;
    private MenuPanel menuPanel;

    public TransactionPanel(JFrame mainFrame, SQLExecutor sqlExecutor, MenuPanel menuPanel) {
        this.mainFrame = mainFrame;
        this.sqlExecutor = sqlExecutor;
        this.menuPanel = menuPanel;
    }

    private void init() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(8, 8, 8, 8);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.add(new JLabel("ID заявки"), gbc);

        gbc.gridx++;
        JTextField requestField = new JTextField(20);
        inputPanel.add(requestField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(new JLabel("ID поставщика"), gbc);

        gbc.gridx++;
        JTextField providerField = new JTextField(20);
        inputPanel.add(providerField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(new JLabel("Наценка"), gbc);

        gbc.gridx++;
        JTextField coefficientField = new JTextField(20);
        inputPanel.add(coefficientField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(inputPanel, gbc);

        gbc.gridy++;
        JButton startTransactionButton = new JButton("Выполнить транзакцию");
        add(startTransactionButton, gbc);

        gbc.gridy++;
        JButton exitButton = new JButton("Вернуться в главное меню");
        add(exitButton, gbc);

        gbc.gridy++;
        infoLabel = new JLabel("\n");
        add(infoLabel, gbc);

        startTransactionButton.addActionListener(e -> {
            infoLabel.setForeground(Color.BLACK);
            infoLabel.setText("Транзакция выполняется...");
            mainFrame.revalidate();
            update(getGraphics());
            String error = "\n";
            try {
                error = sqlExecutor.runTransaction(Integer.parseInt(requestField.getText()),
                        Integer.parseInt(providerField.getText()),
                        Double.parseDouble(coefficientField.getText()));
            }
            catch (NumberFormatException ex) {
                error = ex.getMessage();
            }
            if (error.isEmpty()) {
                infoLabel.setForeground(Color.BLACK);
                infoLabel.setText("Транзакция выполнена");
            }
            else {
                infoLabel.setForeground(Color.RED);
                infoLabel.setText(error);
            }
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
        menuPanel.start();
    }
}
