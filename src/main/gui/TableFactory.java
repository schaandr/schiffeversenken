package main.gui;
import javax.swing.JTable;

public final class TableFactory {
	/**
	 * @wbp.factory
	 */
	public static JTable createJTable() {
		JTable table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setSize(10, 10);
		
		return table;
	}
}