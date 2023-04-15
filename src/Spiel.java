

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;


public class Spiel {
	private Spielfeld spieler;
	private Spielfeld computer;
	public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private String spielerName;
	 

	
	public static void main(String[] args) throws IOException {
		System.setProperty("log4j.configurationFile","./configuration.xml");
		Logger logger = LogManager.getRootLogger();
    	logger.trace("Configuration File Defined To Be :: "+System.getProperty("log4j.properties"));
		Spiel spiel = new Spiel();
		//Schiffseigenschaften intitialisiertsen
		//@TODO: Partameter haben z.Zt. keine Auswirkungen.
		Schiff[] schiffe = {
			Schiff.createSchlachtschiff(0, 0, false),
			Schiff.createKreuzer(0	,0,true),
			Schiff.createZerstoerer(0, 0, false),
			Schiff.createUboot(0, 0, false)
		};
		spiel.setSpielerName();
		spiel.createFields();
		spiel.spieler.gibSpielfeldUndBeschussfeldAus(spiel.computer, spiel.spielerName);
		spiel.spieler.schiffePositionierenSpieler(schiffe);
		spiel.computer.schiffePositionierenComputer(schiffe);
		
		//jetzt wirds spannend
		do {
			spiel.spielerZieht(spiel.computer,spiel.spieler);
			spiel.computerZieht(spiel.computer,spiel.spieler);
			spiel.spieler.gibSpielfeldAufKonsoleAus();
			spiel.computer.gibSpielfeldAufKonsoleAus();
			spiel.spieler.gibSpielfeldUndBeschussfeldAus(spiel.computer, "");
			
		} while(true);
		
		
	}
	
	
	private void setSpielerName() throws IOException {
		System.out.println("Schiffeversenken");
		System.out.print("Dein Name: ");
		spielerName = this.reader.readLine();
	};
	
	
	
	private void createFields() {
		this.spieler = new Spielfeld();
		this.computer = new Spielfeld();
	};
	
	private void spielerZieht(Spielfeld computer, Spielfeld spieler) {
		System.out.println("\nSpieler spielt");
        //Guess co-ordinates
        int x = -1, y = -1;
        do {
            try {
            	System.out.print("X: ");
				x = Integer.parseInt(Spiel.reader.readLine());
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
            	System.out.print("y: ");
				y = Integer.parseInt(Spiel.reader.readLine());
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            if ((x >= 0 && x < 10) && (y >= 0 && y < 10)) //valid guess
            {
            	computer.schiesse(x, y);
                
            }
        }while((x < 0 || x >= 9) || (y < 0 || y >= 9));  //keep re-prompting till valid guess
	};
	
	private void computerZieht(Spielfeld computer, Spielfeld spieler) {
		System.out.println("\nCOMPUTER spielt");
        //Guess co-ordinates
        int x = -1, y = -1;
        do {
            x = (int)(Math.random() * 9) %10;
            y = (int)(Math.random() * 9) %10;

            if ((x >= 0 && x < 10) && (y >= 0 && y < 10)) //valid guess
            {
            	spieler.schiesse(x, y);
                
            }
        }while((x < 0 || x >= 9) || (y < 0 || y >= 9));  //keep re-prompting till valid guess
	};
		
}
