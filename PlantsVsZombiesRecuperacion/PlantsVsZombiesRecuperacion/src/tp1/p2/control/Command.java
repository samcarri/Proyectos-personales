package tp1.p2.control;

import static tp1.p2.view.Messages.error;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import tp1.p2.control.commands.AddPlantCheatCommand;
import tp1.p2.control.commands.AddPlantCommand;
import tp1.p2.control.commands.AddZombieCommand;
import tp1.p2.control.commands.CatchCommand;
import tp1.p2.control.commands.ExitCommand;
import tp1.p2.control.commands.HelpCommand;
import tp1.p2.control.commands.ListPlantsCommand;
import tp1.p2.control.commands.NoneCommand;
import tp1.p2.control.commands.RecordCommand;
import tp1.p2.control.commands.ResetCommand;
import tp1.p2.control.commands.ZombieListCommand;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

/**
 * Represents a user action in the game.
 *
 */
public abstract class Command {

	/* @formatter:off */
	private static final List<Command> AVAILABLE_COMMANDS = Arrays.asList(
		
		new AddPlantCommand(),
		new ListPlantsCommand(),
		new ResetCommand(),
		new HelpCommand(),
		new ExitCommand(),
		new NoneCommand(),
		new ZombieListCommand(),
		new AddZombieCommand(),
		new AddPlantCheatCommand(),
		new CatchCommand(),
		new RecordCommand()
	);
	

	private static Command defaultCommand;
Command command=null;

public static List<Command> getCommands() {
	return AVAILABLE_COMMANDS;
}
public static Command parse(String[] commandWords) throws GameException {
Command aux=null;
	if (commandWords.length == 1 && commandWords[0].isEmpty()) {
	return new NoneCommand(); //REPRESENTA EL PULSAR ENTER
	}

	for (Command command : AVAILABLE_COMMANDS) {
		if (command.matchCommand(commandWords[0])) {
			aux=command.create(commandWords);
			return aux;
		}
	}
	throw new CommandParseException(Messages.UNKNOWN_COMMAND);
}

	public static Iterable<Command> getAvailableCommands() {
		return Collections.unmodifiableList(AVAILABLE_COMMANDS);
	}
	
	public Command create(String[] parameters) throws GameException  {
		  return this;
	}
	
	public boolean matchCommand(String token) {
		String shortcut = getShortcut();
		String name = getName();
		return shortcut.equalsIgnoreCase(token) || name.equalsIgnoreCase(token)
				|| (isDefaultAction() && "".equals(token));
	}

	public boolean isDefaultAction() {
		return Command.defaultCommand == this;
	}
	
	public static void restartCycle() {
	    for(Command c : AVAILABLE_COMMANDS) {
	        c.newCycleStarted();
	    }
	}
	
	abstract protected String getName();

	abstract protected String getShortcut();

	abstract public String getDetails();

	abstract public String getHelp();

	public abstract boolean execute(GameWorld game) throws GameException;

	abstract protected void newCycleStarted();

}