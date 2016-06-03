package de.hsa.games.fatsquirrel.core;

public abstract class PlayerEntity extends Entity {
	
	private int stunned;

	public PlayerEntity(int id, int energy, XY position) {
		super(id, energy, position);
		// TODO Auto-generated constructor stub
	}

	public int getStunned() {
		return stunned;
	}

	public void setStunned(int stunned) {
		this.stunned = stunned;
	}

}
