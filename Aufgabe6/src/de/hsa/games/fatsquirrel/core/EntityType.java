package de.hsa.games.fatsquirrel.core;

public enum EntityType {
	
	Wall ("XX"),
	GoodBeast ("GB"),
	BadBeast ("BB"),
	GoodPlant ("GP"),
	BadPlant ("BP"),
	MasterSquirrel ("MS"),
	MiniSquirrel ("NS"),
	GuidedMasterSquirrel ("MG"),
	
	MasterSquirrelBot ("MB"),
	MiniSquirrelBot ("mb");
	
	private String s;
	
	EntityType(String s) {
		this.s=s;
	}
	
	public String getSymbol() {
		return s;
	}

}
