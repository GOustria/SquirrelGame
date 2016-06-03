package de.hsa.games.fatsquirrel.core;

public class BadPlant extends Entity {
	
	private static final int INIT_Energy=-100;
	
	public BadPlant(int id,XY position) {
		super(id,INIT_Energy,position);
	}
	
	public String toString() {
		return "BadPlant id: "+getId()+", Energy: "+getEnergy()+", Position: "+getPosition().toString();
	}
	
	public boolean equals(Object o) {
		if(o==null) {
			return false;
		}
		if(!(o instanceof BadPlant)) {
			return false;
		}
		if(o==this) {
			return true;
		}
		BadPlant bp=(BadPlant) o;
		return getId()==bp.getId()&&getEnergy()==bp.getEnergy()&&getPosition().equals(bp.getPosition());
	}

	@Override
	public void nextStep(EntityContext context) {
		// TODO Auto-generated method stub
		
	}

}
