package views;

public class TourConfig extends SidePanel{
	
	int preis;
	
	public TourConfig(Main ma, Menue me, String setName, int num) {
		super(ma, me);
		setting = main.settings(setName);
		eintrag = num;
		icon = setting.icon;
		name = setting.name;
		preis = setting.preis;
	}

	public void affiche(int o){
		positionieren(o);
		showTower_Icons_info(preis, name);
		bauen(name, preis);
	}
	
}
