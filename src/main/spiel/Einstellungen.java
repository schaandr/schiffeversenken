package main.spiel;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Einstellungen extends Properties{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8595405807173372563L;
	private static final Einstellungen obj = new Einstellungen(); 
	
	
	private Einstellungen() {
		try (InputStream inStream = ClassLoader.getSystemResourceAsStream("einstellungen.properties")) {
			  if (inStream != null) {
			    this.load(inStream);
			  }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	
	public static Einstellungen getInstance() {
		return obj;
	}
	
}
