package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandInvalidPositionException;
import tp1.p2.control.exceptions.CommandNotEnoughCoinsException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class AddPlantCheatCommand extends Command {
	private int col;

	private int row;

	private String plantName;

	private boolean consumeCoins;

	public AddPlantCheatCommand() {
		this(false);
	}

	public AddPlantCheatCommand(boolean consumeCoins) {
		this.consumeCoins = consumeCoins;
	}

	protected String getName() {
		return "";
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CHEATPLANT_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CHETPLANT_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CHEATPLANT_HELP;
	}

	@Override
	protected void newCycleStarted() {
		// TODO Auto-generated method stub

	}

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

		if (parameters.length == 4) {
			try {
				this.plantName = parameters[1];
				this.col = Integer.parseInt(parameters[2]);
				this.row = Integer.parseInt(parameters[3]);
				return this;
			} catch (NumberFormatException e) {
				throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[2], parameters[3]));
			}
		} else {

			throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
		}

	}

}
