package main.gui;

import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class DescriptionCellRenderer extends DefaultTableCellRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3236289834078946531L;
	private String[] descriptions; 

    public DescriptionCellRenderer(String[] descriptions) {
        this.descriptions = descriptions;
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setText(descriptions[row]);
        return label;
    }
	

}
