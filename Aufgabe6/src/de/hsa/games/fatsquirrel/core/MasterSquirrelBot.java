package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;

public class MasterSquirrelBot extends MasterSquirrel {

	private BotControllerFactory botControllerFactory;
	private BotController masterBotController;

	public MasterSquirrelBot(int id, XY position, String typ) {
		super(id, position);
		
		switch (typ) {
		case "aggressive":
			this.botControllerFactory = new BotControllerFactory() {
				
				@Override
				public BotController createMiniBotController() {
					return null;
				}
				
				@Override
				public BotController createMasterBotController() {
					
					return null;
				}
			};
			break;
			
		case "calm":
			this.botControllerFactory = new BotControllerFactory() {
				
				@Override
				public BotController createMiniBotController() {
					return null;
				}
				
				@Override
				public BotController createMasterBotController() {
					return null;
				}
			};
			break;
			
		case "crazy":
			this.botControllerFactory = new BotControllerFactory() {

				@Override
				public BotController createMiniBotController() {
					return null;
				}

				@Override
				public BotController createMasterBotController() {
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
			break;
		}
		this.masterBotController = botControllerFactory
				.createMasterBotController();
	}

	@Override
	public void nextStep(EntityContext context) {
		masterBotController.nextStep(new ControllerContextImpl(context, this));
	}

	class ControllerContextImpl implements ControllerContext {

		private EntityContext context;
		private MasterSquirrel masterSquirrel;

		public ControllerContextImpl(EntityContext context,
				MasterSquirrel masterSquirrel) {
			this.context = context;
			this.masterSquirrel = masterSquirrel;
		}

		@Override
		public XY getViewLowerLeft() {
			return masterSquirrel.getPosition().add(-15, -15);
		}

		@Override
		public XY getViewUpperRight() {
			return masterSquirrel.getPosition().add(15, 15);
		}

		@Override
		public EntityType getEntityAt(XY xy) {
			return context.getEntityType(xy);
		}

		@Override
		public void move(XY direction) {
			context.tryMove(masterSquirrel, direction);
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) {
			// TODO: ?? Spawnmini ist in GameImpl
		}

		@Override
		public int getEnergy() {
			return masterSquirrel.getEnergy();
		}

	}
}
