package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandInvalidPositionException;
import tp1.p2.control.exceptions.CommandNotEnoughCoinsException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameObjectContainer;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class AddPlantCommand extends Command implements Cloneable {

	private int col;

	private int row;

	private String plantName;

	private boolean consumeCoins;

	public AddPlantCommand() {
		this(true);
	}

	public AddPlantCommand(boolean consumeCoins) {
		this.consumeCoins = consumeCoins;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}

	@Override
	protected void newCycleStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean execute(GameWorld gameworld) throws GameException {
		Plant plant;

		try {
			gameworld.checkValidPlantPosition(col, row);
			plant = PlantFactory.spawnPlant(this.plantName, gameworld, this.col, this.row);
			gameworld.addPlant(plant, this.consumeCoins);

		} catch (GameException e) {
			throw new CommandExecuteException(e.getMessage());
		}
		return true;
	}

	@Override
	public Command create(String[] parameters) throws GameException {

		Command command = null;
		if (parameters.length == 4) {

			this.plantName = parameters[1];

			this.col = Integer.parseInt(parameters[2]);

			this.row = Integer.parseInt(parameters[3]);

			return this;
		} else {
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
	}

}
