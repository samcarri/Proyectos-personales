package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.exceptions.CommandNotCachableException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class CatchCommand extends Command {
	private int col;
	private static boolean caughtSunThisCycle = false;
	private int row;

	@Override
	protected String getName() {
		return Messages.COMMAND_CATCH_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CATCH_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CATCH_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CATCH_HELP;
	}

	@Override
	protected void newCycleStarted() {
		caughtSunThisCycle = false;

	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		if (caughtSunThisCycle == true) {
			throw new CommandNotCachableException(Messages.SUN_ALREADY_CAUGHT);
		} else {
			boolean catched = false;
			catched = game.Catch(this.col, this.row);

			if (catched == false) {
				throw new CommandNotCachableException(Messages.NO_CATCHABLE_IN_POSITION.formatted(col, row));
			} else {
				caughtSunThisCycle = true;
				return true;
			}

		}
	}

	public Command create(String[] parameters) throws GameException {
		if (parameters.length == 3) {

			this.col = Integer.parseInt(parameters[1]);

			this.row = Integer.parseInt(parameters[2]);

			return this;
		} else {
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
	}
}
