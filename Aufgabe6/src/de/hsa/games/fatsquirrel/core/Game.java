package de.hsa.games.fatsquirrel.core;

import java.io.IOException;

import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.util.ui.console.ScanException;

public abstract class Game {
	
//	private static final int FPS=500;
	
	private State state;
	
	private UI ui;
	
	public Game(State state) {
		this.setState(state);
	}
	
	public UI getUi() {
		return ui;
	}

	public void setUi(UI ui) {
		this.ui = ui;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	protected abstract void render();
	
//	verarbeitet Benutzereingaben
	protected abstract void processInput() throws ScanException, IOException;
	

	protected void update() {
		state.update();
	}
	
	public void run() {
		while(true) {
//			render();
			
//			try {
//				Thread.sleep(FPS);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			try {
				processInput();
			} catch (ScanException e) {
//				e.printStackTrace();
				System.err.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.err.println("FEHLER: falsches Argument");
			}
			
//			update();
		}
	}

}
