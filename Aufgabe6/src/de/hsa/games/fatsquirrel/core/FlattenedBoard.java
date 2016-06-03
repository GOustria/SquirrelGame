package de.hsa.games.fatsquirrel.core;

import java.util.Random;
import java.util.logging.Logger;

public class FlattenedBoard implements BoardView, EntityContext {

	private Board board;

	private Entity[][] cells;
	
	Logger log;

	public FlattenedBoard(Board board) {
		this.board = board;
		
		int sizeX = board.getSize().getX();
		int sizeY = board.getSize().getY();
		
		cells = new Entity[sizeX][sizeY];
		for (int i = 0; i < board.getEntities().length; i++) {
			if (board.getEntities()[i] != null) {
				cells[board.getEntities()[i].getPosition().getX()][board.getEntities()[i].getPosition().getY()] = board
						.getEntities()[i];
			}
		}
		
		log = Logger.getLogger(FlattenedBoard.class.getName());
	}

	@Override
	public void tryMove(GoodBeast goodBeast, XY moveDirection) {
		// TODO Auto-generated method stub
		// goodBest lokalisiert Jäger
		if (this.nearestPlayerEntity(moveDirection) != null) {
			int hunterX = this.nearestPlayerEntity(moveDirection).getPosition().getX();
			int hunterY = this.nearestPlayerEntity(moveDirection).getPosition().getY();
			int deltaX = hunterX - goodBeast.getPosition().getX();
			int deltaY = hunterY - goodBeast.getPosition().getY();
			// Fluchtvektor berechnen
			int x, y;
			if (deltaX == 0) {
				x = 0;
			} else if (deltaX > 0) {
				x = 1;
			} else {
				x = -1;
			}

			if (deltaY == 0) {
				y = 0;
			} else if (deltaY > 0) {
				y = 1;
			} else {
				y = -1;
			}
			// Fluchtvektor in Gegenrichtung anwenden
			moveDirection = goodBeast.getPosition().add(-x, -y);
		}

		// was ist vor uns
		EntityType et = getEntityType(moveDirection);
		Entity e = cells[moveDirection.getX()][moveDirection.getY()];

		if (et != null) {
			switch (et) {
			case MiniSquirrel:
			case MasterSquirrel:
			case GuidedMasterSquirrel: {
				e.updateEnergy(goodBeast.getEnergy());
				killAndReplace(goodBeast);
			}
				break;
			default:
				break;
			}
		} else {
			goodBeast.setPosition(moveDirection);
			goodBeast.setStepInterval(4);
		}
	}

	@Override
	public void tryMove(BadBeast badBeast, XY moveDirection) {
		// TODO Auto-generated method stub

		// badBeast lokalisiert Beute
		if (this.nearestPlayerEntity(moveDirection) != null) {
			int preyX = this.nearestPlayerEntity(moveDirection).getPosition().getX();
			int preyY = this.nearestPlayerEntity(moveDirection).getPosition().getY();
			int deltaX = preyX - badBeast.getPosition().getX();
			int deltaY = preyY - badBeast.getPosition().getY();
			// Angriffsvektor berechnen
			int x, y;
			if (deltaX == 0) {
				x = 0;
			} else if (deltaX > 0) {
				x = 1;
			} else {
				x = -1;
			}

			if (deltaY == 0) {
				y = 0;
			} else if (deltaY > 0) {
				y = 1;
			} else {
				y = -1;
			}
			// Angriffsvektor in gleiche Richtung anwenden
			moveDirection = badBeast.getPosition().add(x, y);
		}

		// was ist vor uns
		EntityType et = getEntityType(moveDirection);
		Entity e = cells[moveDirection.getX()][moveDirection.getY()];

		if (et != null) {
			switch (et) {
			case MiniSquirrel:
			case GuidedMasterSquirrel:
			case MasterSquirrel: {
				e.updateEnergy(badBeast.getEnergy());
				badBeast.setBiteCount(badBeast.getBiteCount() - 1);
				if (badBeast.getBiteCount() == 0) {
					killAndReplace(badBeast);
				}
				
				log.info(badBeast.toString() + " >>> von BadBeast gebissen");
			}
				break;
			default:
				break;
			}
		} else {
			badBeast.setPosition(moveDirection);
			badBeast.setStepInterval(4);
		}
	}

	@Override
	public void tryMove(MasterSquirrel masterBot, XY moveDirection) {
		// TODO Auto-generated method stub

		// was ist vor uns

		EntityType et = getEntityType(moveDirection);
		Entity e = cells[moveDirection.getX()][moveDirection.getY()];

		if (et != null) {
			switch (et) {
			case Wall: {
				// System.out.println(masterBot.toString()+", WAND!!!...STOP");
				masterBot.updateEnergy(board.getEntityAtPosition(moveDirection).getEnergy());
				// Schritt wird NICHT ausgeführt, Betäubung auf 3 Schritte
				// setzen
				masterBot.setStunned(3);
				
				log.info(masterBot.toString() + " >>> lauft gegen die Wand");
			}
				break;
			case GoodPlant: {
				// System.out.println(masterBot.toString()+",
				// GoodPlant...fressen");
				masterBot.updateEnergy(e.getEnergy());
				masterBot.setPosition(moveDirection);
				killAndReplace(e);
				
				log.info(masterBot.toString() + " >>> GoodPlant konsumiert");
			}
				break;
			case BadPlant: {
				// System.out.println(masterBot.toString()+",
				// BadPlant...kotzen");
				masterBot.updateEnergy(e.getEnergy());
				masterBot.setPosition(moveDirection);
				killAndReplace(e);
				
				log.info(masterBot.toString() + " >>> BadPlant konsumiert");
			}
				break;
			case GoodBeast: {
				// System.out.println(masterBot.toString()+",
				// GoodBeast...fressen");
				masterBot.updateEnergy(e.getEnergy());
				masterBot.setPosition(moveDirection);
				killAndReplace(e);
				
				log.info(masterBot.toString() + " >>> GoodBeast konsumiert");
			}
				break;
			case BadBeast: {
				// System.out.println(masterBot.toString()+", BadBeast...wurde
				// gebissen");
				masterBot.updateEnergy(e.getEnergy());

				BadBeast temp = (BadBeast) e;
				temp.setBiteCount(temp.getBiteCount() - 1);
				if (temp.getBiteCount() == 0) {
					killAndReplace(e);
				}
			}
				break;
			case MiniSquirrel: {
				if (((MiniSquirrel) e).getParentID() == masterBot.getId()) {
					masterBot.updateEnergy(((MiniSquirrel) e).getEnergy());
					kill(e);
					
					log.info(masterBot.toString() + " >>> eigenes MiniSquirrel getroffen");
				} else {
					masterBot.updateEnergy(150);
					kill(e);
					
					log.info(masterBot.toString() + " >>> fremdes MiniSquirrel gepluendert");
				}
			}
				break;
			default:
				break;
			}
		} else {
			masterBot.setPosition(moveDirection);
		}
	}

	@Override
	public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
		// TODO Auto-generated method stub

		if (miniSquirrel.getEnergy() <= 0) {
			kill(miniSquirrel);
		}

		// was ist vor uns
		EntityType et = getEntityType(moveDirection);
		Entity e = cells[moveDirection.getX()][moveDirection.getY()];

		if (et != null) {
			switch (et) {
			case Wall: {
				// System.out.println(miniSquirrel.toString()+",
				// WAND!!!...STOP");
				miniSquirrel.updateEnergy(board.getEntityAtPosition(moveDirection).getEnergy());
				// Schritt wird NICHT ausgeführt, Betäubung auf 3 Schritte
				// setzen
				miniSquirrel.setStunned(3);
			}
				break;
			case GoodPlant: {
				// System.out.println(miniSquirrel.toString()+",
				// GoodPlant...fressen");
				miniSquirrel.updateEnergy(e.getEnergy());
				miniSquirrel.setPosition(moveDirection);
				miniSquirrel.updateEnergy(-1);
				killAndReplace(e);
			}
				break;
			case BadPlant: {
				// System.out.println(miniSquirrel.toString()+",
				// BadPlant...kotzen");
				miniSquirrel.updateEnergy(e.getEnergy());
				miniSquirrel.setPosition(moveDirection);
				miniSquirrel.updateEnergy(-1);
				killAndReplace(e);
			}
				break;
			case GoodBeast: {
				// System.out.println(masterBot.toString()+",
				// GoodBeast...fressen");
				miniSquirrel.updateEnergy(e.getEnergy());
				miniSquirrel.setPosition(moveDirection);
				miniSquirrel.updateEnergy(-1);
				killAndReplace(e);
			}
				break;
			case BadBeast: {
				// System.out.println(masterBot.toString()+", BadBeast...wurde
				// gebissen");
				miniSquirrel.updateEnergy(e.getEnergy());
				
				BadBeast temp = (BadBeast) e;
				temp.setBiteCount(temp.getBiteCount() - 1);
				if (temp.getBiteCount() == 0) {
					killAndReplace(e);
				}
			}
				break;
			case MasterSquirrel: {
				if (((MasterSquirrel) e).getId() == miniSquirrel.getParentID()) {
					((MasterSquirrel) e).updateEnergy(miniSquirrel.getEnergy());
					kill(miniSquirrel);
				} else {
					kill(miniSquirrel);
				}
			}
				break;
			default:
				break;
			}
		} else {
			if (miniSquirrel.getStunned() == 0) {
				miniSquirrel.setPosition(moveDirection);
				miniSquirrel.updateEnergy(-1);
			} else {
				// System.out.println(miniSquirrel.toString()+",
				// betaeubt...wait");
				miniSquirrel.setStunned(miniSquirrel.getStunned() - 1);
			}
		}
	}

	@Override
	public PlayerEntity nearestPlayerEntity(XY pos) {
		// TODO Auto-generated method stub
		// im Abstand von N Schritten suchen
		int n = 1;
		while (n < 7) {
			for (int x = -n; x <= n; x++) {
				for (int y = n; y >= -n; y--) {
					// für Feld Größe board.getSize().getX() X
					// board.getSize().getY()
					int xX = x + pos.getX();
					int yY = y + pos.getY();
					// Punkt liegt nicht im Feld
					if (!(((0 < xX) && (xX < board.getSize().getX())) && ((0 < yY) && (yY < board.getSize().getY())))) {
						// Punkte außerhalb
						continue;
					} else if (pos.equals(new XY(xX, yY))) {
						// die pos-Stelle nicht mitberücksichtigen (aktuelle
						// Positionsstelle)
						continue;
					} else {
						// Stelle zur Untersuchung
						// System.out.println("Jäger an Posotion:
						// "+"("+xX+","+yY+")");
						if (getEntityType(xX, yY) == EntityType.MasterSquirrel) {
							return (MasterSquirrel) cells[xX][yY];
						} else if (getEntityType(xX, yY) == EntityType.GuidedMasterSquirrel) {
							return (GuidedMasterSquirrel) cells[xX][yY];
						} else if (getEntityType(xX, yY) == EntityType.MiniSquirrel) {
							return (MiniSquirrel) cells[xX][yY];
						}
					}
				}
			}
			n++;
		}
		return null;
	}

	// @Override
	// public void spawnChildBot(MasterSquirrelBot parent, XY direction, int
	// energy) {
	// // TODO Auto-generated method stub
	//
	// }

	@Override
	public void kill(Entity entity) {
		// TODO Auto-generated method stub
		board.removeEntity(entity);
		// Entity aus 2D-Array rausnehmen
		cells[entity.getPosition().getX()][entity.getPosition().getY()] = null;
	}

	@Override
	public void killAndReplace(Entity entity) {
		// TODO Auto-generated method stub
		board.removeEntity(entity);
		// Entity aus 2D-Array rausnehmen
		cells[entity.getPosition().getX()][entity.getPosition().getY()] = null;
		// neues Entity an zufälligen Stelle erzeugen
		if (entity instanceof GoodPlant) {
			board.addEntity(new GoodPlant(board.getEntityID() + 1, new XY(1, 1).randomSet(getSize())));
		}
		if (entity instanceof BadPlant) {
			board.addEntity(new BadPlant(board.getEntityID() + 1, new XY(1, 1).randomSet(getSize())));
		}
		if (entity instanceof GoodBeast) {
			board.addEntity(new GoodBeast(board.getEntityID() + 1, new XY(1, 1).randomSet(getSize())));
		}
		if (entity instanceof BadBeast) {
			board.addEntity(new BadBeast(board.getEntityID() + 1, new XY(1, 1).randomSet(getSize())));
		}
	}

	@Override
	public EntityType getEntityType(XY xy) {
		// TODO Auto-generated method stub
			EntityType[] et = EntityType.values();
			for (int i = 0; i < et.length; i++) {
				//System.out.println("(" +xy.getX() +"|" +xy.getY() +")\n");
				Entity entity = cells[xy.getX()][xy.getY()];
				String etS = et[i].toString();
				if (( entity!= null) 
						&& (etS
						.compareTo(entity.getClass().getSimpleName().toString()) == 0)) {
					return et[i];
				}
			}
		
		return null;
	}

	@Override
	public EntityType getEntityType(int x, int y) {
		// TODO Auto-generated method stub
		EntityType[] et = EntityType.values();
		for (int i = 0; i < et.length; i++) {
			if ((cells[x][y] != null)
					&& (et[i].toString().compareTo(cells[x][y].getClass().getSimpleName().toString()) == 0)) {
				return et[i];
			}
		}
		return null;
	}

	@Override
	public XY getSize() {
		// TODO Auto-generated method stub
		return board.getSize();
	}

	public String toString() {
		String temp = "Inhalt in 2D\n";
		for (int y = 0; y < getSize().getY(); y++) {
			for (int x = 0; x < getSize().getX(); x++) {
				if (cells[x][y] == null) {
					temp += "..";
				} else {
					temp += getEntityType(x, y).getSymbol();
				}
			}
			temp += "\n";
		}
		if (temp == "Inhalt in 2D\n") {
			return "Keine Elemente enthalten...";
		} else {
			return temp;
		}
	}

	public XY getEscapeVector(XY xy) {
		Random rnd = new Random();

		XY pkt = new XY(0, 0);

		if (xy.getX() > 0 && xy.getY() > 0) {
			int max = 0;
			int min = -1;
			while (pkt.equals(new XY(0, 0))) {
				pkt = new XY(rnd.nextInt(max - (min - 1)) + min, rnd.nextInt(max - (min - 1)) + min);
			}
		} else if (xy.getX() > 0 && xy.getY() < 0) {
			int maxX = 0;
			int minX = -1;
			int maxY = 1;
			int minY = 0;
			while (pkt.equals(new XY(0, 0))) {
				pkt = new XY(rnd.nextInt(maxX - (minX - 1)) + minX, rnd.nextInt(maxY - (minY - 1)) + minY);
			}
		} else if (xy.getX() < 0 && xy.getY() < 0) {
			int max = 1;
			int min = 0;
			while (pkt.equals(new XY(0, 0))) {
				pkt = new XY(rnd.nextInt(max - (min - 1)) + min, rnd.nextInt(max - (min - 1)) + min);
			}
		} else if (xy.getX() < 0 && xy.getY() > 0) {
			int maxX = 1;
			int minX = 0;
			int maxY = 0;
			int minY = -1;
			while (pkt.equals(new XY(0, 0))) {
				pkt = new XY(rnd.nextInt(maxX - (minX - 1)) + minX, rnd.nextInt(maxY - (minY - 1)) + minY);
			}
		} else if (xy.getX() == 0 && xy.getY() > 0) {
			int max = 1;
			int min = -1;
			while (pkt.equals(new XY(0, 0))) {
				pkt = new XY(rnd.nextInt(max - (min - 1)) + min, -1);
			}
		} else if (xy.getX() > 0 && xy.getY() == 0) {
			int max = 1;
			int min = -1;
			while (pkt.equals(new XY(0, 0))) {
				pkt = new XY(-1, rnd.nextInt(max - (min - 1)) + min);
			}
		} else if (xy.getX() < 0 && xy.getY() == 0) {
			int max = 1;
			int min = -1;
			while (pkt.equals(new XY(0, 0))) {
				pkt = new XY(1, rnd.nextInt(max - (min - 1)) + min);
			}
		} else if (xy.getX() == 0 && xy.getY() < 0) {
			int max = 1;
			int min = -1;
			while (pkt.equals(new XY(0, 0))) {
				pkt = new XY(rnd.nextInt(max - (min - 1)) + min, 1);
			}
		}
		return pkt;
	}

}
