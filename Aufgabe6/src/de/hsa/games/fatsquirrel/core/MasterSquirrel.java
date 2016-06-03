package de.hsa.games.fatsquirrel.core;

import java.lang.reflect.Proxy;

import de.hsa.games.fatsquirrel.proxy.DebugHandler;

public class MasterSquirrel extends PlayerEntity {

	private static final int INIT_Energy = 1000;

	public MasterSquirrel(int id, XY position) {
		super(id, INIT_Energy, position);
	}

	public String toString() {
		return "MasterSquirrel: " + getId() + ", Energy: " + getEnergy() + ", Betaubt: " + getStunned() + ", Position: "
				+ getPosition().toString();
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof MasterSquirrel)) {
			return false;
		}
		if (o == this) {
			return true;
		}
		MasterSquirrel ms = (MasterSquirrel) o;
		return getId() == ms.getId() && getEnergy() == ms.getEnergy() && getPosition().equals(ms.getPosition());
	}

	@Override
	public void nextStep(EntityContext context) {
		// TODO Auto-generated method stub
		if (this.getStunned() == 0) {
			// aktuelle Position holen
			XY currentPosition = getPosition();
			// System.out.println(currentPosition);

			// nächste Position zufГ¤llig berechnen
			XY nextPosition = currentPosition.randomAdd();
			// System.out.println(nextPosition);

			// nächste Position prГјfen
//			context.tryMove(this, nextPosition);
			
			// nextPositionAufruf über Proxy
			DebugHandler handler = new DebugHandler(context);
			EntityContext  entity = (EntityContext ) Proxy.newProxyInstance(
	            EntityContext.class.getClassLoader(),
	            new Class[] { EntityContext.class },
	            handler);
			
			
			entity.tryMove(this, nextPosition);
			
		} else {
			this.setStunned(this.getStunned() - 1);
		}
	}

}
