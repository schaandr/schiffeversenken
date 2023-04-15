

import java.io.Reader;

public class Schiff{
	private String name;
	private int xstart, ystart;
	private boolean waagerecht;
	private int groesse;
	public int getGroesse() {
		return groesse;
	}


	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}


	private int anzahl;

	
	private Schiff(int xstart, int ystart, boolean waagerecht, int groesse, int anzahl, String name) {
		this.name = name;
		this.xstart=xstart;
		this.ystart=ystart;
		this.waagerecht=waagerecht;
		this.groesse = groesse;
		this.anzahl = anzahl;
	}
	
	
	public static Schiff createSchlachtschiff(int xstart,int ystart, boolean waagerecht) {
		Schiff tmpSchiff = new Schiff(xstart,ystart,waagerecht, 5,1,"Schlachtschiff");	 
		return tmpSchiff;
	}
	
	public static Schiff createKreuzer(int xstart,int ystart, boolean waagerecht) {
		Schiff tmpSchiff = new Schiff(xstart,ystart,waagerecht, 4,2,"Kreuzer");	 
		return tmpSchiff;
	}
	public static Schiff createZerstoerer(int xstart,int ystart, boolean waagerecht) {
		Schiff tmpSchiff = new Schiff(xstart,ystart,waagerecht, 3,3, "Zerstörer");
		return tmpSchiff;
	}
	
	public static Schiff createUboot(int xstart,int ystart, boolean waagerecht) {
		Schiff tmpSchiff = new Schiff(xstart,ystart,waagerecht, 2,4,"U-Boot");
		return tmpSchiff;
	}


	public int getAnzahl() {
		return anzahl;
	}


	public String getName() {
		return name;
	}
	
	
	
}
