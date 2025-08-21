package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;

/**
 * Base class for game non playable character in the game.
 *
 */
public abstract class GameObject implements GameItem {

	protected GameWorld game;

	protected int col;

	protected int row;

	GameObject() {
	}

	GameObject(GameWorld game, int col, int row) {
		this.game = game;
		this.col = col;
		this.row = row;

	}

	public abstract String getSymbol();

	abstract public String getDescription();

	abstract public boolean isEmpty(int col, int row);

	abstract public double getCycles();

	abstract public boolean update();

	abstract public void onEnter();

	abstract public void onExit();

	abstract public int getDamge();

	abstract public int getVida();

	public boolean isInPosition(int col, int row) {
		return this.col == col && this.row == row;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	abstract public boolean isAlive();

	public String toString() {
		if (isAlive() == true) {
			return "vivo";
		} else {
			return "";
		}

	}

}
