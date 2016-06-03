package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.console.GameImpl;
import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.MasterSquirrelBot;
import de.hsa.games.fatsquirrel.core.XY;
import de.hsa.games.fatsquirrel.util.ui.console.NotEnoughEnergyException;
import de.hsa.games.fatsquirrel.util.ui.consoletest.Command;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FxUI extends Scene implements UI {

	private static final int CELL_SIZE = 25;
	private Canvas boardCanvas;
	private Label msgLabel;
	private GameImpl gameimpl;

	public FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
		super(parent);
		this.boardCanvas = boardCanvas;
		this.msgLabel = msgLabel;
	}

	public static FxUI createInstance(XY boardSize) {
		Canvas boardCanvas = new Canvas(boardSize.getX() * CELL_SIZE,
				boardSize.getY() * CELL_SIZE);
		Label statusLabel = new Label();
		VBox top = new VBox();
		top.getChildren().add(boardCanvas);
		top.getChildren().add(statusLabel);
		statusLabel.setText("Hier werden Informationen angezeigt!");
		final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel);
		
		//GUI für Bot-Auswahl:
		GridPane botPane = new GridPane();
		Scene botScene = new Scene(botPane);
		Stage botStage = new Stage();
		botStage.setScene(botScene);
		botStage.setX(400);
		botStage.setY(100);
		botPane.getChildren().add(fxUI.getBotButtons());
		botStage.setResizable(false);
//		botStage.show();
		
		
		fxUI.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub
				arg0.getCode().toString();
				
				switch(arg0.getCode().toString()){
				case "A":
					fxUI.gameimpl.left();
					break;
				case "S":
					fxUI.gameimpl.down();
					break;
				case "D":
					fxUI.gameimpl.right();
					break;
				case "W":
					fxUI.gameimpl.up();
					break;
				case "Z":
					try {
						fxUI.gameimpl.spawn_mini();
					} catch (NotEnoughEnergyException e) {
						e.printStackTrace();
						fxUI.msgLabel.setText(e.getMessage());
					}					
					break;
				case "E":
					GridPane creditsPane= new GridPane();
					creditsPane.getChildren().add(new Label(fxUI.gameimpl.master_energy()));
					creditsPane.setMinSize(200, 400);
					Scene creditsScene = new Scene(creditsPane);
					Stage creditsStage = new Stage();
					creditsStage.setScene(creditsScene);
					creditsStage.setX(1300);
					creditsStage.setY(300);
					creditsStage.setResizable(false);
					creditsStage.show();
					fxUI.gameimpl.master_energy();
					break;
				case "B":
					botStage.show();
					break;
				case "H":
					fxUI.gameimpl.help();
					break;
				case "Q":
					fxUI.gameimpl.exit();
					break;
				}
				
			}
		});

		return fxUI;
	}

	@Override
	public Command getCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void render(final BoardView view) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				repaintBoardCanvas(view);
			}
		});

	}
	
	public void setGameImpl(GameImpl game){
		this.gameimpl = game;
	}

	private void repaintBoardCanvas(BoardView view) {
		GraphicsContext gc = boardCanvas.getGraphicsContext2D();
		gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
		XY viewSize = view.getSize();

		// dummy for rendering a board snapshot, TODO: change it!
		// gc.fillText("Where are the beasts?", 100, 100);
		// gc.setFill(Color.RED);
		// gc.fillOval(viewSize.getX(),viewSize.getY(), 50, 50);

		// hier ergÃ¤nzen
		for (int y = 0; y < viewSize.getY(); y++) {
			for (int x = 0; x < viewSize.getX(); x++) {
				if (view.getEntityType(x, y) == null) {
					// kein Entity
				} else {
					// Entity zeichnen

					if (view.getEntityType(x, y) == EntityType.Wall) {
						gc.setFill(Color.YELLOW);
						gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE,
								CELL_SIZE);
					}
					if (view.getEntityType(x, y) == EntityType.GuidedMasterSquirrel) {
						gc.setFill(Color.BLUE);
						gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE,
								CELL_SIZE);
					}
					if (view.getEntityType(x, y) == EntityType.BadBeast) {
						gc.setFill(Color.RED);
						gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE,
								CELL_SIZE);
					}
					if (view.getEntityType(x, y) == EntityType.GoodBeast) {
						gc.setFill(Color.GREEN);
						gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE,
								CELL_SIZE);
					}
					if (view.getEntityType(x, y) == EntityType.GoodPlant) {
						gc.setFill(Color.GREEN);
						gc.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE,
								CELL_SIZE);
					}
					if (view.getEntityType(x, y) == EntityType.BadPlant) {
						gc.setFill(Color.RED);
						gc.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE,
								CELL_SIZE);
					}
					if(view.getEntityType(x, y) == EntityType.MiniSquirrel) {
						gc.setFill(Color.BLACK);
						gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, 
								CELL_SIZE);
					}
					if(view.getEntityType(x, y) == EntityType.MasterSquirrel) {
						gc.setFill(Color.BROWN);
						gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, 
								CELL_SIZE);
					}
					if(view.getEntityType(x, y) == EntityType.MasterSquirrelBot) {
						gc.setFill(Color.PURPLE);
						gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, 
								CELL_SIZE);
					}
				}
			}
		}
	}

	@Override
	public void message(final String msg) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				msgLabel.setText(msg);
			}
		});

	}
	
	private VBox getBotButtons() {
		
		VBox box = new VBox();
		
		box.getChildren().add(new Label("Wähle ein Charakter aus: "));
		Button button1 = new Button("AGGRESSIVE");
		button1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Board board = gameimpl.getState().getBoard();
				board.addEntity(new MasterSquirrelBot(board.getEntityID()+1, 
						new XY(0,0).randomSet(board.getSize()), "aggressive"));
			}
		});
		
		Button button2 = new Button("CALM");
		button2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Board board = gameimpl.getState().getBoard();
				board.addEntity(new MasterSquirrelBot(board.getEntityID()+1, 
						new XY(0,0).randomSet(board.getSize()), "calm"));
			}
		});
		
		Button button3 = new Button("CRAZY");
		button3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Board board = gameimpl.getState().getBoard();
				board.addEntity(new MasterSquirrelBot(board.getEntityID()+1, 
						new XY(0,0).randomSet(board.getSize()), "crazy"));
			}
		});
		
		box.getChildren().add(button1);
		box.getChildren().add(button2);
		box.getChildren().add(button3);
		
		return box;
	}

}
