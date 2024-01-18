package views;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import models.BlitzTower;
import models.Building;
import models.EnergyGenerator;
import models.Gegner;
import models.GegnerType1;
import models.GegnerType2;
import models.LaserTower;
import models.Roboter;
import models.SchussTower;
import models.Tower;
import models.Wave;
import processing.core.PImage;
import processing.core.PVector;

public class Game {
	Main main;
	Menue menu;
	HautMenu hautmenu;
	PauseMenue pauseMenue;
	public Maps map;
	public Wave actuellewave;	
	PImage GameOver;
	int timer;
	int vaguetimer;
	public int monnaie;
	//int h = 0;
	int wave;
	boolean secours;
	public float maxEnergy = 100;
	public float energy = maxEnergy;
	int maxVie = 100;
	public int vie = maxVie;
	public int waveOffsetTime = 0;
		
	public ArrayList<Wave> waves = new ArrayList<>();
	public ArrayList<Point[]> surface;
	public ArrayList<PVector> aire_point;
	public ArrayList<PVector> aire_dir;
	public ArrayList<Gegner> ennemis = new ArrayList<Gegner>();
	public ArrayList<Tower> towers = new ArrayList<Tower>();
	public ArrayList<Roboter> roboters = new ArrayList<Roboter>();
	public ArrayList<Building> buildings = new ArrayList<Building>();
	public ArrayList<Object> retirer = new ArrayList<Object>();
	public boolean running;
	Comparator<? super Tower> tcomp;

	public int level;
	public int np_map;
	
	public Game(Main ma, int niveau, int carte) {
		main = ma;
		hautmenu = new HautMenu(main);
		tcomp = new TowerComp();
		level = niveau;
		np_map = carte;
	}
	
	public Menue menu() {
		return menu;
	}

	void neuesGame(int niveau, int map) {
		level = niveau;
		newmap(map);
		newWave();
		newMenu();
		newPauseMenu();
		newRoboter();
		monnaie +=500;
		vaguetimer = 550;
		timer = 100;
		running = true;
	}

	void newMenu() {
		menu = new Menue(main);
		//menu bereich bedecken
			Point[] aire = new Point[2];
			aire[0] = new Point(-100,0);
			aire[1] = new Point(0, 720);
			surface.add(aire);
	}

	void newPauseMenu() {
		pauseMenue = new PauseMenue(main);
	}
	
	public void newTower(String name){		
		boolean bu = false;
		boolean to = false;
		Tower t = new Tower(main);
		Building b = new Building(main);
		//Settings setting = main.settings(name);
		if(name == "Tour de Tir"){t = new SchussTower(main); to = true;
		System.out.println("..........Tour de Tir Selected..........");
		//System.exit(0);
		}
		if(name == "Tour Laser"){t = new LaserTower(main); to = true;
		System.out.println("..........Tour Laser Selected..........");
		}
		
		if(name == "Tour Éclair"){t = new BlitzTower(main); to = true;
		System.out.println("..........Tour Éclair Selected..........");
		}
		if(name == "Energy Generator"){b = new EnergyGenerator(main); bu = true;
		System.out.println("..........Energy Tower Selected..........");
		}
		int largeur = 0;
		int hauteur = 0;
		if(to){largeur = t.largeur;}else{largeur = b.largeur;}
		if(to){hauteur = t.hauteur;}else{hauteur = b.hauteur;}
		PVector startPosition = new PVector(main.mouseX - (largeur/2) - 100, main.mouseY - (hauteur/2));		
		//PVector endPosition = new PVector(startPosition.x+largeur,startPosition.y+hauteur);
		if(monnaie < t.preis) {}
		else{
		Point[] aire = new Point[2];
		t.setPosition(startPosition);
		b.setPosition(startPosition);

		if(name == "Energy Generator") {
			aire[0] = new Point((int) main.mouseX-b.HBbreite - 100, main.mouseY-b.HBhoehe);
			aire[1] = new Point((int) main.mouseX+b.HBbreite - 100, main.mouseY+b.HBhoehe);
			surface.add(aire);
		}
		else {
			aire[0] = new Point((int) main.mouseX-t.HBbreite - 100, main.mouseY-t.HBhoehe);
			aire[1] = new Point((int) main.mouseX+t.HBbreite - 100, main.mouseY+t.HBhoehe);
			surface.add(aire);
		}
		menu.BuildingType = 0;
		if(to) {monnaie -= t.preis;}
		if(bu) {monnaie -= b.preis;}
		if(to){towers.add(t);}
		if(bu){buildings.add(b);}
		}
	}
	
	void newWave(){ 
		// type d'ennemis / longeur de la vague / délai entre les ennemis / temps d'attente intial
		if(level == 1){ 
 			
			ArrayList<int[]> gruppen = new ArrayList<>();
			int[] gruppe1 = {1, 20, 70, 0};			gruppen.add(gruppe1);
			int[] gruppe2 = {1, 20, 50, 1000};		gruppen.add(gruppe2);
			Wave w1 = new Wave(main, gruppen, 4000);
			
			ArrayList<int[]> gruppen2 = new ArrayList<>();
			int[] gruppe12 = {2, 5, 60, 1000};		gruppen2.add(gruppe12);
			int[] gruppe22 = {1, 10, 60, 600};		gruppen2.add(gruppe22);
			int[] gruppe32 = {2, 20, 50, 800};		gruppen2.add(gruppe32);	
			Wave w2 = new Wave(main, gruppen2, 100);
			
			
			waves.add(w1);
			waves.add(w2);
			actuellewave = waves.get(0);
		}
		
		// type d'ennemis / longeur de la vague / délai entre les ennemis / temps d'attente intial
		else if(level == 2){ 
 			monnaie -= 200;
			ArrayList<int[]> gruppen = new ArrayList<>();
			int[] gruppe1 = {2, 30, 40, 0};			gruppen.add(gruppe1);
			int[] gruppe2 = {1, 30, 50, 1000};		gruppen.add(gruppe2);
			Wave w1 = new Wave(main, gruppen, 4000);
			
			ArrayList<int[]> gruppen2 = new ArrayList<>();
			int[] gruppe12 = {1, 25, 40, 1000};		gruppen2.add(gruppe12);
			int[] gruppe22 = {2, 25, 40, 600};		gruppen2.add(gruppe22);
			int[] gruppe32 = {1, 35, 30, 800};		gruppen2.add(gruppe32);	
			Wave w2 = new Wave(main, gruppen2, 100);
			
			
			waves.add(w1);
			waves.add(w2);
			actuellewave = waves.get(0);
		}
		else if(level == 3){ 
 			monnaie -= 300;
			ArrayList<int[]> gruppen = new ArrayList<>();
			int[] gruppe1 = {1, 30, 30, 0};			gruppen.add(gruppe1);
			int[] gruppe2 = {1, 40, 50, 1000};		gruppen.add(gruppe2);
			Wave w1 = new Wave(main, gruppen, 4000);
			
			ArrayList<int[]> gruppen2 = new ArrayList<>();
			int[] gruppe12 = {2, 30, 60, 1000};		gruppen2.add(gruppe12);
			int[] gruppe22 = {1, 40, 50, 600};		gruppen2.add(gruppe22);
			int[] gruppe32 = {2, 30, 20, 800};		gruppen2.add(gruppe32);	
			Wave w2 = new Wave(main, gruppen2, 100);
			
			
			waves.add(w1);
			waves.add(w2);
			actuellewave = waves.get(0);
		}
	}
	
	void newRoboter(){
		Roboter Hamlet = new Roboter(main,"Hamlet"); roboters.add(Hamlet);
		Roboter Steve = new Roboter(main,"Steve"); roboters.add(Steve);
		Roboter Ghost = new Roboter(main,"Ghost"); roboters.add(Ghost);
		Roboter Randy = new Roboter(main,"Randy"); roboters.add(Randy);
		Roboter Sam = new Roboter(main,"Sam"); roboters.add(Sam);
	}

	void newEnnemi(int offsetPos, int type) {
		Gegner g = new Gegner(main);
		if(type == 1){g = new GegnerType1(main);}
		if(type == 2){g = new GegnerType2(main);}
		
		g.direction = aire_dir.get(0);
		PVector spawnPosition = new PVector();
		spawnPosition.x = aire_point.get(0).x + (aire_dir.get(0).x * -offsetPos);
		spawnPosition.y = aire_point.get(0).y + (aire_dir.get(0).y * -offsetPos);
		g.setPosition(spawnPosition);
		ennemis.add(g);
		g.init_ausrichtung();
	}
	
	void newmap(int Lv){
		map = new Maps(main,this,Lv);
		aire_point = map.getWendepunkte_pos();
		aire_dir = map.getWendepunkte_dir();
		surface =  map.getsurfaces();
	}
	
	void neueWelle(int start, int laenge,int rate, int type) {
		for(int i = 1; i <= laenge; i++) {
			newEnnemi(i*rate + start, type);
		}
	}
	
	class TowerComp implements Comparator<Tower> {
		@Override
		public int compare(Tower t1, Tower t2) {
			return -(t1.getEnergyPriority().compareTo(t2.getEnergyPriority()));
		}
	}
	
	public void sortTowers() {
		Collections.sort(towers, tcomp);
	}
	
	
	void checkStatus() {
		if(vie<=0) {
			running = false;
			vie = 0;
			System.out.println("You Lost!");
		}
		if(energy<0) {energy = 0;}
		
		
	}
	
	void clean() {
		for (Object o : retirer) {
			if(o instanceof Gegner) {ennemis.remove(o);}
			if(o instanceof Tower) {towers.remove(o);}
			if(o instanceof Building) {buildings.remove(o);}
			if(o instanceof Point[]) {surface.remove(o);} 
		}
		retirer.clear();
	}
	
	void pause() {
		if(secours) {main.keyCode = 0; main.key = 0; secours = false;}
		if(vie<=0) {hautmenu.gameOverScreen();}		
		else {
			pauseMenue.pauseMenue();
		}
	}
	
	void removeBedeckteFlaechen(float objektPosX, float objektPosY) {
		for(Point[] p : surface){
			if(objektPosX == p[0].x && objektPosY == p[0].y){
				retirer.add(p);
			}
		}
	}
	
	void waveManager() {
		if(actuellewave == null) {
			if(ennemis.size() == 0) {
				main.text("You Win !", 300, 400);
				System.out.println("You Win!");
			}
		} else {
			actuellewave.machDeinDing();
		}
	}
	
	public boolean bedeckt(float pos1X, float pos1Y, float pos2X, float pos2Y) {
		boolean beidesBedeckt = false;
		for(int i = 0; i < surface.size(); i++) {
			boolean xBedeckt = false;
			boolean yBedeckt = false;
			if(pos1X <= surface.get(i)[0].x && surface.get(i)[0].x <= pos2X) {xBedeckt = true;}
			if(pos1X >= surface.get(i)[0].x && surface.get(i)[1].x >= pos1X) {xBedeckt = true;}
			if(pos1Y <= surface.get(i)[0].y && surface.get(i)[0].y <= pos2Y) {yBedeckt = true;}
			if(pos1Y >= surface.get(i)[0].y && surface.get(i)[1].y >= pos1Y) {yBedeckt = true;}
			if(xBedeckt && yBedeckt) {beidesBedeckt = true;}
		}
		return beidesBedeckt;	
	}
	 		
	public static int myRandom(int low, int high) {
		high++;
		return (int) (Math.random() * (high - low) + low);
	}
	
	int gameStufe() {
		if(wave <= 30){return 1;}
		if(wave <= 200 && wave > 30) {return 2;}
		else {return myRandom(1, 2);}
	}
	
	void gameLoop() {
		if(running) {
		main.background(0);
		main.translate(100, 0);
		map.drawMap();
		for (Building b : buildings) {b.machDeinDing();}
		for (Gegner g : ennemis) {g.machDeinDing();}
		for (Tower t : towers) {t.machDeinDing();}
		map.drawDetails();
		waveManager();
		menu.machMenue();
		checkStatus();
		clean();
		}
		else {pause();}
	}
}
