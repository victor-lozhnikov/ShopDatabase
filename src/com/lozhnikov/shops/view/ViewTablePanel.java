package com.lozhnikov.shops.view;

import com.lozhnikov.shops.entities.Row;
import com.lozhnikov.shops.entities.Table;
import com.lozhnikov.shops.entities.Value;
import com.lozhnikov.shops.sql.SQLExecutor;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class ViewTablePanel extends JPanel {
    private final JFrame mainFrame;
    private final ChooseTablePanel chooseTablePanel;
    private final Table table;
    private final SQLExecutor sqlExecutor;
    private ResultSet resultSet;
    private JTable jTable;
    private JScrollPane jScrollPane;
    private JLabel infoLabel;

    public ViewTablePanel(JFrame mainFrame, ChooseTablePanel chooseTablePanel,
                          Table table, SQLExecutor sqlExecutor) {
        this.mainFrame = mainFrame;
        this.chooseTablePanel = chooseTablePanel;
        this.table = table;
        this.sqlExecutor = sqlExecutor;
    }

    private void init() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(8, 8, 8, 8);

        add(new JLabel(table.getTranslate()), gbc);
        gbc.gridy++;

        jTable = new JTable();
        jTable.setEnabled(false);
        jScrollPane = new JScrollPane(jTable);
        add(jScrollPane, gbc);

        JPanel editButtons = new JPanel(new GridBagLayout());
        GridBagConstraints gbcButtons = new GridBagConstraints();
        gbcButtons.gridx = 0;
        gbcButtons.gridy = 0;

        JButton addRowButton = new JButton("Добавить новый ряд");
        addRowButton.addActionListener(e -> {
            AddRowPanel addRowPanel = new AddRowPanel(mainFrame, this, sqlExecutor, table);
            addRowPanel.start();
        });
        editButtons.add(addRowButton, gbcButtons);

        gbcButtons.gridx++;
        JButton deleteRowButton = new JButton("Удалить выделенный ряд");
        deleteRowButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) {
                updateInfoLabel("Ряд не выбран", true);
            }
            else {
                Row row = new Row();
                for (int i = 0; i < jTable.getColumnCount(); ++i) {
                    row.add(new Value(table.findFieldByName(jTable.getColumnName(i)),
                            jTable.getModel().getValueAt(jTable.getSelectedRow(), i)));
                }
                String error = sqlExecutor.deleteRow(table, row);
                if (!error.isEmpty()) {
                    updateInfoLabel(error, true);
                }
                else {
                    updateInfoLabel("Ряд удален", false);
                    ((CustomTableModel)jTable.getModel()).removeRow(jTable.getSelectedRow());
                }
            }
        });
        editButtons.add(deleteRowButton, gbcButtons);

        gbc.gridy++;
        add(editButtons, gbc);

        gbc.gridy++;
        infoLabel = new JLabel("\n");
        add(infoLabel, gbc);

        gbc.gridy++;
        JButton closeButton = new JButton("Вернуться к выбору таблицы");
        add(closeButton, gbc);
        closeButton.addActionListener(e -> {
            close();
        });

        try {
            infoLabel.setForeground(Color.BLACK);
            infoLabel.setText("Получение данных таблицы...");
            mainFrame.revalidate();
            resultSet = sqlExecutor.getAllTableValues(table);
            jTable = new JTable(new CustomTableModel(resultSet, table, sqlExecutor, this));
            jTable.setAutoCreateRowSorter(true);
            jScrollPane.getViewport().removeAll();
            jScrollPane.getViewport().add(jTable);
            infoLabel.setText("\n");
        }
        catch (SQLException ex) {
            infoLabel.setForeground(Color.RED);
            infoLabel.setText(ex.getMessage());
        }
    }

    public void updateInfoLabel(String labelText, boolean isError) {
        if (isError) {
            infoLabel.setForeground(Color.RED);
        }
        else {
            infoLabel.setForeground(Color.BLACK);
        }
        infoLabel.setText(labelText);
        if (!isError) {
            mainFrame.revalidate();
            update(getGraphics());
        }
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
