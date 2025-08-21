package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class RecordCommand extends Command{
public RecordCommand() {
	
}

@Override
protected String getName() {
return Messages.COMMAND_RECORD_NAME;
}

@Override
protected String getShortcut() {
return Messages.COMMAND_RECORD_SHORTCUT;
}

@Override
public String getDetails() {
	return Messages.COMMAND_RECORD_DETAILS;
}

@Override
public String getHelp() {
return Messages.COMMAND_RECORD_HELP;
}

@Override
protected void newCycleStarted() {
	// TODO Auto-generated method stub
	
}

@Override
public boolean execute(GameWorld game) throws GameException {
game.readRecord();
return false;
}

}
