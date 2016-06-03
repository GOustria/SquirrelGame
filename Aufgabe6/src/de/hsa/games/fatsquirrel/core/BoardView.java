package de.hsa.games.fatsquirrel.core;

public interface BoardView {
	
	public abstract EntityType getEntityType(int x,int y);
	
	public abstract XY getSize();

}
