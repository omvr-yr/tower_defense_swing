package views;

import java.util.ArrayList;

import models.BauHilfe;
import models.Settings;
import processing.core.PImage;

public class SeitenLeiste {
	Settings setting;
	Main main;
	Game game;
	Menue menu;
	
	int type;
	int eintrag;
	public int offset;
	int tapSize;
	PImage icon;
	PImage image1;
	PImage image2;
	PImage image3;
	String name;
	int position;
	public ArrayList<TowerEintrag> tap1 = new ArrayList<TowerEintrag>();
	public ArrayList<BuildingEintrag> tap2 = new ArrayList<BuildingEintrag>();
	
	
	public SeitenLeiste(Main ma, Menue me) {
		main = ma;
		menu = me;
		game = main.game();
	}
	
	public void bauEintraegeAdden() {
		
		//tap 1
		tap1.clear();
		int num = 0;
	 	TowerEintrag t1 = new TowerEintrag(main, menu, "Tour Laser", num); tap1.add(t1); num++;
	 	TowerEintrag t3 = new TowerEintrag(main, menu, "Tour Éclair", num); tap1.add(t3); num++;
	 	TowerEintrag t4 = new TowerEintrag(main, menu, "Tour de Tir", num); tap1.add(t4); num++;
	 	
		//tap 2
	 	tap2.clear();
	 	//System.out.println(tap2.size());
		num = 0;
		BuildingEintrag b1 = new BuildingEintrag(main, menu, "Energy Generator", num); tap2.add(b1);num++;
		//tap3
		game.roboters.clear();
		num = 0;
	}
	
	void bauen(String namedeszubauenden, int preis){
		if(main.steuerung.clickOn("links", 0, position, 100, 100) && !locked(preis)){
			main.fill(30,180,250);
			main.rect(-100 , position, 100, 100);
			menu.bauHelp = new BauHilfe(main,namedeszubauenden, menu);
			menu.building = true;
		}
			//System.out.println("bauen wird ausgef�rt");
	}
	
	void Robosetzen(){
		if(main.steuerung.clickOn("links", 0, position, 100, 100)){
			
		}
	}
	void scrollen() {
		int spied = 30;
		if(main.steuerung.wheel("hoch")) {offset += spied;}
		if(main.steuerung.wheel("runter")) {offset -= spied;}
	}

	void darstellen(int tap) {
		for(int i=0; i < tapSize; i++) {
			main.fill(100,100,200,50);
			main.strokeWeight(2);
			main.stroke(120,120,250);
			main.rect(-100,(i*100) + offset, 100, 100);
			main.strokeWeight(1);
		}
	}
	void offsetMax_Min(int tap){
		if(tap == 1){tapSize = tap1.size();}
		if(tap == 2){tapSize = tap2.size();}
		if(tap == 3){tapSize = game.roboters.size();}
		if(offset < -tapSize * 100 + main.hauteur){
			offset = -tapSize * 100 + main.hauteur;
		}
		if(offset > 0){offset = 0;}
	}
	
	boolean locked(int preis) {
		if(preis > main.game.monnaie) {return true;}
		return false;
	}	

	void positionieren(int o) {
		//System.out.println(o);
		position = (eintrag * 100) + o;
		
	}
	
	void darstellen_Icons_info(int preis, String name) {
		if(locked(preis)) {main.tint(255, 100);}
		main.image(icon, -100,  position);
		main.noTint();
		if(main.steuerung.mausbedeckt(0, position, 100, 100)) {
			int mX = main.mouseX-100; int mY = main.mouseY;
			main.fill(0,120); 
			main.noStroke();
			main.rect(mX+15, mY-15, 130, 40);
			main.fill(255);
			main.text(name, mX+20, mY);
			main.text("Prix :", mX+20, mY+20);
			main.text(preis, mX+70, mY+20);
		}
	}
	
	 public void machDeinDing(int tap) {
		 scrollen();
		 offsetMax_Min(tap);
		 darstellen(tap);
		 if(tap == 1){for(TowerEintrag e : tap1) {e.machDeinDing(offset);}}
		 if(tap == 2){for(BuildingEintrag e : tap2) {e.machDeinDing(offset);}}
	 }
	
}
