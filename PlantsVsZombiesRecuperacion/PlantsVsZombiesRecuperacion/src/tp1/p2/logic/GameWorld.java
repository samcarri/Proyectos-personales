package tp1.p2.logic;

import java.io.FileNotFoundException;
import java.io.IOException;

import tp1.p2.control.ExecutionResult;
import tp1.p2.control.Level;
import tp1.p2.control.exceptions.CommandInvalidPositionException;
import tp1.p2.control.exceptions.CommandNotEnoughCoinsException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.Zombie;

public interface GameWorld {

	public static final int NUM_ROWS = 4;

	public static final int NUM_COLS = 8;

	void update() throws GameException;

	boolean playerQuits(boolean acabar);

	void reset() throws GameException;

	void reset(Level level, long seed) throws GameException;

	public void checkValidPlantPosition(int col, int row) throws GameException;

	public void checkValidZombiePosition(int col, int row) throws GameException;

	public void readRecord() throws GameException;

	public void writeRecord() throws GameException;

	public void initializeRecord() throws GameException, FileNotFoundException, IOException;

	boolean tryToBuy(int coins);

	boolean zombieFinalPos();

	void zombieDied();

	void addSuncoins();

	boolean addPlant(Plant planta, boolean suncoins) throws  GameException;

	boolean addZombie(Zombie zombie) throws GameException;

	boolean addRandomZombie();

	boolean checkPos(int col, int row);

	boolean Catch(int col, int row);

	void pushAction(GameAction gameAction);

	GameItem getGameItemInPos(int col, int row);

	boolean positionFilled(int col, int row);

	public void addPoints(int points);

	public Level getLevel();

	public long getSeed();
}
