package main.database;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class XMLParser {
	

	public static void main(String[] args) {
        try {
            // Erzeugen Sie eine SAXParserFactory-Instanz
            SAXParserFactory factory = SAXParserFactory.newInstance();

            // Erzeugen Sie einen SAXParser
            SAXParser parser = factory.newSAXParser();

            // Geben Sie den Pfad zur XML-Datei an
            File file = new File("database/content.xml");

            // Erstellen Sie einen benutzerdefinierten Handler, um die XML-Daten zu verarbeiten
            DatabaseContentHandler handler = new DatabaseContentHandler();

            // Parsen Sie die XML-Datei mit dem Handler
            parser.parse(file, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
		
}
