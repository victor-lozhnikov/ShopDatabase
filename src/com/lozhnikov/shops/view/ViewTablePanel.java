package com.lozhnikov.shops.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class ViewTablePanel extends JPanel {
    private final JFrame mainFrame;
    private final ChooseTablePanel chooseTablePanel;
    private final ResultSet resultSet;

    public ViewTablePanel(JFrame mainFrame, ChooseTablePanel chooseTablePanel, ResultSet resultSet) {
        this.mainFrame = mainFrame;
        this.chooseTablePanel = chooseTablePanel;
        this.resultSet = resultSet;
    }

    private DefaultTableModel buildTableModel() throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();

        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; ++i) {
            columnNames.add(metaData.getColumnName(i));
        }

        Vector<Vector<Object>> data = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> row = new Vector<>();
            for (int i = 1; i <= columnCount; ++i) {
                row.add(resultSet.getObject(i));
            }
            data.add(row);
        }

        return new DefaultTableModel(data, columnNames);
    }

    private void init() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(8, 8, 8, 8);

        try {
            JTable table = new JTable(buildTableModel());
            table.setPreferredScrollableViewportSize(table.getPreferredSize());
            table.setEnabled(false);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, gbc);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

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
