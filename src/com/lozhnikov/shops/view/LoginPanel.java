package com.lozhnikov.shops.view;

import com.lozhnikov.shops.sql.SQLExecutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginPanel extends JPanel {
    private final JFrame mainFrame;

    public LoginPanel(JFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());

        JPanel content = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(8, 8, 8, 8);

        content.add(new JLabel("URL"), gbc);
        gbc.gridy++;
        content.add(new JLabel("Login"), gbc);
        gbc.gridy++;
        content.add(new JLabel("Password"), gbc);

        gbc.gridy = 0;
        gbc.gridx++;

        JTextField urlField = new JTextField(20);
        content.add(urlField, gbc);
        gbc.gridy++;
        JTextField loginField = new JTextField(20);
        content.add(loginField, gbc);
        gbc.gridy++;
        JTextField passwordField = new JTextField(20);
        content.add(passwordField, gbc);

        add(content);
        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton loginButton = new JButton("Войти");
        add(loginButton, gbc);
        gbc.gridy++;
        JButton defaultLoginButton = new JButton("Использовать данные по умолчанию");
        add(defaultLoginButton, gbc);

        gbc.gridy++;
        JLabel errorLabel = new JLabel("");
        add(errorLabel, gbc);

        loginButton.addActionListener(e -> {
            try {
                SQLExecutor sqlExecutor = new SQLExecutor(
                        urlField.getText(), loginField.getText(), passwordField.getText());
            }
            catch (ClassNotFoundException | SQLException ex) {
                errorLabel.setText(ex.getMessage());
            }
        });

        defaultLoginButton.addActionListener(e -> {
            try {
                SQLExecutor sqlExecutor = new SQLExecutor();
            }
            catch (ClassNotFoundException | SQLException ex) {
                errorLabel.setText(ex.getMessage());
            }
        });

        mainFrame.add(this);
        mainFrame.setVisible(true);
    }
}
