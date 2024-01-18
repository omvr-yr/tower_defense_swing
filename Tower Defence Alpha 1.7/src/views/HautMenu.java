package views;

import processing.core.PConstants;
import processing.core.PImage;

public class HautMenu {
	Main main;
	Steuerung steuerung;

	PImage startBild;
	PImage gameOverBild;
	int eintrag = 0;

	int level;
	int nb_map;
	
	public HautMenu(Main ma) {
		main = ma;
		steuerung = main.steuerung();
		
		startBild = main.loadImage("Background.png");
		gameOverBild = main.loadImage("GameOver.png");
	}
	
	void mainMenue() {
		main.background(245, 214, 115);
		main.image(startBild,70,0);
		main.fill(255);
		main.textSize(35);
		
		if(steuerung.mausbedeckt(950, 540, 300, 50)) {eintrag = 1;}
		else if(steuerung.mausbedeckt(820, 110, 340, 50)) {eintrag = 2;}
		else if(steuerung.mausbedeckt(300, 280, 200, 50)) {eintrag = 3;}
		else if(steuerung.mausbedeckt(400, 280, 300, 50)) {eintrag = 4;}
		else if(steuerung.mausbedeckt(750, 280, 340, 50)) {eintrag = 5;}
		else if(steuerung.mausbedeckt(400, 390, 200, 50)) {eintrag = 6;}
		else if(steuerung.mausbedeckt(620, 390, 340, 50)) {eintrag = 7;}
		else {eintrag = 0;}
		
		if(steuerung.click("links")) {mainMenueWeiter(); main.delay(200);}
				
		if(eintrag == 1) {main.fill(0,220,255);}
		main.text("Start",950, 580);
		main.fill(255);
		
		if(eintrag == 2) {main.fill(0,220,255);}
		main.text("Exit",820, 150);
		main.fill(255);

		main.textSize(30);
		if(eintrag == 3) {main.fill(0,220,255); level=1;}
		main.text("Débutant",300, 320);
		main.fill(255);

		if(eintrag == 4) {main.fill(0,220,255); level=2;}
		main.text("Intermédiaire",500, 320);
		main.fill(255);

		if(eintrag == 5) {main.fill(0,220,255); level=3;}
		main.text("Expert",750, 320);
		main.fill(255);

		main.textSize(30);
		if(eintrag == 6) {main.fill(0,220,255); nb_map=1;}
		main.text("Cyberpunk",400, 430);
		main.fill(255);

		if(eintrag == 7) {main.fill(0,220,255); nb_map=2;}
		main.text("Minecraft",620, 430);
		main.fill(255);

		main.textSize(20);
		main.text("POOIG Project by Omar & Sami", 70, 800);

		String niveau = "";
		if(level == 1){
			niveau = "Débutant";
		} else if (level == 2){
			niveau = "Intermédiaire";
		} else if (level == 3){
			niveau = "Expert";
		} else {
			niveau = "aucun";
		}

		main.textSize(20);
		main.text("Niveau selectionné : " + niveau, 780, 800);
	}

	void mainMenueWeiter() {
		switch (eintrag) {
			case 1: main.gameStart(level, nb_map); main.GameActive = true; main.keyCode = 0; break;
			case 2: main.exit(); break;
		}
	}

	void gameOverScreen() {
		main.image(gameOverBild, 0, 0);
		main.keyPressed();
		main.mouseClicked();
		if(main.keyPressed || main.mousePressed); {
			if(main.keyCode == PConstants.ENTER || main.mouseButton == PConstants.LEFT) {
				main.GameActive = false;
				eintrag = 1;
				main.delay(600);
			}
		}
		main.mouseButton = 0;
		main.keyCode = 0;
	}
}
