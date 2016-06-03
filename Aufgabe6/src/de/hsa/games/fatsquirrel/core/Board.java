package de.hsa.games.fatsquirrel.core;

import java.util.logging.Logger;

public class Board {

	private BoardConfig boardConfig;

	private Entity[] entities;

	private int entityID;

	Logger log;

	public Board(BoardConfig boardConfig) {
		this.boardConfig = boardConfig;
		entities = new Entity[boardConfig.getSize().getX()
				* boardConfig.getSize().getY()];
		initWallsAround();
		initEntities();

		log = Logger.getLogger(Board.class.getName());
	}

	public Entity[] getEntities() {
		return entities;
	}

	public XY getSize() {
		return boardConfig.getSize();
	}

	public int getEntityID() {
		return entityID;
	}

	public void setEntityID(int entityID) {
		this.entityID = entityID;
	}

	// sucht eine freie Stelle im Array
	public int getFreeIndex() {
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] == null) {
				return i;
			}
		}
		return 99;
	}

	// prüft ob ID vorhanden
	public boolean containID(Entity e) {
		for (int i = 0; i < entities.length; i++) {
			if ((entities[i] != null) && (entities[i].getId() == e.getId())) {
				return true;
			}
		}
		return false;
	}

	// prüft ob Position XY nicht schon besetzt ist
	public boolean containPosition(Entity e) {
		for (int i = 0; i < entities.length; i++) {
			if ((entities[i] != null)
					&& (entities[i].getPosition().equals(e.getPosition()))) {
				return true;
			}
		}
		return false;
	}

	public void addEntity(Entity e) {
		if (getFreeIndex() == -1) {
			System.out.println("Speicher voll...");
		} else {
			if (containID(e)) {
				System.out.println("ID bereits enthalten...");
			} else if (containPosition(e)) {
				// System.out.println("Position bereits besetzt...");
				// System.out.println("suche neue Stelle...");

				// log.info(e.toString() +
				// " >>> Position bereits besetzt, suche neue Stelle");
				// TODO: FEHLER

				e.setPosition(e.getPosition().randomSet(getSize()));
				this.addEntity(e);
			} else {
				entities[getFreeIndex()] = e;
				entityID++;
			}
		}
	}

	// liefert Index von Entity im Array
	public int getEntityIndexn(Entity e) {
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null && entities[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}

	// liefert Entity nach Position
	public Entity getEntityAtPosition(XY position) {
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null
					&& entities[i].getPosition().equals(position)) {
				return entities[i];
			}
		}
		return null;
	}

	public void removeEntity(Entity e) {
		if (getEntityIndexn(e) == -1) {
			System.out.println("Entity nicht enthalten...");
		} else {
			entities[getEntityIndexn(e)] = null;
		}
	}

	public void initWallsAround() {
		int x = boardConfig.getSize().getX();
		int y = boardConfig.getSize().getY();
		for (int i = 0; i < x; i++) {
			this.addEntity(new Wall(entityID, new XY(i, 0)));
		}
		for (int i = 0; i < x; i++) {
			this.addEntity(new Wall(entityID, new XY(i, y - 1)));
		}
		for (int i = 1; i < y - 1; i++) {
			this.addEntity(new Wall(entityID, new XY(0, i)));
		}
		for (int i = 1; i < y - 1; i++) {
			this.addEntity(new Wall(entityID, new XY(x - 1, i)));
		}
	}

	public void initEntities() {
		XY temp = new XY(0, 0);
		for (int i = 0; i < boardConfig.getInitBB(); i++) {
			this.addEntity(new BadBeast(entityID, temp.randomSet(getSize())));
		}
		for (int i = 0; i < boardConfig.getInitBP(); i++) {
			this.addEntity(new BadPlant(entityID, temp.randomSet(getSize())));
		}
		for (int i = 0; i < boardConfig.getInitGB(); i++) {
			this.addEntity(new GoodBeast(entityID, temp.randomSet(getSize())));
		}
		for (int i = 0; i < boardConfig.getInitGP(); i++) {
			this.addEntity(new GoodPlant(entityID, temp.randomSet(getSize())));
		}
		for (int i = 0; i < boardConfig.getInitW(); i++) {
			this.addEntity(new Wall(entityID, temp.randomSet(getSize())));
		}
		// for(int i=0;i<boardConfig.getInitMS();i++) {
		// this.addEntity(new
		// MasterSquirrel(entityID,temp.randomSet(getSize())));
		// }
		for (int i = 0; i < boardConfig.getInitMB(); i++) {
			this.addEntity(new MasterSquirrelBot(entityID, temp.randomSet(getSize()), "crazy"));
		}
	}

	public String toString() {
		String temp = "Inhalt\n";
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null) {
				temp += entities[i].toString();
				temp += "\n";
			}
		}
		if (temp == "Inhalt\n") {
			return "Keine Elemente enthalten...";
		} else {
			return temp;
		}
	}

}
