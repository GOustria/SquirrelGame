package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;

public class MiniSquirrelBot extends MiniSquirrel {

	BotControllerFactory botControllerFactory;
	BotController miniBotController;

	public MiniSquirrelBot(int parentID, int id, int energy, XY position) {
		super(parentID, id, energy, position);

		this.botControllerFactory = new BotControllerFactory() {

			@Override
			public BotController createMasterBotController() {
				return null;
			}

			@Override
			public BotController createMiniBotController() {
				return new BotController() {

					@Override
					public void nextStep(ControllerContext view) {
						if (getStunned() == 0) {
							XY xy = XY.getRandomDirection().add(getPosition());
							view.move(xy);
						} else {
							setStunned(getStunned() - 1);
						}
					}
				};
			}
		};
		this.miniBotController = botControllerFactory.createMiniBotController();
	}

	class ControllerContextImpl implements ControllerContext {

		private EntityContext context;
		private MiniSquirrel miniSquirrel;

		public ControllerContextImpl(EntityContext context,
				MiniSquirrel miniSquirrel) {
			this.context = context;
			this.miniSquirrel = miniSquirrel;
		}

		@Override
		public XY getViewLowerLeft() {
			return miniSquirrel.getPosition().add(-10, -10);
		}

		@Override
		public XY getViewUpperRight() {
			return miniSquirrel.getPosition().add(10, 10);
		}

		@Override
		public EntityType getEntityAt(XY xy) {
			return context.getEntityType(xy);
		}

		@Override
		public void move(XY direction) {
			context.tryMove(miniSquirrel, direction);
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) {
		}

		@Override
		public int getEnergy() {
			return miniSquirrel.getEnergy();
		}

	}
}
