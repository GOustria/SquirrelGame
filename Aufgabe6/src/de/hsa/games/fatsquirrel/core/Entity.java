package de.hsa.games.fatsquirrel.core;

public abstract class Entity {
	
	private int id;
	private int energy;
	private XY position;
	
	public Entity(int id,int energy,XY position) {
		this.id=id;
		this.energy=energy;
		this.position=position;
	}
	
	public int getId() {
		return id;
	}
	public int getEnergy() {
		return energy;
	}
	
	public XY getPosition() {
		return position;
	}
	
	public void setPosition(XY position) {
		this.position=position;
	}
	
	public void updateEnergy(int energy) {
		this.energy+=energy;
	}
	
	public abstract void nextStep(EntityContext context);
	
	public boolean equals(Object o) {
		if(o==null) {
			return false;
		}
		if(!(o instanceof Entity)) {
			return false;
		}
		if(o==this) {
			return true;
		}
		Entity e=(Entity) o;
		return getId()==e.getId()&&getEnergy()==e.getEnergy()&&getPosition().equals(e.getPosition());
	}
	
	public String toString() {
		return "Entity id: "+getId()+", Energy: "+getEnergy()+", Position: "+getPosition().toString();
	}

}
