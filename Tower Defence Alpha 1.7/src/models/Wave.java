package models;

import java.util.ArrayList;

import processing.core.PVector;
import views.Main;
import views.Game;

public class Wave {
	
	Main main;
	Game game;	
	ArrayList<int[]> gegenerGruppen;
	int offsetTime;
	int waveTimer = 0;
	int gruppenTimer = 0;
	int aktiveGruppeIndex = 0;

	public Wave(Main ma, ArrayList<int[]> gruppenList, int offset) {
		main = ma;
		gegenerGruppen = gruppenList;
		offsetTime = offset;
		game = main.game();
	}
	
	void newEnnemi(int offsetPos, int type) {
		Gegner g = new Gegner(main);
		if(type == 1){g = new GegnerType1(main);}
		if(type == 2){g = new GegnerType2(main);}
		
		g.direction = game.aire_dir.get(0);
		PVector spawnPosition = new PVector();
		spawnPosition.x = game.aire_point.get(0).x + (game.aire_dir.get(0).x * -offsetPos);
		spawnPosition.y = game.aire_point.get(0).y + (game.aire_dir.get(0).y * -offsetPos);
		g.setPosition(spawnPosition);
		game.ennemis.add(g);
		g.init_ausrichtung();
	}
	void neueWelle(int type, int laenge, int offset) {
		for(int i = 1; i <= laenge; i++) {
			newEnnemi(i*offset, type);
		}
	}

	public void machDeinDing() {
		try {
			if(waveTimer > game.waveOffsetTime) {
			
				int[] gruppe = gegenerGruppen.get(aktiveGruppeIndex);
			
				if(gruppenTimer > gruppe[3]) {
					neueWelle(gruppe[0], gruppe[1], gruppe[2]);
					gruppenTimer = 0;
					if(aktiveGruppeIndex >= gegenerGruppen.size() - 1) {
						game.actuellewave = game.waves.get(game.waves.indexOf(game.actuellewave) + 1);
						
					} else {aktiveGruppeIndex ++;}
			
				} else {gruppenTimer ++;}
			
			}	else {waveTimer ++;}
		
		} catch (Exception e) {
			System.err.println(e);
			game.actuellewave = null;
		}
	}
	
}
