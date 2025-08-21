package tp1.p2.control;

import static tp1.p2.view.Messages.debug;
import static tp1.p2.view.Messages.error;

import java.io.IOException;
import java.util.Scanner;

import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameObjectContainer;
import tp1.p2.logic.GameStatus;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.SunManager;
import tp1.p2.logic.gameobjects.Basic_Zombie;
import tp1.p2.logic.gameobjects.Peashooter;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.Sunflower;//PARA TESTEAR EL ARRAY LIST
import tp1.p2.view.GamePrinter;
import tp1.p2.view.Messages;

/**
 * Accepts user input and coordinates the game execution logic.
 */
public class Controller {

	private Game game;

	private GameWorld gameworld;// QUITAR, SOLO PARA PRUEBAS

	private GameStatus gamestatus;// A�ADIMOS PARA QUE DEJE DE DAR ERROR

	private Scanner scanner;

	private GamePrinter gamePrinter;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		//
		this.scanner = scanner;
		//
		this.gamePrinter = new GamePrinter(game);
		//
	}

	/**
	 * Draw / Paint the game.
	 */
	private void printGame() {
		System.out.println(gamePrinter);
	}

	/**
	 * Prints the final message once the match is finished.
	 */
	public void printEndMessage() {
		System.out.println(gamePrinter.endMessage());
	}

	/**
	 * Show prompt and request command.
	 *
	 * @return the player command as words
	 */
	private String[] prompt() {
		System.out.print(Messages.PROMPT);
		String line = scanner.nextLine();
		String[] words = line.toLowerCase().trim().split("\\s+");

		System.out.println(debug(line));

		return words;
	}

	/**
	 * Runs the game logic.
	 * 
	 * @throws GameException
	 * @throws IOException
	 */
	public void run() throws GameException, IOException {
		boolean refreshDisplay = true;
		this.gameworld = game.getGameWorld();
		game.initializeRecord();
		while (!game.isFinished() && !game.isPlayerQuits()) {

			// 1. Draw
			if (refreshDisplay == true) {
				System.out.println(Messages.NUMBER_OF_CYCLES + game.getCycle());
				System.out.println(Messages.NUMBER_OF_COINS + game.getSuncoins());
				System.out.println(Messages.REMAINING_ZOMBIES + game.getRemainingZombies());
				System.out.println("Suns generated : " + SunManager.generated);
				System.out.println("Suns catched: " + SunManager.catched);
				System.out.println("Points: " + game.points);
				System.out.println("");
				printGame();
			}

			// 2. User action
			String[] words = prompt();

			try {
				refreshDisplay = false;
				Command command;
				command = Command.parse(words);
				refreshDisplay = game.execute(command);
			} catch (GameException e) {
				System.out.println(error(e.getMessage()));
			}

		}

		game.writeRecord();
		printEndMessage();
	}

}
