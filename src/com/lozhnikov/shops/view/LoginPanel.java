package com.lozhnikov.shops.view;

import com.lozhnikov.shops.SecretProperties;
import com.lozhnikov.shops.sql.SQLExecutor;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginPanel extends JPanel {
    private final JFrame mainFrame;
    private JLabel infoLabel;

    public LoginPanel(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    private void init() {
        setLayout(new GridBagLayout());

        JPanel fields = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(8, 8, 8, 8);

        fields.add(new JLabel("URL"), gbc);
        gbc.gridy++;
        fields.add(new JLabel("Логин"), gbc);
        gbc.gridy++;
        fields.add(new JLabel("Пароль"), gbc);

        gbc.gridy = 0;
        gbc.gridx++;

        JTextField urlField = new JTextField(20);
        fields.add(urlField, gbc);
        gbc.gridy++;
        JTextField loginField = new JTextField(20);
        fields.add(loginField, gbc);
        gbc.gridy++;
        JPasswordField passwordField = new JPasswordField(20);
        fields.add(passwordField, gbc);

        add(fields);
        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton loginButton = new JButton("Войти");
        add(loginButton, gbc);
        gbc.gridy++;
        add(new JLabel("Выбрать роль:"), gbc);

        JPanel defaultButtons = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton adminButton = new JButton("Администратор");
        defaultButtons.add(adminButton, gbc);
        gbc.gridx++;
        JButton managerButton = new JButton("Менеджер");
        defaultButtons.add(managerButton, gbc);
        gbc.gridx++;
        JButton buyerButton = new JButton("Покупатель");
        defaultButtons.add(buyerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(defaultButtons, gbc);

        gbc.gridy++;
        JLabel infoLabel = new JLabel("\n");
        add(infoLabel, gbc);

        loginButton.addActionListener(e -> {
            infoLabel.setForeground(Color.BLACK);
            infoLabel.setText("Идет подключение...");
            mainFrame.revalidate();
            update(getGraphics());
            try {
                SQLExecutor sqlExecutor = new SQLExecutor(
                        urlField.getText(), loginField.getText(), passwordField.getText());
                MenuPanel menuPanel = new MenuPanel(mainFrame, sqlExecutor, this);
                menuPanel.start();
            }
            catch (ClassNotFoundException | SQLException ex) {
                String error = ex.getMessage();
                if (error.length() > SecretProperties.MAX_ERROR_LENGTH) {
                    error = error.substring(0, SecretProperties.MAX_ERROR_LENGTH) + "...";
                }
                infoLabel.setForeground(Color.RED);
                infoLabel.setText(error);
            }
        });

        adminButton.addActionListener(e -> {
            urlField.setText(SecretProperties.DB_URL);
            loginField.setText(SecretProperties.DB_ADMIN_LOGIN);
            passwordField.setText(SecretProperties.DB_ADMIN_PASSWORD);
        });

        managerButton.addActionListener(e -> {
            urlField.setText(SecretProperties.DB_URL);
            loginField.setText(SecretProperties.DB_MANAGER_LOGIN);
            passwordField.setText(SecretProperties.DB_MANAGER_PASSWORD);
        });

        buyerButton.addActionListener(e -> {
            urlField.setText(SecretProperties.DB_URL);
            loginField.setText(SecretProperties.DB_BUYER_LOGIN);
            passwordField.setText(SecretProperties.DB_BUYER_PASSWORD);
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
}
