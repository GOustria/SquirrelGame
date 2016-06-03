package de.hsa.games.fatsquirrel.core;

public class State {
	
	private int highscore;
	
	private Board board;
	
	public State(Board board) {
		this.board=board;
	}
	
	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

//	führt Veränderungen an allen Entities im Array durch
	public void update() {
		
		FlattenedBoard fb=flattenedBoard();
		for(int i=0;i<board.getSize().getX()*board.getSize().getY();i++) {
			if(board.getEntities()[i]!=null) {
				board.getEntities()[i].nextStep(fb);
			}
		}
	}
	
	public FlattenedBoard flattenedBoard() {
		return new FlattenedBoard(board);
	}

}
