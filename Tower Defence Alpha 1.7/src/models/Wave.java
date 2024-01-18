package models;

import java.util.ArrayList;

import processing.core.PVector;
import views.Main;
import views.Game;

public class Wave {
	
	Main main;
	Game game;	
	ArrayList<int[]> gegenergroupe;
	int offsetTime;
	int waveTimer = 0;
	int groupeTimer = 0;
	int aktiveGruppeIndex = 0;

	public Wave(Main ma, ArrayList<int[]> groupeList, int offset) {
		main = ma;
		gegenergroupe = groupeList;
		offsetTime = offset;
		game = main.game();
	}
	
	void newEnnemi(int offsetPos, int type) {
		Ennemi g = new Ennemi(main);
		if(type == 1){g = new Ennemi_un(main);}
		if(type == 2){g = new Ennemi_deux(main);}
		
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

	public void affiche() {
		try {
			if(waveTimer > game.waveOffsetTime) {
			
				int[] gruppe = gegenergroupe.get(aktiveGruppeIndex);
			
				if(groupeTimer > gruppe[3]) {
					neueWelle(gruppe[0], gruppe[1], gruppe[2]);
					groupeTimer = 0;
					if(aktiveGruppeIndex >= gegenergroupe.size() - 1) {
						game.actuellewave = game.waves.get(game.waves.indexOf(game.actuellewave) + 1);
						
					} else {aktiveGruppeIndex ++;}
			
				} else {groupeTimer ++;}
			
			}	else {waveTimer ++;}
		
		} catch (Exception e) {
			System.err.println(e);
			game.actuellewave = null;
		}
	}
	
}
