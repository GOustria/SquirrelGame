package de.hsa.games.fatsquirrel.core;

public class BoardConfig {
	
	//Größe des Spielfeldes
	private XY size;
	
	//Anzahl von verschiedenen Entities auf dem Spielfeld
	private static final int INIT_MasterSquirrel=2;
	private static final int INIT_GoodBeast=10; //5
	private static final int INIT_BadBeast=1; //1
	private static final int INIT_GoodPlant=2;
	private static final int INIT_BadPlant=2;
	private static final int INIT_Wall=5;
	
	public BoardConfig(XY size) {
		this.size=size;
	}
	
	public int getInitMS() {
		return INIT_MasterSquirrel;
	}
	
	public int getInitGB() {
		return INIT_GoodBeast;
	}
	
	public int getInitBB() {
		return INIT_BadBeast;
	}
	
	public int getInitGP() {
		return INIT_GoodPlant;
	}
	
	public int getInitBP() {
		return INIT_BadPlant;
	}
	
	public int getInitW() {
		return INIT_Wall;
	}
	
	public int getInitMB() {
		return INIT_MasterSquirrel;
	}
	
	public XY getSize() {
		return size;
	}
	
	public String toString() {
		return "BoardConfig, Size: "+size.getX()+"x"+size.getY();
	}

}
