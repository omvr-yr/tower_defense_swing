package models;

import views.Main;

public class Ennemi_deux extends Ennemi {
			
	public Ennemi_deux(Main ma) {
		super(ma);
		setting = main.settings("Ennemi Type2");

		health = setting.health_max;
		health_max = setting.health_max;
		DMG = setting.DMG;
		loot = setting.loot;
		speed_Max = setting.speed_Max;
		speed = setting.speed;
		drehGesch = setting.drehGesch;
		bild = setting.bild;

	}
	
	public void affiche() {
		durchkommen();
		sterben();
		bewegen();
		showTower();
		lebensleiste();
		speedReset();
	}
	
}
