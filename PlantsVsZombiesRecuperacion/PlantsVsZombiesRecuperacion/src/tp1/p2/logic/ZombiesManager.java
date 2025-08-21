package tp1.p2.logic;

import java.util.Random;

import tp1.p2.control.Level;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameObjectContainer;
import tp1.p2.logic.gameobjects.Basic_Zombie;
import tp1.p2.logic.gameobjects.BucketHead_Zombie;
import tp1.p2.logic.gameobjects.Sporty_Zombie;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;


public class ZombiesManager {
	private Game game;
	private Level level;
	private Random rand;
	private static int remainingZombies;
	private static int zombiesAlive;
	GameObjectContainer lista;

	public ZombiesManager(Game game, Level level, Random rand, GameObjectContainer Lista) {
		this.game = game;
		this.level = level;
		this.rand = rand;
		remainingZombies = level.getNumberOfZombies();
		zombiesAlive = 0;
		this.lista = Lista;

	}

	private int randomZombieType() {
		return rand.nextInt(ZombieFactory.getAvailableZombies().size());
	}

	private int randomZombieRow() {
		return rand.nextInt(ZombieFactory.getAvailableZombies().size());
	}

	public boolean addZombie() throws GameException {
		int row = randomZombieRow();
		return addZombie(row);

	}

	public boolean addZombie(int row) throws GameException {
		boolean canAdd = getRemainingZombies() > 0 && shouldAddZombie() && isPositionEmpty(GameWorld.NUM_COLS, row);
		int zombieType = randomZombieType(); //Cambiar spawnZombie cuando haya más tipos jeje
		if (canAdd) {
			
			Zombie zombie = ZombieFactory.spawnZombie(zombieType, game, GameWorld.NUM_COLS, row);
			zombiesAlive++;
			remainingZombies--;
			lista.addToList(zombie);

		}
		return canAdd;
		
	}

	private boolean shouldAddZombie() {
		return rand.nextDouble() < level.getZombieFrequency();

	}

	boolean isPositionEmpty(int cols, int fila) {
		boolean comprobar = true;
		comprobar = lista.isEmpty(cols, fila);

		return comprobar;
	}

	public int getRemainingZombies() {
		return remainingZombies;
	}

	public boolean allZombiesDead() {
		if (remainingZombies == 0 && zombiesAlive == 0) {
			return true;
		}
		return false;
	}

	public static void zombieDied() {
	zombiesAlive--;
		
	}


}

//int zombieType = randomZombieType(); //Cambiar spawnZombie cuando haya más tipos jeje
//if (canAdd) {
//	
//	// TODO add your code here
//	Zombie zombie = ZombieFactory.spawnZombie(zombieType, game, GameWorld.NUM_COLS, row);
//	zombieOnEnter();
//	if(zombie != null){
//		//System.out.println("Añado zombi");
//		remainingZombies -= 1;
//	    lista.addToList(zombie);
//	   
//	}
//}
//return canAdd;