package de.hsa.games.fatsquirrel.core;

public class GuidedMasterSquirrel extends MasterSquirrel {

	// Benutzereingaben
	XY input;

	// SchrittzГ¤hler
	int steps;

	public GuidedMasterSquirrel(int id, XY position) {
		super(id, position);
		// TODO Auto-generated constructor stub
		// input=new XY(0,0);
	}

	public void nextStep(EntityContext context) {
		if (input != null) {
			if (this.getStunned() == 0) {
				// nächste Position aus Input bestimmen
				XY nextPosition = getPosition().add(input);

				input = null;

				// nächste Position prГјfen
				context.tryMove(this, nextPosition);
			} else {
				this.setStunned(this.getStunned() - 1);
			}
		}
	}

	public void setInput(XY input) {
		this.input = input;
	}

	public String toString() {
		return "GuidedMasterSquirrel: " + getId() + ", Energy: " + getEnergy() + ", Betaubt: " + getStunned()
				+ ", Position: " + getPosition().toString() + ", distanceTo(1,1): "
				+ getPosition().getDistanceTo(new XY(1, 1)) + ", count: " + steps++;
	}

}
