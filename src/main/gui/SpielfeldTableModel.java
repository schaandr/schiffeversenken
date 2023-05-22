package main.gui;

import javax.swing.table.AbstractTableModel;

import main.spiel.Gebiet;

public class SpielfeldTableModel extends AbstractTableModel {
		private String[] columnNames = {"A","B","C","D","E","F","G","H","I","J"};
		private Object[][] data;	
	
		private static final long serialVersionUID = 1L;
		
		public int getColumnCount() {
	        return columnNames.length;
	    }

	    public int getRowCount() {
	        return data.length;
	    }

	    public String getColumnName(int col) {
	        return columnNames[col];
	    }

	    public Object getValueAt(int row, int col) {
	        return data[row][col];
	    }
	    
	    public Class getColumnClass(int row, int col) {
	    	return getValueAt(row, col).getClass();
	    }

	    /*
	     * Don't need to implement this method unless your table's
	     * editable.
	     */
	    public boolean isCellEditable(int row, int col) {
	        //Note that the data/cell address is constant,
	        //no matter where the cell appears onscreen.
	        if (col < 2) {
	            return false;
	        } else {
	            return true;
	        }
	    }

	    /*
	     * Don't need to implement this method unless your table's
	     * data can change.
	     */
	    public void setValueAt(Gebiet value, int row, int col) {
	        data[row][col] = value;
	        fireTableCellUpdated(row, col);
	    }
}

