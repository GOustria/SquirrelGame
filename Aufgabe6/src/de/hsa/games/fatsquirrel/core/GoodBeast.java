package de.hsa.games.fatsquirrel.core;

public class GoodBeast extends Entity {

	private static final int INIT_Energy = 200;

	private int stepInterval;

	public GoodBeast(int id, XY position) {
		super(id, INIT_Energy, position);
	}

	public int getStepInterval() {
		return stepInterval;
	}

	public void setStepInterval(int stepInterval) {
		this.stepInterval = stepInterval;
	}

	@Override
	public void nextStep(EntityContext context) {
		if (this.getStepInterval() == 0) {
			// aktuelle Position holen
			XY currentPosition = getPosition();
			// System.out.println(currentPosition);

			// nächste Position zufällig berechnen
			XY nextPosition = currentPosition.randomAdd();
			// System.out.println(nextPosition);

			// nächste Position prüfen
			context.tryMove(this, nextPosition);
		} else {
			this.setStepInterval(this.getStepInterval() - 1);
		}
	}

	public String toString() {
		return "GoodBeast id: " + getId() + ", Energy: " + getEnergy() + ", Position: " + getPosition().toString();
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof GoodBeast)) {
			return false;
		}
		if (o == this) {
			return true;
		}
		GoodBeast gb = (GoodBeast) o;
		return getId() == gb.getId() && getEnergy() == gb.getEnergy() && getPosition().equals(gb.getPosition());
	}

}
