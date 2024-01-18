package models;

import views.Main;

public class EnergyGenerator extends Building {
	double energyProduce;
	
	public EnergyGenerator(Main ma){
		super(ma);
		setting = main.settings("Energy Generator");
		
		energyProduce = setting.energyProduce;
		preis = setting.preis;
		largeur = setting.largeur;
		hauteur = setting.hauteur;
		HBbreite = setting.HBbreite;
		HBhoehe = setting.HBhoehe;
		bild = setting.icon;
	}
	
	void showTower() {
		bild = main.loadImage("Generator.png");
		main.image(bild,position.x,position.y);
		main.fill(50,50,250);
		main.fill(250);
	}
	
	public void machDeinDing() {
		if(game.energy<game.maxEnergy) {game.energy += energyProduce;}
		showTower();
	}
	
}
