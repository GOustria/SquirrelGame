package de.hsa.games.fatsquirrel.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import de.hsa.games.fatsquirrel.core.Game;
import de.hsa.games.fatsquirrel.core.GuidedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.MasterSquirrel;
import de.hsa.games.fatsquirrel.core.MiniSquirrel;
import de.hsa.games.fatsquirrel.core.State;
import de.hsa.games.fatsquirrel.core.XY;
import de.hsa.games.fatsquirrel.util.ui.console.CommandScanner;
import de.hsa.games.fatsquirrel.util.ui.console.CommandTypeInfo;
import de.hsa.games.fatsquirrel.util.ui.console.NotEnoughEnergyException;
import de.hsa.games.fatsquirrel.util.ui.console.ScanException;
import de.hsa.games.fatsquirrel.util.ui.consoletest.Command;

public class GameImpl extends Game {

	private List<MasterSquirrel> squirrelBots = new ArrayList<MasterSquirrel>();
	private GuidedMasterSquirrel guidedMasterSquirrel;

	// private int masterEnergy;

	public GameImpl(State state, MasterSquirrel... masterSquirrel) {
		super(state);
		// TODO Auto-generated constructor stub

		this.guidedMasterSquirrel = new GuidedMasterSquirrel(state.getBoard()
				.getEntityID(), new XY(1, 1));
		this.getState().getBoard().addEntity(guidedMasterSquirrel);

		for (MasterSquirrel squirrel : masterSquirrel) {
			this.getState().getBoard().addEntity(squirrel);
			this.squirrelBots.add(squirrel);
		}

		// System.out.println(state.getBoard().toString());
	}

	// public GuidedMasterSquirrel getMasterSquirrel() {
	// return masterSquirrel;
	// }
	//
	// public void setMasterSquirrel(GuidedMasterSquirrel masterSquirrel) {
	// this.masterSquirrel = masterSquirrel;
	// }

	@Override
	public void render() {
		// TODO Auto-generated method stub
		this.getUi().render(this.getState().flattenedBoard());

		// bei mindestens einem MS gibt seine Daten aus
		// System.out.println(masterSquirrel.toString()+"\n");
	}

	@Override
	public void processInput() throws ScanException, IOException {
		// TODO Auto-generated method stub
		// masterSquirrel.setInput(getUi().getCommand().getXY());

		CommandScanner commandScanner = new CommandScanner(
				GameCommandType.values(), new BufferedReader(
						new InputStreamReader(System.in)));

		Class<?> processorClass = null;

		// GameImpl als processorClass laden
		try {
			processorClass = Class.forName(this.getClass().getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Command command = commandScanner.next(); // einlesen des Kommandos von
													// der Konsole

		Object[] params = command.getParams(); // Parameter des Kommandos werden
												// in Objectarray "params"
												// gespeichert

		GameCommandType commandType = (GameCommandType) command
				.getCommandType(); // commandType zwischenspeichern

		Object[] castedParams = new Object[params.length]; // gecastete Params
															// werden
															// gespeichert

		for (int i = 0; i < params.length; i++) {
			if (commandType.getParamTypes()[i] == int.class) {
				castedParams[i] = Integer.parseInt((String) params[i]);
			} else if (commandType.getParamTypes()[i] == float.class) {
				castedParams[i] = Float.parseFloat((String) params[i]);
			} else {
				castedParams[i] = params[i];
			}
		}

		Method method = null;

		try {
			method = processorClass.getMethod(commandType.name().toLowerCase(),
					commandType.getParamTypes());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			method.invoke(this, castedParams);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void help() {
		for (CommandTypeInfo cti : GameCommandType.values()) {
			System.out.println(">> " + cti.getName() + cti.getHelpText());
		}
		guidedMasterSquirrel.setInput(new XY(0, 0));
	}

	// public String helpForGUI() {
	// String str = "";
	// for (CommandTypeInfo cti : GameCommandType.values()) {
	// str += ">> " + cti.getName() + cti.getHelpText() + "\n";
	// }
	// guidedMasterSquirrel.setInput(new XY(0, 0));
	//
	// return str;
	// }

	public void exit() {
		System.out.println("The program says 'Bye' ...");
		System.exit(0);
	}

	public void all() {
		System.out.println("ALL");

		guidedMasterSquirrel.setInput(new XY(0, 0));
	}

	public void left() {
		guidedMasterSquirrel.setInput(new XY(-1, 0));
	}

	public void up() {
		guidedMasterSquirrel.setInput(new XY(0, -1));
	}

	public void down() {
		guidedMasterSquirrel.setInput(new XY(0, 1));
	}

	public void right() {
		guidedMasterSquirrel.setInput(new XY(1, 0));
	}

	public String master_energy() {
		System.out.println("MASTER_ENERGY");

		if (guidedMasterSquirrel == null)
			return "There is no MasterSquirrel in the game.";
		// masterSquirrel.setInput(new XY(0,0));
		return " GuidedMasterSquirrel \r\n Energie: "
				+ guidedMasterSquirrel.getEnergy();
	}

	public void spawn_mini() throws NotEnoughEnergyException {
		System.out.println("SPAWN_MINI");
		int minienergy = 199;
		if (guidedMasterSquirrel.getEnergy() > minienergy) {
			MiniSquirrel miniSquirrel = new MiniSquirrel(
					guidedMasterSquirrel.getId(), this.getState().getBoard()
							.getEntityID() + 1, minienergy,
					new XY(0, 0)
							.randomSet(this.getState().getBoard().getSize()));
			this.getState().getBoard().addEntity(miniSquirrel);
			guidedMasterSquirrel.updateEnergy(-minienergy);
		} else
			throw new NotEnoughEnergyException("Not enough energy to spawn mini.");

			guidedMasterSquirrel.setInput(new XY(0, 0));
	}

	public void update() {
		this.getState().update();
	}

}
