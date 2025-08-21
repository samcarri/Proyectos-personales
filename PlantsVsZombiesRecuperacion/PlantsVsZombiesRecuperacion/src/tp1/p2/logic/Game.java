package tp1.p2.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.Level;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandInvalidPositionException;
import tp1.p2.control.exceptions.CommandNotEnoughCoinsException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.RecordException;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.view.Messages;
import tp1.p2.logic.actions.GameAction;

public class Game implements GameWorld, GameStatus {
	private long Gseed;
	private Level Glevel;
	private GameWorld gameWorld;

	private GameObjectContainer lista;
	private ZombiesManager zombieManager;
	private SunManager suns;
	private Random random;
	private Record record;

	private int remainingZombies;
	public static int NUM_ROWS = 4;
	public static int NUM_COLS = 8;
	public int suncoins = GameStatus.suncoins;
	public int points = GameStatus.points;
	public int contador_ciclos = 0;
	public static boolean ENDGAME = false;
	private Deque<GameAction> actions;

	public Game(long seed, Level Glevel) throws GameException {
		this.Gseed = seed;
		this.Glevel = Glevel;
		this.lista = new GameObjectContainer();
		this.random = new Random(seed);
		this.zombieManager = new ZombiesManager(this, Glevel, random, this.lista);
		this.suns = new SunManager(this, Glevel, random, this.lista);
		this.remainingZombies = zombieManager.getRemainingZombies();
		this.actions = new ArrayDeque<>();
		this.record = new Record();

	}

	public GameWorld getGameWorld() {
		return this.gameWorld;
	}

	public Level getLevel() {
		return this.Glevel;
	}

	public long getSeed() {
		return this.Gseed;
	}

	public boolean zombieWins() {
		if (zombieFinalPos() == true) {
			return true;
		}
		return false;
	}

	public boolean playerWins() {
		boolean gana = false;
		if (zombieManager.allZombiesDead() == true) {
			gana = true;
			System.out.println(Messages.PLAYER_WINS);
		}
		return gana;
	}

	public boolean isFinished() {// NUEVO
		if (playerWins() == true || zombieWins() == true) {
			return true;
		}
		return false;
	}

//EL JUGADOR SE "RINDE"/////////////////////////////
	public boolean isPlayerQuits() {// NUEVO
		if (ENDGAME == true) {
			System.out.println(Messages.PLAYER_QUITS);
			return true;
		}
		return false;
	}
///////////////////////////////////////////////////

//PASA EL STRING DEL ELEMENTO EN LA CASILLA SELECCIONADA///
	public String positionToString(int col, int row) {
		String cosas = "";
		cosas = lista.getSymbol(col, row);
		return cosas;
	}

	public boolean execute(Command command) throws GameException {
		return command.execute(this);
	}

	public void reset(Level level, long seed) {
		this.contador_ciclos = 0;
		this.suncoins = 50;
		this.lista = new GameObjectContainer();
		this.random = new Random(seed);
		this.zombieManager = new ZombiesManager(this, level, random, this.lista);
		this.suns = new SunManager(this, level, random, this.lista);
		this.remainingZombies = zombieManager.getRemainingZombies();
		this.actions = new ArrayDeque<>();
		this.Glevel = level;
		this.Gseed = seed;

		try {
			writeRecord();
		} catch (GameException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void reset() throws GameException {
		reset(this.Glevel, this.Gseed);
	}

	public int getSuncoins() {
		return this.suncoins;
	}

	public int getCycle() {
		return this.contador_ciclos;
	}

	public boolean playerQuits(boolean acabar) {
		if (acabar == true) {
			return true;
		}
		return false;
	}

	@Override
	public void update() throws GameException {

		int contador_generate = 0; // veces que un sunflower genera plantas
		boolean deadRemoved = true;

		executePendingActions();
		// EXECUTE GAME ACTIONS
		suns.addRandomSun();
		addRandomZombie();
		// UPDATE GAMEOBJECTS
		contador_generate = lista.update();
		for (int i = 0; i < contador_generate; i++) {
			suns.addSuns(true, false);
		}

		// 4. Remove dead
		while (deadRemoved || areTherePendingActions()) {
			// 4. Remove dead
			deadRemoved = this.lista.deleteDead();
			// 5. execute pending actions
			executePendingActions();
		}

		// 5. execute pending actions
		this.contador_ciclos++;
		SunManager.cogido = false;
		Command.restartCycle();

	}

	public boolean addPlant(Plant planta, boolean suncoins) throws GameException {
		if (planta == null)
			throw new GameException(Messages.INVALID_GAME_OBJECT);
		if (this.positionFilled(planta.getCol(), planta.getRow()))
			throw new CommandInvalidPositionException(Messages.INVALID_POSITION);

		if (planta != null && lista.isEmpty(planta.getCol(), planta.getRow()) == true) {
			if (suncoins == false) {
				this.lista.addToList(planta);
				return true;
			} else {
				if (tryToBuy(planta.getCost()) == true) {
					this.lista.addToList(planta);
					return true;
				} else {
					throw new CommandNotEnoughCoinsException(Messages.NOT_ENOUGH_COINS);
				}
			}
		}
		return false;
	}

	@Override

	public boolean addZombie(Zombie zombie)throws GameException {
		if(zombie==null) throw new CommandExecuteException(Messages.INVALID_GAME_OBJECT);
		if(this.positionFilled(zombie.getCol(), zombie.getRow()))throw new CommandInvalidPositionException(Messages.INVALID_POSITION);
		boolean added = false;
		try {
			added = this.zombieManager.addZombie();
		} catch (GameException e) {
			System.out.println(e.getMessage());
		}
		this.lista.addToList(zombie);
		return added;
	}

	@Override
	public void addSuncoins() {
		this.suncoins = this.suncoins + 10;

	}

	@Override
	public boolean playerLoose() {
		boolean end = false;
		if (zombieFinalPos() == true) {
			end = true;
		}

		return end;
	}

	@Override
	public boolean zombieFinalPos() {
		for (int i = 0; i < Game.NUM_COLS; i++) {
			if (lista.isEmpty(-1, i) == false) {
				System.out.println(Messages.ZOMBIES_WIN);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkPos(int col, int row) {
		boolean checked = false;
		checked = lista.isEmpty(col, row);// FALSE SI ESTA VACIO
		return checked;
	}

	@Override
	public boolean addRandomZombie() {
		boolean added = false;
		try {
			added = this.zombieManager.addZombie();
		} catch (GameException e) {
			System.out.println(e.getMessage());
		}
		return added;
	}

	@Override
	public int getRemainingZombies() {
		return this.zombieManager.getRemainingZombies();
	}

	@Override
	public boolean Catch(int col, int row) {
		GameItem objeto = null;
		if (SunManager.cogido == false) {
			objeto = lista.getCatchableObjectInPos(col, row);
			if (objeto != null) {
				if (objeto.Catch(col, row) == true) {
					lista.deleteDead();
					SunManager.cogido = true;
					SunManager.catched++;
					addSuncoins();
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public void zombieDied() {
		ZombiesManager.zombieDied();
	}

	public void pushAction(GameAction gameAction) {
		this.actions.addLast(gameAction);
	}

	private void executePendingActions() {
		while (!this.actions.isEmpty()) {
			GameAction action = this.actions.removeLast();
			action.execute(this);
		}
	}

	private boolean areTherePendingActions() {
		return this.actions.size() > 0;
	}

	public GameItem getGameItemInPos(int col, int row) {
		GameItem item = null;
		item = lista.getObjectInPos(col, row);
		return item;
	}

	@Override
	public boolean positionFilled(int col, int row) {
		return lista.checkPos(col, row);
	}

	public void writeRecord() throws GameException {// practica 3
		record.writeRecord(this.Glevel, points);
	}

	public void readRecord() throws RecordException {
		record.showRecord(Glevel);
	}

	public void initializeRecord() throws GameException, IOException {
		record.initializeRecord();
	}

	@Override
	public void addPoints(int points) {
		this.points += points;

	}

	@Override
	public void checkValidPlantPosition(int col, int row) throws GameException {
		if (!(col >= 0 && col < Game.NUM_COLS && row >= 0 && row < NUM_ROWS))
			throw new CommandInvalidPositionException(Messages.INVALID_POSITION.formatted(col, row));

	}

	@Override
	public void checkValidZombiePosition(int col, int row) throws GameException {
		if (!(col >= 0 && col < Game.NUM_COLS && row >= 0 && row < NUM_ROWS))
			throw new CommandInvalidPositionException(Messages.INVALID_POSITION);

	}

	@Override
	public boolean tryToBuy(int coins) {
		boolean can = false;
		if (coins <= this.suncoins) {
			this.suncoins = this.suncoins - coins;
			can = true;
		}
		return can;
	}
}
