package app.Calendar;

import javax.swing.table.DefaultTableModel;

public class ImmutableTableModel extends DefaultTableModel {
    public ImmutableTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}