package de.hsa.games.fatsquirrel.core;

public class BadBeast extends Entity {

	private static final int INIT_Energy = -150;

	private int stepInterval;

	private int biteCount;

	public BadBeast(int id, XY position) {
		super(id, INIT_Energy, position);
		biteCount = 7;
	}

	public int getBiteCount() {
		return biteCount;
	}

	public void setBiteCount(int biteCount) {
		this.biteCount = biteCount;
	}

	public int getStepInterval() {
		return stepInterval;
	}

	public void setStepInterval(int stepInterval) {
		this.stepInterval = stepInterval;
	}

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
		return "BadBeast id: " + getId() + ", Energy: " + getEnergy() + ", Position: " + getPosition().toString()
				+ ", stepInterval: " + stepInterval + ", biteCount: " + biteCount;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof BadBeast)) {
			return false;
		}
		if (o == this) {
			return true;
		}
		BadBeast bb = (BadBeast) o;
		return getId() == bb.getId() && getEnergy() == bb.getEnergy() && getPosition().equals(bb.getPosition());
	}

}
