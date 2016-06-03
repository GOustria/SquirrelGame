package de.hsa.games.fatsquirrel.core;

public class Wall extends Entity {
	
	private static final int INIT_Energy=-10;

	public Wall(int id,XY position) {
		super(id,INIT_Energy,position);
	}
	
	public String toString() {
		return "Wall id: "+getId()+", Energy: "+getEnergy()+", Position: "+getPosition().toString();
	}

	@Override
	public void nextStep(EntityContext context) {
		// TODO Auto-generated method stub
		
	}

}
