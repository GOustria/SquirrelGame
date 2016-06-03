package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.util.ui.console.CommandTypeInfo;

public enum GameCommandType implements CommandTypeInfo {
	
	HELP("help"," * list all commands"),
	EXIT("exit"," * exit program"),
	ALL("all"," * hilfetext zu all"),
	LEFT("a"," * hilfetext zu left"),
	UP("w"," * hilfetext zu up"),
	DOWN("s"," * hilfetext zu down"),
	RIGHT("d"," * hilfetext zu right"),
	MASTER_ENERGY("master_energy"," * hilfetext zu master_energy"),
	SPAWN_MINI("spawn_mini"," * hilfetext zu spawn_mini");
	
	
	private String befehl;
	private String help;
	private Class<?>[] var;
	
	private GameCommandType(String befehl, String help, Class<?>... var){
		this.befehl = befehl;
		this.help = help;
		this.var = var;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.befehl;
	}

	@Override
	public String getHelpText() {
		// TODO Auto-generated method stub
		return this.help;
	}

	@Override
	public Class<?>[] getParamTypes() {
		// TODO Auto-generated method stub
		return this.var;
	}

}
