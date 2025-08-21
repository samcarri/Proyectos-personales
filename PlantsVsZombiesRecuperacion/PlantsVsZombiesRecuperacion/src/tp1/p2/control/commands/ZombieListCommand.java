package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.view.Messages;

public class ZombieListCommand extends Command {

	@Override
	protected String getName() {
		return Messages.COMMAND_LISTZOMBIE_NAME;
	}

	@Override
	protected String getShortcut() {
	return Messages.COMMAND_lISTZOMBIE_SHORTCUT;

	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_LISTZOMBIE_DETAILS;

	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_LISTZOMBIR_HELP;
	}

	@Override
	protected void newCycleStarted() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public boolean execute(GameWorld game) {
		for(Zombie zombie: Zombie.getList()) {
			System.out.println(zombie.getDescription());
	
		}
		System.out.println("");
		return false;
	}

}
