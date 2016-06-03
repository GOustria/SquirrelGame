package de.hsa.games.fatsquirrel.util.ui.consoletest;

import de.hsa.games.fatsquirrel.util.ui.console.CommandTypeInfo;

public enum MyFavoriteCommandType implements CommandTypeInfo {
	
	HELP("help", " * list all commands"),
	EXIT("exit", " * exit program"),
	ADDI("addi", "<param1> <param2> * simple integer add", int.class, int.class),
	ADDF("addf", "<param1> <param2> * simple float add", float.class, float.class),
	ECHO("echo", "<param1> <param2> * echos param1 string param2 times", String.class, int.class);
	
	
	private String befehl;
	private String help;
	private Class<?>[] var;
	
	private MyFavoriteCommandType(String befehl, String help, Class<?>... var){
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
