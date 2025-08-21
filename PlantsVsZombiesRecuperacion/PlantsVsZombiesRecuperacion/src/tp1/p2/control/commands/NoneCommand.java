package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class NoneCommand extends Command {

	public NoneCommand() {
		// default command
		//super(true);
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_NONE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_NONE_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_NONE_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_NONE_HELP;
	}

	@Override
	protected void newCycleStarted() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean execute(GameWorld game) throws CommandExecuteException {
		try {
			game.update();
		}catch(GameException e) {
			throw new CommandExecuteException (e.getMessage());
		}
		return true;
	}

}
