package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandInvalidPositionException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

public class AddZombieCommand extends Command {
	private int col;

	private int row;

	private int ZombieInd;

	@Override
	protected String getName() {
		return Messages.COMMAND_ADDZOMBIE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADDZOMBIE_SHORTCUT;
	}

	@Override
	public String getDetails() {

		return Messages.COMMAND_ADDZOMBIE_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADDZOMBIE_HELP;
	}

	@Override
	protected void newCycleStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean execute(GameWorld game) throws GameException {

		Zombie zombie;
		try {
			game.checkValidZombiePosition(col, row);
			zombie = ZombieFactory.spawnZombie(this.ZombieInd, game, this.col, this.row);

			game.addZombie(zombie);
		} catch (GameException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	public Command create(String[] parameters) throws GameException {
		Command command = null;

		if (parameters.length == 4) {

			this.ZombieInd = Integer.parseInt(parameters[1]);

			this.col = Integer.parseInt(parameters[2]);

			this.row = Integer.parseInt(parameters[3]);

			return this;
		} else {
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
	}

}
