package views;

import models.Tower;

public class InfoLeiste {

	Main main;
	Menue menu;
	
	public InfoLeiste(Main ma, Menue me) {
		main = ma;
		menu = me;
	}
	
	public void machDeinDing() {
		if(menu.infoObjekt != null) {((Tower) menu.infoObjekt).infoDisplay();}
	}	
}
