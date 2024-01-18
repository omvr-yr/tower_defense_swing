package models;
import processing.core.PImage;
import processing.core.PVector;
import views.Main;
import views.Menue;
import views.Game;

public class Building {
	Main main;
	Menue menu;
	Game game;
	Settings setting;
	
	public PVector position;
    public int largeur;
    public int hauteur;
    public int HBbreite;
    public int HBhoehe;
    public int preis;
	PImage bild;
	
	public Building(Main ma) {
		main = ma;
		game = main.game();		
	}
	
	public void setPosition(PVector startPos) {
		position = startPos;
		
	}
	

	void showTower() {
		main.fill(250,150,150);
		main.rect(position.x, position.y, largeur, hauteur);
	}

	public void affiche() {
		showTower();
	}


	
}
