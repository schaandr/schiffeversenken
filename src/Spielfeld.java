

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Spielfeld
{
	Logger logger = LogManager.getLogger(Spielfeld.class);
	// Eigenschaften
   private Gebiet[][] feld;

    // Konstruktor
	public Spielfeld()
	{
		
	   feld = new Gebiet[10][10];
	   // Anfangswerte setzen für alle 100 Felder
	   for (int i=0;i<10;i++){ // senkrecht
	     for (int j=0;j<10;j++){ //waagerecht
	       feld[j][i]=new Gebiet();
	     }
	   }
	}

	// Methoden
	public String schiesse(int x, int y){
	   if (feld[x][y].beschiesseFeld()) return "Treffer";
	   else return "Daneben";
	}
	
	public void gibSpielfeldAufKonsoleAus(){
	   System.out.println("\n  0123456789"); // Leerzeile + Beschriftung
	   for (int i=0;i<10;i++){
	     System.out.print(i+" ");
	     for (int j=0;j<10;j++){
	       if (feld[j][i].isFeldWurdeBeschossen()) System.out.print("*");
	       else {
	          if (feld[j][i].isSchiff()) System.out.print("X");
	          else System.out.print("-");
	       }
	     }
	     System.out.println(); // Zeilenwechsel
	   }	      
	}
	
	protected Gebiet[][] getFeld() {
		return feld;
	}
	
	//Spielfeld ausgeben und die Felde die beschossen wurden ausgeben. 
	public void gibSpielfeldUndBeschussfeldAus(Spielfeld beschossen, String spieler) {
		System.out.println("\n  "+spieler.substring(0, Math.min(spieler.length(), 9))+ " \t\t\t Computer" );
		System.out.println("\n  0123456789 \t\t\t 0123456789"); // Leerzeile + Beschriftung
		   for (int i=0;i<10;i++){
		     System.out.print(i+" ");
		     for (int j=0;j<10;j++){
		       if (feld[j][i].isFeldWurdeBeschossen()) System.out.print("*");
		       else {
		          if (feld[j][i].isSchiff()) System.out.print("X");
		          else System.out.print("-");
		       }
		     }
		     System.out.print("\t\t\t");
		     System.out.print(i+" ");
		     for (int j=0;j<10;j++){
		       if ((beschossen.getFeld()[j][i]).isFeldWurdeBeschossen() && !beschossen.getFeld()[j][i].isSchiff()) {
		    	   System.out.print("*");
		       }
		       else if((beschossen.getFeld()[j][i]).isFeldWurdeBeschossen() && beschossen.getFeld()[j][i].isSchiff()){
		          System.out.print("X");
		       }
		       else {
		    	   System.out.print("-");
		       }
		     }
		     
		     System.out.println(); // Zeilenwechsel
		   }
	}
	
	//Schiffe positionieren.
	public void schiffePositionierenSpieler(Schiff[] schiffe) throws NumberFormatException, IOException {
		//Schlachtkreuzer

		for(Schiff schiff : schiffe) {
			for(int s=0;s<schiff.getAnzahl();s++) {
				System.out.printf("%d. %s (%s Felder)\n",s+1,schiff.getName(),schiff.getGroesse());
				schiffPositionierenSpieler(schiff);
				
			}
		}
		

	}
	
	public void schiffPositionierenSpieler(Schiff schiff) {
		boolean falscheAngabe = false;
		int x=-1,y=-1;
		String a="";
		do {
			falscheAngabe = false;
			while(true) {
				try {
					System.out.printf(" X:",schiff.getName());
					x = Integer.parseInt(Spiel.reader.readLine());
					System.out.print("  Y:");
					y = Integer.parseInt(Spiel.reader.readLine());
					System.out.print(" Ausrichtung [w/v]:");
					a = Spiel.reader.readLine();
					logger.debug("Spieler hat {} auf Position {}/{} in {} Ausrichtung positioniert.",schiff.getName(), x,y,a);
					break;
				} catch(IOException e) {
					logger.error(e.getMessage()); 
				} catch (NumberFormatException e){
					logger.error(e.getMessage());
				}
			}
				
			
			//liegt das Start- und Endfeld im Spielfeld
			if(x>=0 && x<=9 && y>=0 && y<=9 &&
					x+schiff.getGroesse()>=0 && x+schiff.getGroesse()<=9 && y+schiff.getGroesse()>=0 && y+schiff.getGroesse()<=9	//MagigNumber
					) {
				//Wenn ja, dann schauen, ob andere Schiffe im Weg sind. 
				//Vertikal
				if(a.equalsIgnoreCase("v")) {
					for(int j=y;j<y+schiff.getGroesse();j++) {
						if(feld[x][j].isSchiff()) {
							falscheAngabe=true;
						}
					}
				}
				//Horizontal
				if(a.equalsIgnoreCase("w")) {
					for(int i=x;i<x+schiff.getGroesse();i++) {
						if(feld[i][y].isSchiff()) {
							falscheAngabe=true;
						}
					}
				}
			} 
		}while(falscheAngabe);
		setShip(x,y,a,schiff);
		gibSpielfeldAufKonsoleAus();

	}
	
	public void setShip(int x, int y, String a, Schiff schiff) {
		logger.info(schiff.getName()+" - Start: ("+x+"/"+y+") - Groesse: "+schiff.getGroesse());
		if(a.equalsIgnoreCase("v")) {
			for(int j=y;j<y+schiff.getGroesse();j++) {
				feld[x][j].setzeSchiff();
			}
		}
		//Horizontal
		if(a.equalsIgnoreCase("w")) {			
			for(int i=x;i<x+schiff.getGroesse();i++) {
				feld[i][y].setzeSchiff();
			}
		}
	}
	
	public void schiffePositionierenComputer(Schiff[] schiffe) throws NumberFormatException, IOException {
		//Schlachtkreuzer

		for(Schiff schiff : schiffe) {
			for(int s=0;s<schiff.getAnzahl();s++) {
				schiffPositionierenComputer(schiff);
			}
		}
		
	}
	
	public void schiffPositionierenComputer(Schiff schiff) throws NumberFormatException, IOException {
		boolean falscheAngabe = false;
		int x,y;
		String a;
		do {
			falscheAngabe = false;
			x=(int)(Math.random() * 10)%10;
			y=(int)(Math.random() * 10)%10;
			a=(int)(Math.random() * 2)%2==1 ? "w" : "v";
			//liegt das Start- und Endfeld im Spielfeld
			if(x>=0 && x<=9 && y>=0 && y<=9 &&
					x+schiff.getGroesse()>=0 && x+schiff.getGroesse()<=9 && y+schiff.getGroesse()>=0 && y+schiff.getGroesse()<=9	//MagigNumber
					) {
				//Wenn ja, dann schauen, ob andere Schiffe im Weg sind. 
				//Vertikal
				if(a.equalsIgnoreCase("v")) {
					for(int j=y;j<y+schiff.getGroesse();j++) {
						if(feld[x][j].isSchiff()) {
							falscheAngabe=true;
						}
					}
				}
				//Horizontal
				if(a.equalsIgnoreCase("w")) {
					for(int i=x;i<x+schiff.getGroesse();i++) {
						if(feld[i][y].isSchiff()) {
							falscheAngabe=true;
						}
					}
				}
			} 
			else {
				falscheAngabe=true;
				//System.out.println("Computer muss nochmal überlegen.");
			}

		}while(falscheAngabe);
		setShip(x,y,a,schiff);
		//gibSpielfeldAufKonsoleAus();

	}
	
	
	
} // Ende der Klasse