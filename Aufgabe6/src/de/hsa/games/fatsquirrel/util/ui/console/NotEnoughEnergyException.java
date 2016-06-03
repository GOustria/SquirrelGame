package de.hsa.games.fatsquirrel.util.ui.console;

import de.hsa.games.fatsquirrel.core.MasterSquirrelBot;

public class NotEnoughEnergyException extends Exception {
	
	public NotEnoughEnergyException(String message){
		super(message);
	}
}
