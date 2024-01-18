package models;

import processing.core.PImage;
import processing.core.PVector;
import views.Main;

public class BlitzTower extends Tower {
	double slowFactor;
	double rotSpeed;
	int blitzRefresh = 60;
	
	PImage cover;
	public BlitzTower(Main ma) {
		super(ma);
		settings = main.settings("Tour Éclair");
		range = settings.range;
		energyKonsum = settings.energyKonsum;
		preis = settings.preis;
		schaden = settings.schaden;
		frequenz = settings.frequenz;
		largeur = settings.largeur;
		hauteur = settings.hauteur;
		HBbreite = settings.HBbreite;
		HBhoehe = settings.HBhoehe;
		type = settings.type;
		base = settings.base;
		gun = settings.gun;
		cover = settings.cover;
		
		slowFactor = settings.slowFactor;
		rotSpeed = settings.rotSpeed;
	}
	
	void shoot(float targetX,float targetY, float startX, float startY){			
		boolean malen = true;
		int ende = 17;
		int blitzLaenge = 17;
		PVector ziel = new PVector(targetX,targetY);
		PVector pos = new PVector(startX,startY);
		double posAltx = startX;
		double posAlty = startY;
			
			while(malen){
				
				//-PosAlt = pos
				/*System.out.println("posAlt_anfang"); 
				System.out.println(posAlt);*/
				//pos berechnen
				//PVector richtung = new PVector((targetX - pos.x), (targetY - pos.y));
				//PVector richtungRandom = new PVector( (float)((targetX - pos.x)/dist(pos, ziel) * Math.random() * blitzLaenge) ,(float) ((targetY - pos.y)/dist(pos, ziel) * Math.random() * blitzLaenge));
				//System.out.println("posAlt"); 
				//System.out.println(posAltx);
				//System.out.println(posAlty);
				pos.x = (float) (pos.x + ((targetX - pos.x)/dist(pos, ziel) * Math.random() * blitzLaenge)) ;
				pos.y = (float) (pos.y + ((targetY - pos.y)/dist(pos, ziel) * Math.random() * blitzLaenge)) ;
				
				//System.out.println(posAltx);
				//System.out.println(posAlty);
				//-zeichnen
				
				main.strokeWeight(4);
				main.stroke(30, 100, 255, 100);
				main.line((float)posAltx,(float) posAlty,pos.x, pos.y);
				main.strokeWeight(1);
				main.stroke(30, 230, 255);
				main.line((float)posAltx,(float) posAlty,pos.x, pos.y);

				//Ende
				if(dist(pos, ziel) < ende){
					malen = false;
					main.line(targetX,targetY,pos.x, pos.y);
				}
				posAltx = pos.x;
				posAlty = pos.y;
			}		
	}
	
	void zielen() {
		int i = 1;
		int gegnerMenge = settings.gegnerMenge;
		for (Ennemi g : game.ennemis) {
			if(angreifbar(g)){
				//PVector positionGun = new PVector(position.x+(largeur/2), position.y+(hauteur/2));
				//double dist = dist(positionGun, g.position);			
				//main.stroke(126);
				g.speed = g.speed * slowFactor;
				g.health = g.health - schaden;
				shoot(g.position.x, g.position.y, position.x + 40, position.y + 40);
				game.energy -= energyKonsum;	
				i++;
			}
			if(i == gegnerMenge){
				i = 0;
				break;
			}
			
		}
	}
	void drehen(){
		angle_rotation += rotSpeed;
	}
	void showTower() {
		main.image(base, position.x, position.y);
		main.translate((position.x + (gun.height/2)) , (position.y + (gun.height/2)));
		main.rotate(angle_rotation);
		main.image(gun,-(gun.height/2),-(gun.height/2) /*- (gun.width / 2)*/);
		main.rotate((float)-angle_rotation);
		main.translate( -(position.x + (gun.height/2)), -(position.y + (gun.height/2)));
	}
	void covershowTower() {
		main.image(cover, position.x, position.y);
	}
	
	void buttongestiontour() {
		switch (button) {
		case 1: upgradeSchaden((float) 0.1, 100); break;
		case 2: upgradeRange(10, 60); break;
		case 3: changePriority(1); break;
		case 4: changePriority(-1); break;
		}
	}
	
	public void affiche() {
		showTower();
		if(game.energy >= energyKonsum) {zielen();}
		covershowTower();
		drehen();
		//main.color(255, 204, 0);
		}
	
}
