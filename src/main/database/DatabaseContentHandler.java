package main.database;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DatabaseContentHandler extends DefaultHandler{
	int indent=0;

	// Wird aufgerufen, wenn ein Startelement gefunden wird
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("Start-Element: " + qName);
        
        // Verarbeiten Sie hier die Attribute des Elements, falls erforderlich
        if (attributes.getLength() > 0) {
            System.out.println(String.  "Attribute:");
            for (int i = 0; i < attributes.getLength(); i++) {
                String attributeName = attributes.getQName(i);
                String attributeValue = attributes.getValue(i);
                System.out.println(attributeName + " = " + attributeValue);
            }
        }
        indent++;
    }

    // Wird aufgerufen, wenn ein Endelement gefunden wird
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("End-Element: " + qName);
        indent--;
    }

    // Wird aufgerufen, wenn der Text innerhalb eines Elements gefunden wird
    public void characters(char[] ch, int start, int length) throws SAXException {
        String text = new String(ch, start, length).trim();
        if (!text.isEmpty()) {
            System.out.println("Text: " + text);
        }
    }
    
    private String repeatString(String str, int times) {
    	for(int)
    };
}

