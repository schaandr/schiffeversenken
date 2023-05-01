package main.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.spiel.Einstellungen;

public class DbConnect {
	private static DbConnect dbconnection = null;
	Logger logger = LogManager.getLogger(DbConnect.class);
	private Connection con = null;
	Einstellungen einstellungen = Einstellungen.getInstance();
	public Connection getConnection() {
		return con;
	}

	private DbConnect() {
		logger.info("Datenbankverbindung erstellen");
		try {
	         //Registering the HSQLDB JDBC driver
	         Class.forName("org.hsqldb.jdbcDriver");
	         String conString = "jdbc:hsqldb:file:"+einstellungen.getProperty("databasepath")+File.separator+einstellungen.getProperty("databasename")+";shutdown=true";
	         //Creating the connection with HSQLDB
	         logger.info(conString);
	         con = DriverManager.getConnection(conString,"SA","");
	         
	         if (con!= null){
	            logger.info("Verbindung zur Datenbank hergestellt");
	            
	         }else{
	           logger.error("Prooblem beim Herstellen der Verbindung");
	         }
	      
	      }  catch (Exception e) {
	         e.printStackTrace(System.out);
	      }
	}
	
	public static DbConnect getInstance() {
		if(dbconnection==null) {
			dbconnection = new DbConnect();			
		} 
		return dbconnection;
		
	}
	
	
}
