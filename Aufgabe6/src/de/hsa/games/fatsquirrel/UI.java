package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.util.ui.consoletest.Command;

public interface UI {
	
//	public abstract MoveCommand getCommand();
	
	public abstract Command getCommand();
	
	public abstract void render(BoardView view);
	
	public abstract void message(String msg);

}
