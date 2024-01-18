package models;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import views.Main;
import views.Maps;
import views.Game;

public class Ennemi {
	
	Main main;
	Maps map;
	Game game;
	Settings setting;
	
	double speed;
	double speed_Max;
	int DMG;
	double health;
	double health_max;
	int loot;
	int drehGesch;
	int angle_rotation;
	int aktuellerWendepunkt = 0;
	public PVector direction = new PVector (0, 0);
	PVector position;
	PImage bild;	
	
	public Ennemi(Main ma) {
		main = ma;
		game = main.game();
		
	 /* health = settings.Gegener_health_max;
		health_max = settings.Gegener_health_max;
		DMG = settings.Gegener_DMG;
		loot = settings.Gegener_loot;
		speed = settings.Gegener_speed;
		drehGesch = settings.Gegener_drehGesch;
		bild = settings.Gegner_bild; */
	}
	
	public void setPosition(PVector startPos) {
		position = startPos;
	}
	
	boolean amZiel(){
		boolean b = (aktuellerWendepunkt == game.map.aire_point.size());
			return b; 
	}
	
	void sterben() {
		if(health <= 0) {
			health = 0;
			game.retirer.add(this);
			game.monnaie = game.monnaie + loot;
			main.game.energy += 4;
			
			System.out.println("Enemy removed from wave!");
			
		}
	}
	
	void durchkommen(){
		if(amZiel()){
			speed = 0;
			game.retirer.add(this);
			game.vie -= DMG;
		}
	}

	public boolean bedeckt(float x, float y, float x2, float y2) {
		boolean b = ( (direction.x == -1 && x <= x2)||(direction.x == 1 && x >= x2)||(direction.y == -1 && y <= y2)||(direction.y == 1 && y >= y2) );
		return b;
	}
	
	void richtungAendern() {
		float wePuX = game.aire_point.get(aktuellerWendepunkt).x;
		float wePuY = game.aire_point.get(aktuellerWendepunkt).y;
		
		if(bedeckt(position.x, position.y, wePuX, wePuY)) { 	
			direction = game.aire_dir.get(aktuellerWendepunkt);
			position.x = (game.aire_point.get(aktuellerWendepunkt).x + direction.x);
			position.y = (game.aire_point.get(aktuellerWendepunkt).y + direction.y);
			aktuellerWendepunkt = aktuellerWendepunkt + 1;
		}
				//System.out.println(aktuellerWendepunkt);
}
	
	void bewegen() {
		double by = direction.y * speed;
		double bx = direction.x * speed;
		position.y = (float) (position.y + by);
		position.x = (float) (position.x + bx); 
		if(speed != 0){
			richtungAendern();
		}
		
	}
	
	void lebensleiste(){
		int balkenLaenge_max = 20;
		float faktor = (float) (health/health_max);
		int faktorGruen = (int) (faktor*250);
		int faktorRot = (int) ((1-faktor)*250);
		main.fill(faktorRot, faktorGruen, 0);
		main.stroke(20, 20, 200);
		int balkenLaenge = (int)(balkenLaenge_max * faktor);
		main.rect(position.x - balkenLaenge_max/2, position.y - bild.height/2 ,balkenLaenge, 2);	
	}
	
	public void init_ausrichtung(){
		if (direction.x == 1){angle_rotation = 90;}
		if (direction.x == -1){angle_rotation = 270;}
		if (direction.y == 1){angle_rotation = 180;}
		if (direction.y == -1){angle_rotation = 0;}
	}
	
	void drehen(){
		if (angle_rotation == 360){angle_rotation = 0;}
		if (direction.x == 1 && angle_rotation != 90){
			if(angle_rotation < 90){angle_rotation = angle_rotation + drehGesch;}
			if(angle_rotation > 90){angle_rotation = angle_rotation - drehGesch;}
		}
		if (direction.x == -1 && angle_rotation != 270){
			if(angle_rotation >= 180 && angle_rotation < 270){angle_rotation = angle_rotation + drehGesch;}
			if(angle_rotation == 0){angle_rotation = 360 - drehGesch;}
			if(angle_rotation > 270){angle_rotation = angle_rotation - drehGesch;}
		}
		if (direction.y == 1 && angle_rotation != 180){
			if(angle_rotation < 180){angle_rotation = angle_rotation + drehGesch;}
			if(angle_rotation > 180){angle_rotation = angle_rotation - drehGesch;}
		}
		if (direction.y == -1 && angle_rotation != 0){
			if(angle_rotation <= 90){angle_rotation = angle_rotation - drehGesch;}
			if(angle_rotation >= 270){angle_rotation = angle_rotation + drehGesch;}
		}
	}
	void showTower() {		
		drehen();
		main.translate((int)position.x , (int)position.y);
		main.rotate(angle_rotation*PConstants.TWO_PI/360);
		main.image(bild, - (bild.height / 2), - (bild.width / 2));
		main.rotate(-(angle_rotation*PConstants.TWO_PI/360));
		main.translate((int)-position.x ,(int) - position.y);
		
		//main.image(bild,position.x,position.y);
	}
	void speedReset(){
		speed = speed_Max;
	}
	
	
	public void affiche() { }
}