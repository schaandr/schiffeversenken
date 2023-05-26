package main.gui;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import main.spiel.Gebiet;


public class Spielfeld extends JTable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5489394602647162613L;
	private int rows;
	private int cols;
	private final String[] DESC = {"1","2","3","4","5","6","7","8","9","10"};
	
	public Spielfeld() {
		
	}

	public TableCellRenderer getCellRenderer(int row, int column) {		
		if(column==0) {
			return new DescriptionCellRenderer(DESC);			
		}
		
		return new DefaultTableCellRenderer();

	}
	
	public Gebiet[][] initSpielfeld() {
		Gebiet[][] feld=new Gebiet[10][10]; 
		// Anfangswerte setzen für alle 100 Felder
		   for (int i=0;i<10;i++){ // senkrecht
		     for (int j=0;j<10;j++){ //waagerecht
		       feld[j][i]=new Gebiet();
		     }
		   }
		  return feld;
	}
	
}
