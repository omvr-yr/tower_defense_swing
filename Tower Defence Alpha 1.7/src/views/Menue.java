package views;
import java.awt.Point;

import models.Builder;
import models.Building;
import models.Gegner;
import models.Settings;
import models.Tower;
import models.Wave;

public class Menue {
	Main main;
	Steuerung steuerung;
	Game game;
	Settings settings;
	Wave waves;
	SidePanel SidePanel;
	Builder bauHelp;
	InfoLeiste infoLeiste;
	
	public int tap;
	boolean flaechen_zeigen;
	public boolean building;
	int balkenLaenge;
	int BuildingType; // 0=Nichts, 1=LaserTower, 2=EnergyGenerator, 3=Tour Éclair
	int activeBuilding;
	int mouseButton;
	Tower upgradeTower;
	Tower infoObjekt;
	
	public Menue(Main ma) {
		main = ma;
		game = main.game();
		//settings = main.settings();
		tap = 1;
		steuerung = main.steuerung();
		neueSidePanel();
		neueInfoLeiste();
	}
	
	void neueSidePanel() {
		SidePanel  = new SidePanel(main, this); 
		SidePanel.bauEintraegeAdden();
	}
	
	void neueInfoLeiste() {
		infoLeiste = new InfoLeiste(main, this);
	}
	
	/*void mouseControl() {
		main.mousePressed();
		if(main.mousePressed) {
			
			if(main.mouseButton == PConstants.LEFT) {
				//if(BuildingType > 0 || activeBuilding > 0){
						game.newTower(BuildingType, main.mouseX-100, main.mouseY);
					//}					
				}
				else {mouseOverButton();mouseOverBuilding();}
			}
			
			/*if(main.mouseButton == PConstants.RIGHT) {
				BuildingType = 0;
				activeBuilding = 0;
			}
		
	}*/
	
	void keyControl() {
		if(game.secours) {main.keyCode = 0; main.key = 0; game.secours = false;}
		if(steuerung.key('w')) {game.wave += 1;}
		if(steuerung.key('g')) { game.monnaie += 10; }
		if(steuerung.key('c')) { for (Gegner g : game.ennemis) {game.retirer.add(g);} }
		//if(steuerung.key('v')) { for (Tower t : game.towers) {game.retirer.add(t); game.removeBedeckteFlaechen(t.position.x+((main.settings(t.name).largeur/2)-main.settings(t.name).HBbreite), t.position.y+((main.settings(t.name).hauteur/2)-main.settings(t.name).HBhoehe));} }
		if(steuerung.key('x')) { for (Building b : game.buildings) {game.retirer.add(b); game.removeBedeckteFlaechen(b.position.x, b.position.y);} }
		if(steuerung.key('b')) { if(flaechen_zeigen) {flaechen_zeigen = false;} else{flaechen_zeigen = true;} }
		if(steuerung.key('l')) { game.vie += 10; game.maxVie += 10;}
		if(steuerung.key('e')) { game.energy += 10; game.maxEnergy += 10;}
		if(steuerung.key('n')) { SidePanel.offset += 4;}
		if(steuerung.key('m')) { SidePanel.offset -= 4;}
		if(steuerung.key('s')) { System.out.println(game.towers);}
	}
	
	void mouseOverBuilding() {
		if(steuerung.click("rechts")) {upgradeTower = null; infoObjekt = null;}
		for(Tower t : game.towers) {
			if(steuerung.clickOn("links", (int)t.position.x+100, (int)t.position.y, t.largeur, t.hauteur)) {
				//upgradeTower = t;
				infoObjekt = t;				
			}
		}
	}
	
	void surface() {
		main.fill(255,0,0,75);
		for(Point[] p : game.surface) {
			int Xpos = p[0].x;
			int Ypos = p[0].y;
			int Xlaenge = p[1].x - Xpos;
			int Ylaenge = p[1].y - Ypos;
			main.rect(Xpos, Ypos, Xlaenge, Ylaenge);
		}
	}

	void upgradeHilfe() {
		main.textSize(20);
		main.text("upgraden", main.mouseX-100, main.mouseY);
	}
	
	void tabsZeichnen(int groeseX, int groeseY, int rectY, int posX1, int posX2, int posX3){
		int textY = 740;
		
		if(tap == 1){main.stroke(20,200,255);}
		
		main.fill(20,200,255);
		main.text("Tower", 20,textY);
		main.fill(100,100,100,100);
		main.rect(posX1, rectY , groeseX, groeseY);
		main.stroke(0);
		
		if(tap == 2){main.stroke(20,200,255);}
		main.fill(20,200,255);
		main.text("Buildings",130,textY);
		main.fill(100,100,100,100);
		main.rect(posX2, rectY , groeseX, groeseY);
		main.stroke(0);
		
	}
	
	void tabs(){
		int groeseX = 100;
		int groeseY = 20;
		int posY1 = 10;
		int posY2 = 120;
		int posY3 = 230;
		int rectX = 725;
		if(steuerung.click("links")){
				if(steuerung.mausbedeckt(posY1 + 100, 730, groeseX, groeseY)){tap = 1;}
				if(steuerung.mausbedeckt(posY2 + 100, 730, groeseX, groeseY)){tap = 2;}
				if(steuerung.mausbedeckt(posY3 + 100, 730, groeseX, groeseY)){tap = 3;}
				//SidePanel.offset = 0;
		}
		tabsZeichnen(groeseX, groeseY, rectX, posY1,posY2, posY3);
	}
	
	void energyAnzeige() {
		int balkenLaenge_max = 400;
		float faktor = game.energy/game.maxEnergy;
		System.out.println("-------------------------");
		System.out.println(game.maxEnergy/game.energy);
		System.out.println("-------------------------");
		System.out.println(faktor);
		int faktorGruen = (int) (faktor*250);
		System.out.println(faktorGruen);

		int faktorRot = (int) ((1-faktor)*250);
		System.out.println(faktorRot);

		balkenLaenge = (int) (balkenLaenge_max * faktor);
		main.fill(faktorRot, faktorGruen, 250);
		main.stroke(120,120,250);
		main.rect(530, 22, balkenLaenge, 18);
		main.textSize(25);
		main.text("Energy:",430,40);
		main.textSize(10);
		main.text((int) game.energy,430,50);
		main.noStroke();
	}
	void increase_energyAnzeige() {
//		int balkenLaenge_max = 400;
		float faktor = game.maxEnergy/game.energy;
		System.out.println("-------------------------");
//		System.out.println(game.maxEnergy/game.energy);
		System.out.println("-------------------------");
		System.out.println(faktor);
		int faktorGruen = (int) (faktor*250);
		System.out.println(faktorGruen);

		int faktorRot = (int) ((1+faktor)*250);
		System.out.println(faktorRot);

		System.out.println(balkenLaenge);
		balkenLaenge = balkenLaenge+(int) (1 * faktor);
		System.out.println(balkenLaenge);
		main.fill(faktorRot, faktorGruen, 250);
		main.stroke(120,120,250);
		main.rect(530, 22, balkenLaenge, 18);
		main.textSize(25);
		main.text("Energy:",430,40);
		main.textSize(10);
		main.text((int) game.energy,430,50);
		main.noStroke();
	}
	
	void lebensAnzeige() {
		int balkenLaenge_max = 300;
		float faktor = (float) game.vie/game.maxVie;
		int faktorGruen = (int) (faktor*250);
		int faktorRot = (int) ((1-faktor)*250);
		balkenLaenge = (int) (balkenLaenge_max * faktor);
		main.fill(faktorRot, faktorGruen, 0);
		main.stroke(120,120,250);
		main.rect(110, 22, balkenLaenge, 18);
		main.textSize(25);
		main.text("Lifeline:",20,40);
		main.textSize(10);
		main.text(game.vie,20,50);
		main.noStroke();
	}
	
	void anzeigen() {
		main.fill(200,200,250);
		main.stroke(200,200,250);
		main.textSize(15);
		main.text("Monnaie : ",860,740);
		main.text(" " + game.monnaie, 930,740);
		main.noStroke();
		main.text("Mode : ",860,790);
		String niveau = "";
		if(game.level == 1){
			niveau = "Débutant";
		} else if (game.level == 2){
			niveau = "Intermédiaire";
		} else if (game.level == 3){
			niveau = "Expert";
		}
		main.text(" " + niveau, 910,790);
		main.noStroke();
	}
	void buildingEnd(){
		//System.out.println(building);
		if(steuerung.click("rechts")){
			building = false;
		}
	}
	
	void hintergrundAnzeigen() {
		main.fill(0);
		main.noStroke();
		main.rect(-100, 0, 100, main.hauteur);
		main.rect(-100, main.hauteur-100, main.largeur, 100);
	}
	
	Menue getMenue(){
		return this;
	}

	public void machMenue() {
		hintergrundAnzeigen();
		infoLeiste.machDeinDing();	
		SidePanel.machDeinDing(tap);
		if(flaechen_zeigen) {surface();}
		if(building) {bauHelp.machDeinDing();}
		lebensAnzeige();
		energyAnzeige();
//		increase_energyAnzeige();
		anzeigen();
		keyControl();
		mouseOverBuilding();
		buildingEnd();
		tabs();
	}

}
