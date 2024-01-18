package views;

import java.awt.Cursor;
import java.awt.Image;
import java.util.ArrayList;

import models.Settings;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.event.MouseEvent;

@SuppressWarnings("serial")
public class Main extends PApplet {
	static public void main(String args[]) {
		PApplet.main(new String [] { "views.Main" } );
	}
	public Game game;
	HautMenu hautmenu;
	public Steuerung steuerung;
	
	ArrayList<Settings> settingObjekte = new ArrayList<Settings>();
	PImage icon = loadImage("icon2.png");
	public String version = "";
	public int largeur = 1124;
	public int hauteur = 820;
	public float mausScrolling; 
	boolean GameActive = false;
	Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
		
	public Game game() {
		return game;
	}
	
	public HautMenu hautmenu() {
		return hautmenu;
	}
	
	public Settings settings(String art) {
		Object returnOb = null;
		for(Settings sO: settingObjekte) {
			if(sO.name == art) {returnOb = sO;}
		}
		return (Settings) returnOb;
	}
	
	public Steuerung steuerung() {
		return steuerung;
	}
	
	public void gameStart(int level, int map) {
		game = new Game(this, level, map);
		game.neuesGame(level, map);
	}
	
	void settingErstellen() {
		Settings LT_settings = new Settings(this,"LaserTower"); settingObjekte.add(LT_settings);
		Settings GT1_settings = new Settings(this,"Ennemi_un"); settingObjekte.add(GT1_settings);
		Settings GT2_settings = new Settings(this,"Ennemi_deux"); settingObjekte.add(GT2_settings);
		Settings BT_settings = new Settings(this,"BlitzTower"); settingObjekte.add(BT_settings);
		Settings EG_settings = new Settings(this,"EnergyGenerator"); settingObjekte.add(EG_settings);
		Settings ST_settings = new Settings(this,"SchussTower"); settingObjekte.add(ST_settings);
	}
	
	public void setup() {
		steuerung = new Steuerung(this);
		settingErstellen();
		hautmenu = new HautMenu(this);
		frame.setTitle("Tower Defense");
		frame.setIconImage((Image) icon.getNative());
		frame.setCursor(cursor);
		//frame.setResizable(true);
		size(largeur, hauteur);
		noStroke();
		fill(255);
		background(0);
		Settings.testImage = loadImage("GegnerLv2.png");
	}

	public void keyPressed() {
		if(keyCode == PConstants.ESC) {
			if(game == null) {keyCode = 0; key = 0;}
			else {keyCode = 0; key = 0; 
				if(game.running) {game.running = false; game.secours = true; delay(200);}
				else {game.running = true; game.secours = true; delay(200);}
			}
		}
	}
	
	private void inputRest() {
		key = 0;
		keyCode = 0;
		mouseButton = 0;
		mausScrolling = 0;
	}
	
	public void mouseWheel(MouseEvent event) {
		mausScrolling = event.getCount();
	}
	
	public void mouseReleased() {
		steuerung.released = true;
		steuerung.pressed = false;
	}
	
	public void mousePressed() {
		steuerung.released = false;
		steuerung.pressed = true;
	}
	
	public void delay(int time) {
		try {
		    Thread.sleep(time);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	public void draw() {
		if(GameActive) {game.gameLoop();}
		else {hautmenu.mainMenue();}
		inputRest();
    }

}