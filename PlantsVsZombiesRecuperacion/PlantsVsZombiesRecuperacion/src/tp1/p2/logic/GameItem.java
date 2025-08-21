package tp1.p2.logic;

import tp1.p2.logic.gameobjects.Plant;

/**
 * Represents a game item and its allowed game actions.
 *
 */
public interface GameItem {

	boolean receiveZombieAttack(int damage);

	boolean receivePlantAttack(int damage);
	
	boolean fillPosition();

	boolean Catch(int col, int row);
	
	
}