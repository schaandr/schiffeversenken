package main.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DbCheck {
	Logger logger = LogManager.getLogger(DbCheck.class);
	
	public void checkDatabase() {
		DbConnect db = DbConnect.getInstance();
		ResultSet dbmd = null;
		try {
			dbmd = db.getConnection().getMetaData().getTables(null, null, null, null);
			System.out.println(countRows(dbmd));
			if(countRows(dbmd)!=2) {
				System.out.println("Zwei Tabellen werden benötigt.");
			}
			System.out.println("Folgende Tabellen sind vorhanden:");
			while(dbmd.next()) {
				System.out.println(dbmd.getString(3)+" - " + (((dbmd.getString(3).equalsIgnoreCase("Spiel")) || (dbmd.getString(3).equalsIgnoreCase("Spielzug"))) ? "richtiger Name":"falscher Name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void checkDatabaseSQL() {
		DbConnect db = DbConnect.getInstance();
		ResultSet dbmd = null;
		try {
			dbmd = db.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES");
			dbmd.last();
			System.out.print(dbmd.getRow());
			dbmd.beforeFirst();
			while(dbmd.next()) {
				for(int i=1;i<=dbmd.getMetaData().getColumnCount();i++) {
					System.out.print(dbmd.getString(i)+"\t\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		System.setProperty("log4j.configurationFile","./configuration.xml");
		Logger logger = LogManager.getRootLogger();
    	logger.trace("Configuration File Defined To Be :: "+System.getProperty("log4j.properties"));
		DbConnect dbc = DbConnect.getInstance();
		DbCheck db = new DbCheck();
		db.checkDatabase();
		ResultSet rs;
		try {
			rs = dbc.getConnection().createStatement().executeQuery("SELECT * FROM SPielzug");
			try {
				while(rs.next()) {
					for(int i=1; i<rs.getMetaData().getColumnCount();i++) {
						System.out.println(rs.getBoolean(i)+"\t\t");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//db.checkDatabaseSQL();
		//db.checkTableSpiel();
		//db.checkTableSpielzug(dbc);
		try {
			dbc.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unused")
	private void checkTableSpielzug(DbConnect db){		
		int correctnames=0;
		//Welche Namen der Spalten werden erwartet und stimmt der Datentyp?
		String[] expectednames = {"SpielzugNr","SpielNr","SpielfeldSpieler","SpielfeldComputer"};
		try {
			ResultSet rs = db.getConnection().getMetaData().getColumns(null, null, "Spielzug", null);
			if(countRows(rs)!=4) {
					System.out.println("Es müssen 4 Spalten vorhanden sein.");
				} else {
				while(rs.next()) {
					if(rs.getString(4).equalsIgnoreCase(expectednames[0]) || rs.getString(4).equalsIgnoreCase(expectednames[1]) || rs.getString(4).equalsIgnoreCase(expectednames[2]) || rs.getString(4).equalsIgnoreCase(expectednames[3])) {
						System.out.println("Name ist richtig");
						//if(rs.getString(4).equalsIgnoreCase("") && (rs.getString(5)== Types.INTEGER))
					}
				}
				if(correctnames!=4) {
					System.out.println("Namen sind nicht korrekt"); 
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Primärschlüssel prüfen
		try {
			HashMap<String, Boolean> primaryKeys = new HashMap<String, Boolean>();			
			ResultSet rs = db.getConnection().getMetaData().getPrimaryKeys(null,null,"Spielzug");
			while(rs.next()) {
				primaryKeys.put(rs.getString(4),Boolean.TRUE);
			}
			if(primaryKeys.get("SPIELNR")!=null && primaryKeys.get("SPIELFELDNR")!=null && (primaryKeys.get("SPIELNR").booleanValue()) && (primaryKeys.get("SPIELFELDNR").booleanValue())) {
				
			} else {
				System.out.println("Primärschlüssel nicht korrekt. Es müssen zwei Spalten sein und die Richtigen :P");
			}
		} catch(SQLException e) {
			
		}
	}

	@SuppressWarnings("unused")
	private void checkTableSpiel(){
		
		
	}

	private int countRows(ResultSet rs)  throws SQLException{
		int rowcount = 0;
		if (rs.last()) {
		  rowcount = rs.getRow();
		  rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
		}
		return rowcount;
	}
}
