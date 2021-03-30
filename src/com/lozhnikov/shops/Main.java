package com.lozhnikov.shops;

import com.lozhnikov.shops.view.LoginPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Shop Database App");
        mainFrame.setBounds(100, 100, 640, 640);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LoginPanel loginPanel = new LoginPanel(mainFrame);
        loginPanel.start();
        mainFrame.setVisible(true);
    }
}
