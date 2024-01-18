package models;
import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import processing.core.PImage;
import processing.core.PVector;
import views.Main;
import views.Menue;
import views.Game;

public class Tower {
	Main main;
	Menue menu;
	Game game;
	Settings settings;
	Roboter robo;
	
	double energyKonsum;
	float schaden;
	public int largeur;
	public int hauteur;
	public int HBbreite;
	public int HBhoehe;
	int range;
	int frequenz;
	public int preis;
	int type;
	int energyPriority;
	String name;
	PImage base;
	PImage gun;
	PImage cover;
	float ausrichtung;
	int button;
	public PVector position;
	
	public Tower(Main ma) {
		main = ma;
		game = main.game();
		energyPriority = 1;
	}
	
	public Integer getEnergyPriority() {
		return energyPriority;
	}
	

	public void changePriority(int w) {
		 if(energyPriority > 0-w && energyPriority <= 10-w) {energyPriority += w;}
		 game.sortTowers();		
	}
	
	public void upgradeSchaden(float plusSchaden, int preis) {
		if(game.monnaie >= preis) {schaden += plusSchaden; game.monnaie -= preis;}
	}
	
	public void upgradeRange(float plusRange, int preis) {
		if(game.monnaie >= preis) {range += plusRange; game.monnaie -= preis;}
	}
	
	public void setPosition(PVector startPos) {
		position = startPos;
	}
	
	double dist(PVector t, PVector g){
		float distX = t.x - g.x;
		float distY = t.y - g.y;
		double dist_t_g = sqrt((distX * distX) + (distY * distY));
		return dist_t_g;
	}
	
	boolean pos(float z){return z>0;}
	boolean neg(float z){return z<0;}
	
	void shoot(float targetX,float targetY, float startX, float startY){
		main.strokeCap(1);
		main.strokeWeight(6);
		main.stroke(200, 0, 100, 80);
		main.line(startX, startY, targetX, targetY);
		main.strokeWeight(1);
		main.stroke(0, 178, 255);
		main.line(startX, startY, targetX, targetY);
	}
	
	void zielen_ausrichten() {
		for (Gegner g : game.ennemis) {
				if(angreifbar(g)){
					PVector positionGun = new PVector(position.x+(largeur/2), position.y+(hauteur/2));
					double dist = dist(positionGun, g.position);
					double alpha = (double) (Math.acos(-(g.position.y-(position.y+40))/dist));
					main.stroke(126);
					shoot(position.x + 40, position.y + 40, g.position.x, g.position.y);
					game.energy -= (energyKonsum);
					g.health = g.health - schaden;
					if(g.position.x >= (position.x+40)) {ausrichtung = (float) alpha;}
					if(g.position.x <= (position.x+40)) {ausrichtung = (float) ((double)((2*PI)-alpha));}
					
					
					break;
			}
		}
	}
	
	public void infoDisplay() {
		main.fill(0, 80);		
		main.strokeWeight(2);
		main.stroke(120,120,250);
		//main.rect(555, 776, 30, 18);
		main.ellipse(position.x+(largeur/2), position.y+(hauteur/2), 2 * range, 2 * range);
		main.fill(255);
		
		main.text(String.valueOf((float)(((int)(schaden*10))/10.0)), 470, 740);
		main.text("Dégâts :", 400, 740);
		main.text(range, 470, 765);
		main.text("Portée :", 400, 765);		
				
		if(main.steuerung.mausbedeckt(515+100, 726, 135, 18)) {button = 1;}
		else if(main.steuerung.mausbedeckt(515+100, 751, 135, 18)) {button = 2;}
		else if(main.steuerung.mausbedeckt(515+100, 776, 30, 18)) {button = 3;}
		else if(main.steuerung.mausbedeckt(555+100, 776, 30, 18)) {button = 4;}
		else{button = 0;}
		
		if(main.steuerung.click("links")) {buttongestiontour();}
		
		if(button == 1) {main.fill(50,220,255);}
		main.text("Augmenter pour : " + 90, 520, 740);
		main.fill(255);
		if(button == 2) {main.fill(50,220,255);}
		main.text("Augmenter pour : " + 80, 520, 765);
		main.fill(255);
	}
	
	boolean angreifbar(Gegner a){
		PVector positionGun = new PVector(position.x+(largeur/2), position.y+(hauteur/2));
		double dist = dist(positionGun, a.position);
		if(a.position.y < 720 && dist <= range){
			return true;
		}
		return false;
	}
	
	void buttongestiontour() {
		switch (button) {
		case 1: upgradeSchaden((float) 0.1, 50); break;
		case 2: upgradeRange(10, 50); break;
		case 3: changePriority(1); break;
		case 4: changePriority(-1); break;
		}
	}

	public void affiche() {
		
	}


	
}
