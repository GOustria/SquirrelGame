package de.hsa.games.fatsquirrel.core;

public class MiniSquirrel extends PlayerEntity {

	private int parentID;

	public MiniSquirrel(int parentID, int id, int energy, XY position) {
		super(id, energy, position);
		// TODO Auto-generated constructor stub
		this.parentID = parentID;
	}

	public int getParentID() {
		return parentID;
	}

	public String toString() {
		return "MiniSquirrel id: " + getId() + ", Energy: " + getEnergy() + ", Position: " + getPosition().toString();
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (o == null) {
			return false;
		}
		if (!(o instanceof MiniSquirrel)) {
			return false;
		}
		if (o == this) {
			return true;
		}
		MiniSquirrel ms = (MiniSquirrel) o;
		return getId() == ms.getId() && getEnergy() == ms.getEnergy() && getPosition().equals(ms.getPosition())
				&& getParentID() == ms.getParentID();
	}

	@Override
	public void nextStep(EntityContext context) {
		// TODO Auto-generated method stub
		if (this.getStunned() == 0) {
			// aktuelle Position holen
			XY currentPosition = getPosition();
			// System.out.println(currentPosition);

			// nächste Position zufällig berechnen
			XY nextPosition = currentPosition.randomAdd();
			// System.out.println(nextPosition);

			// nächste Position prüfen
			context.tryMove(this, nextPosition);
		} else {
			this.setStunned(this.getStunned() - 1);
		}
	}

}
