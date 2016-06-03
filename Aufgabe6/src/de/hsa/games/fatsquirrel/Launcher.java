package de.hsa.games.fatsquirrel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogManager;

import de.hsa.games.fatsquirrel.console.GameImpl;
import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.Game;
import de.hsa.games.fatsquirrel.core.State;
import de.hsa.games.fatsquirrel.core.XY;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

class MyTimerTask extends TimerTask {
	GameImpl gameImpl;

	MyTimerTask(Game game) {
		this.gameImpl = (GameImpl) game;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		gameImpl.render();
		gameImpl.update();
	}

}

public class Launcher extends Application {

	public static void startGame(Game game) {
		Timer myTimer = new Timer();
		MyTimerTask myTimerTask = new MyTimerTask(game);
		myTimer.schedule(myTimerTask, 0, 100);
	}

	public static void main(String[] args) {
		// Logger über Konfigurationsdatei einstellen
		try {
			LogManager.getLogManager().readConfiguration(
					new FileInputStream("logging.properties"));
		} catch (IOException e) {
			System.err.println("Could not setup logger configuration: "
					+ e.toString());
		}

		// UI ui=new ConsoleUI(); //fГјr ConsoleUI

		// Board b=new Board(new BoardConfig(new XY(20,20)));

		// State s=new State(b);

		// GameImpl gi=new GameImpl(s);

		// Kontrollfluss von render()+update()
		// startGame(gi);

		// gi.setUi(ui); //fГјr ConsoleUI

		// Game-Loop starten
		// gi.run();

		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("The game begins now...");
		BoardConfig boardConfig = new BoardConfig(new XY(25, 25));

		Board board = new Board(boardConfig);

		State state = new State(board);

		FxUI fxUI = FxUI.createInstance(boardConfig.getSize());

		final Game game = new GameImpl(state);

		game.setUi(fxUI);

		fxUI.setGameImpl((GameImpl) game);

		primaryStage.setScene(fxUI);
		primaryStage.setTitle("Welcome to the virtual world of Squirrels.");
		primaryStage.setAlwaysOnTop(true);
		fxUI.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent evt) {
				System.exit(-1);
			}

		});
		primaryStage.show();

		startGame(game);
	}

}
