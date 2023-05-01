package main.database;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author andsc
 * Diese Klasse exportiert die Datenbank aus eine ODB-Datei ins Dateisystem.
 */

public class DatabaseImportExport {
	File zipFilePath;				//ODB-Datei
	File outputDirectory;			//Ausgabeverzeichnis
	String outputFilenamePrefix;	//Datenbankname
	File tmp;						//Tempverzeichnis
	Logger logger = LogManager.getLogger(DatabaseImportExport.class);
	
	//Konstruktor
	public DatabaseImportExport(String zipFile, String outputPrefix, String outputDirectory) throws IOException {
		this.tmp = Files.createTempDirectory("Schiffeversenken").toFile();
		this.zipFilePath = new File(zipFile);
		this.outputDirectory = new File(outputDirectory);
		this.outputFilenamePrefix = outputPrefix;
		
	}
	
	//main zum Testen
	public static void main(String[] args) {
		//
		DatabaseImportExport die=null;
		
		try {
			die = new DatabaseImportExport("save.odb","schiffeversenken.","database");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String regex ="(database/)(\\w+)";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(entry.getName());
		
		try {
			die.importDatabase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 

	}
	
	/**
	 * importiert die Datenbank aus einer ODB-Datei
	 * @throws IOException
	 */
	public void importDatabase() throws IOException {
		logger.info("Extrahieren Datenbank...");
		this.extractDatabase();
		logger.info("Verschiebe Dateien aus temp. Ordner...");
		this.moveFiles(outputDirectory);
		logger.info("Dateien umbennen...");
		this.renameFiles();
		logger.info("Import abgeschlossen");
	}
	
	
	/**
	 * Verschiebt die Dateien an den endültigen Zielort. 
	 * @param destination
	 * @throws IOException
	 */
	private void moveFiles(File destination) throws IOException {
		Collection<File> databaseFolder = FileUtils.listFiles(tmp, new NameFileFilter("database"), null);
		Collection<File> dbFiles = FileUtils.listFiles(databaseFolder.iterator().next(), TrueFileFilter.TRUE, FalseFileFilter.FALSE);
		dbFiles.iterator().forEachRemaining(f->{
			try {
				FileUtils.moveToDirectory(f, destination, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		FileUtils.deleteDirectory(this.tmp);
		
	}
	
	/**
	 * Die Dateien aus den ODB-Datei müssen umbenannt werden. Der Prefix ist der DB-Name. 
	 */
	private void renameFiles() {
		Collection<File> dbFiles = FileUtils.listFiles(this.outputDirectory, TrueFileFilter.TRUE, FalseFileFilter.FALSE);
		dbFiles.iterator().forEachRemaining(f->{
			File renamedFile = new File(outputDirectory.getName()+File.separator+this.outputFilenamePrefix+f.getName());
			System.out.println(renamedFile.toString());
			f.renameTo(renamedFile);
		});
		
	}
	
			
		/**
		 * ODB-Datei entpacken. Es handelt sich im Prinzip um eine einfache Zip-Datei. 
		 */
		private void extractDatabase() {
			byte[] buffer = new byte[1024];
			
			try {
				//Zip-Datei lesen
				// Erstellt ein ZipInputStream-Objekt, um die Zip-Datei zu lesen
	            ZipInputStream zipIn = new ZipInputStream(new FileInputStream(this.zipFilePath));
	            ZipEntry entry;
				while((entry = zipIn.getNextEntry()) !=null) {
					System.out.println(entry.getName());
					String filePath = tmp.getAbsolutePath()+File.separator+entry.getName();
		                if (!entry.isDirectory()) {
		                    // Erstellt alle benötigten Verzeichnisse
		                    new File(filePath).getParentFile().mkdirs();

		                    // Erstellt eine FileOutputStream-Instanz, um die Datei zu schreiben
		                    FileOutputStream fos = new FileOutputStream(filePath);

		                    // Liest die Daten aus dem ZipInputStream und schreibt sie in die Datei
		                    int len;
		                    while ((len = zipIn.read(buffer)) > 0) {
		                        fos.write(buffer, 0, len);
		                    }

		                    // Schließt die FileOutputStream
		                    fos.close();
		                } else {
		                    // Wenn die Eintragung ein Verzeichnis ist, erstellt es das Verzeichnis
		                	new File(filePath).mkdirs();
		                    
		                }
		                zipIn.closeEntry();
		            }

		            // Schließt den ZipInputStream
		            zipIn.close();

		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		}
		
		public  void databaseToOdbFile() {
			
		}
		
	}


