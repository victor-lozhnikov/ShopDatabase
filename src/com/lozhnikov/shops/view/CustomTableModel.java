package com.lozhnikov.shops.view;

import com.lozhnikov.shops.SecretProperties;
import com.lozhnikov.shops.entities.Row;
import com.lozhnikov.shops.entities.Table;
import com.lozhnikov.shops.entities.Value;
import com.lozhnikov.shops.sql.SQLExecutor;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class CustomTableModel extends AbstractTableModel {

    private final Table table;
    private final Vector <String> columnNames;
    private final Vector <Vector<Object>> rows;
    private final ResultSetMetaData metaData;
    private final SQLExecutor sqlExecutor;
    private final ViewTablePanel viewTablePanel;

    public CustomTableModel(ResultSet resultSet, Table table,
                            SQLExecutor sqlExecutor,
                            ViewTablePanel viewTablePanel) throws SQLException {
        this.table = table;
        this.sqlExecutor = sqlExecutor;
        this.viewTablePanel = viewTablePanel;
        metaData = resultSet.getMetaData();

        columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int i = 0; i < columnCount; ++i) {
            columnNames.add(table.getFields().get(i).getTranslate());
        }

        rows = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> row = new Vector<>();
            for (int i = 1; i <= columnCount; ++i) {
                row.add(resultSet.getObject(i));
            }
            rows.add(row);
        }
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows.get(rowIndex).get(columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        viewTablePanel.updateInfoLabel("Значение в таблице обновляется...", false);
        Row row = new Row();
        for (int i = 0; i < columnNames.size(); ++i) {
            row.add(new Value(table.findFieldByName(columnNames.get(i)), rows.get(rowIndex).get(i)));
        }
        try {
            sqlExecutor.updateValue(table,
                    new Value(table.findFieldByName(columnNames.get(columnIndex)), aValue),
                    row);
            rows.get(rowIndex).set(columnIndex, aValue);
            super.setValueAt(aValue, rowIndex, columnIndex);
            viewTablePanel.updateInfoLabel("Значение в таблице обновлено", false);
        }
        catch (SQLException ex) {
            String error = ex.getMessage();
            if (error.length() > SecretProperties.MAX_ERROR_LENGTH) {
                error = error.substring(0, SecretProperties.MAX_ERROR_LENGTH) + "...";
            }
            viewTablePanel.updateInfoLabel(error, true);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        try {
            return metaData.isWritable(columnIndex + 1);
        }
        catch (SQLException ex) {
            return false;
        }
    }

    public void removeRow(int rowIndex) {
        rows.remove(rowIndex);
        super.fireTableRowsDeleted(rowIndex, rowIndex);
        super.fireTableDataChanged();
    }
}
