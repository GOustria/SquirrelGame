package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.core.XY;

public enum MoveCommand {
	
	UP (new XY(0,-1)),
	DOWN (new XY(0,1)),
	RIGHT (new XY(1,0)),
	LEFT (new XY(-1,0));
	
	private XY xy;
	
	MoveCommand(XY xy) {
		this.xy=xy;
	}
	
	public XY getXY() {
		return xy;
	}

}
