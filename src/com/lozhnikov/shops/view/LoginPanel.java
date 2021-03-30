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
        JButton defaultLoginButton = new JButton("Использовать данные по умолчанию");
        add(defaultLoginButton, gbc);

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
                MenuPanel menuPanel = new MenuPanel(mainFrame, sqlExecutor);
                menuPanel.start();
            }
            catch (ClassNotFoundException | SQLException ex) {
                infoLabel.setForeground(Color.RED);
                infoLabel.setText(ex.getMessage());
            }
        });

        defaultLoginButton.addActionListener(e -> {
            urlField.setText(SecretProperties.DB_URL);
            loginField.setText(SecretProperties.DB_USER_LOGIN);
            passwordField.setText(SecretProperties.DB_USER_PASSWORD);
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
