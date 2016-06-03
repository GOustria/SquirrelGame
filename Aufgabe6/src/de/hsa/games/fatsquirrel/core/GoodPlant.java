package de.hsa.games.fatsquirrel.core;

public class GoodPlant extends Entity {
	
	private static final int INIT_Energy=100;
	
	public GoodPlant(int id,XY position) {
		super(id,INIT_Energy,position);
	}
	
	public String toString() {
		return "GoodPlant id: "+getId()+", Energy: "+getEnergy()+", Position: "+getPosition().toString();
	}
	
	public boolean equals(Object o) {
		if(o==null) {
			return false;
		}
		if(!(o instanceof GoodPlant)) {
			return false;
		}
		if(o==this) {
			return true;
		}
		GoodPlant gp=(GoodPlant) o;
		return getId()==gp.getId()&&getEnergy()==gp.getEnergy()&&getPosition().equals(gp.getPosition());
	}

	@Override
	public void nextStep(EntityContext context) {
		// TODO Auto-generated method stub
		
	}

}
