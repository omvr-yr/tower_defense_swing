package views;
import java.awt.Point;
import java.util.ArrayList;

import processing.core.PImage;
import processing.core.PVector;


public class Maps {
	Main main;
	Game game;
	
	int posX;
	int posY;
	int posXalt;
	int posYalt;
	public ArrayList<PVector> aire_point = new ArrayList<PVector>();
	public ArrayList<PVector> aire_dir = new ArrayList<PVector>();
	public ArrayList<Point[]> wegFlaechen = new ArrayList<Point[]>();
	public ArrayList<Point[]> wegFlaechen2 = new ArrayList<Point[]>();
	public PImage back;
	int maxWendePunkte;
	PVector	p0_pos;	PVector p1_pos;	PVector p2_pos;	PVector p3_pos;	PVector p4_pos;	PVector p5_pos;	PVector p6_pos;	PVector p7_pos; PVector p8_pos;
	PVector p0_dir;	PVector p1_dir;	PVector p2_dir;	PVector p3_dir;	PVector p4_dir;	PVector p5_dir;	PVector p6_dir;	PVector p7_dir;	PVector p8_dir;
	Point[] p0_area = new Point[2]; Point[] p1_area = new Point[2]; Point[] p2_area = new Point[2]; Point[] p3_area = new Point[2]; Point[] p4_area = new Point[2];
	Point[] p5_area = new Point[2]; Point[] p6_area = new Point[2]; Point[] p7_area = new Point[2]; Point[] p8_area = new Point[2]; Point[] rand1_area = new Point[2];
	Point[] rand2_area = new Point[2]; Point[] rand3_area = new Point[2]; Point[] rand4_area = new Point[2];
		
	public Maps(Main ma,Game sp, int Lv) {
		main = ma;
		game = sp;
		
		rand1_area[0] = new Point(-200, 0); 	rand1_area[1] = new Point(0, 920);
		rand2_area[0] = new Point(0, 720); 		rand2_area[1] = new Point(1224, 920);
		rand3_area[0] = new Point(1024, -200); 	rand3_area[1] = new Point(1224, 720);
		rand4_area[0] = new Point(-200, -200); 	rand4_area[1] = new Point(1024, 0);
	
		if(Lv == 1) {
			maxWendePunkte = 9;
			p0_pos = new PVector(696, 720);
			p1_pos = new PVector(696, 380);
			p2_pos = new PVector(532, 380);
			p3_pos = new PVector(532, 580);
			p4_pos = new PVector(184, 580);
			p5_pos = new PVector(184, 133);
			p6_pos = new PVector(838, 133);
			p7_pos = new PVector(838, 476);
			p8_pos = new PVector(1030, 476);
			
			p0_dir = new PVector(0, -1);
			p1_dir = new PVector(-1, 0);
			p2_dir = new PVector(0, 1);
			p3_dir = new PVector(-1, 0);
			p4_dir = new PVector(0, -1);
			p5_dir = new PVector(1, 0);
			p6_dir = new PVector(0, 1);
			p7_dir = new PVector(1, 0);
			p8_dir = new PVector(1, 0);

			p0_area[0] = new Point(676, 400); p0_area[1] = new Point(716, 720);
			p1_area[0] = new Point(512, 360); p1_area[1] = new Point(716, 400);
			p2_area[0] = new Point(512, 400); p2_area[1] = new Point(552, 600);
			p3_area[0] = new Point(164, 560); p3_area[1] = new Point(512, 600);
			p4_area[0] = new Point(164, 113); p4_area[1] = new Point(204, 560);
			p5_area[0] = new Point(204, 113); p5_area[1] = new Point(818, 153);
			p6_area[0] = new Point(818, 113); p6_area[1] = new Point(860, 456);
			p7_area[0] = new Point(818, 456); p7_area[1] = new Point(1024,496);
			p8_area[0] = new Point(920, 423); p8_area[1] = new Point(1024,531);
			
			back = main.loadImage("BackLv1.png");
		} else if(Lv == 2) {
			maxWendePunkte = 9;
			p0_pos = new PVector(300, 720);
			p1_pos = new PVector(300, 270);
			p2_pos = new PVector(550, 270);
			p3_pos = new PVector(550, 650);
			p4_pos = new PVector(770, 650);
			p5_pos = new PVector(770, 400);
			p6_pos = new PVector(950, 400);
			p7_pos = new PVector(950, 130);
			p8_pos = new PVector(0, 130);
			
			p0_dir = new PVector(0, -1);
			p1_dir = new PVector(1, 0);
			p2_dir = new PVector(0, 1);
			p3_dir = new PVector(1, 0);
			p4_dir = new PVector(0, -1);
			p5_dir = new PVector(1, 0);
			p6_dir = new PVector(0, -1);
			p7_dir = new PVector(-1, 0);
			p8_dir = new PVector(-1, 0);

			p0_area[0] = new Point(280, 250); p0_area[1] = new Point(320, 720);
			p1_area[0] = new Point(280, 250); p1_area[1] = new Point(570, 290);
			p2_area[0] = new Point(570, 290); p2_area[1] = new Point(530, 670);
			p3_area[0] = new Point(530, 670); p3_area[1] = new Point(790, 630);
			p4_area[0] = new Point(790, 630); p4_area[1] = new Point(750, 380);
			p5_area[0] = new Point(750, 380); p5_area[1] = new Point(970, 380);
			p6_area[0] = new Point(970, 380); p6_area[1] = new Point(930, 310);
			p7_area[0] = new Point(0, 100); p7_area[1] = new Point(930,350);
			p8_area[0] = new Point(0, 100); p8_area[1] = new Point(930,350);

			back = main.loadImage("BackLv2.png");

		}
				
		wegFlaechen.add(rand1_area); wegFlaechen.add(rand2_area); wegFlaechen.add(rand3_area); wegFlaechen.add(rand4_area);		
		
		for(int n = 0; n < maxWendePunkte; n++) {
			switch(n) {
				case 0: aire_point.add(p0_pos); aire_dir.add(p0_dir); wegFlaechen.add(p0_area); break;
				case 1: aire_point.add(p1_pos); aire_dir.add(p1_dir); wegFlaechen.add(p1_area); break;
				case 2: aire_point.add(p2_pos); aire_dir.add(p2_dir); wegFlaechen.add(p2_area); break;
				case 3: aire_point.add(p3_pos); aire_dir.add(p3_dir); wegFlaechen.add(p3_area); break;
				case 4: aire_point.add(p4_pos); aire_dir.add(p4_dir); wegFlaechen.add(p4_area); break;
				case 5: aire_point.add(p5_pos); aire_dir.add(p5_dir); wegFlaechen.add(p5_area); break;
				case 6: aire_point.add(p6_pos); aire_dir.add(p6_dir); wegFlaechen.add(p6_area); break;
				case 7: aire_point.add(p7_pos); aire_dir.add(p7_dir); wegFlaechen.add(p7_area); break;
				case 8: aire_point.add(p8_pos); aire_dir.add(p8_dir); wegFlaechen.add(p8_area); break;			
			}
		}
	}
	
	public ArrayList<PVector> getWendepunkte_pos() {
		return aire_point;
	}
	
	public ArrayList<PVector> getWendepunkte_dir() {
		return aire_dir;
	}
	
	public ArrayList<Point[]> getWegFlaechen() {
		return wegFlaechen;
	}
	
	void drawMap() {
		main.image(back, 0, 0);
	}
	
	void drawDetails(){
	}

}
	

