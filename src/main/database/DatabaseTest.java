package main.database;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.spiel.Einstellungen;

public class DatabaseTest {

	public static void main(String[] args) {
		System.setProperty("log4j.configurationFile","./configuration.xml");
		Logger logger = LogManager.getRootLogger();
    	logger.trace("Configuration File Defined To Be :: "+System.getProperty("log4j.properties"));
		
    	Einstellungen einstellungen = Einstellungen.getInstance();
    	
    	//Verbindung zur Datenbank
    	DatabaseImportExport die = null;
    	try {
			 die = new DatabaseImportExport("save.odb","schiffeversenken.","database");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			die.importDatabase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	DbCheck dbcheck = new DbCheck(); 	 	
    	dbcheck.checkDatabase();

	}

}
