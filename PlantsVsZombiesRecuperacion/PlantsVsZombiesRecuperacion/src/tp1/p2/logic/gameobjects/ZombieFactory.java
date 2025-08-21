package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ZombieFactory {

	private static final List<Zombie> AVAILABLE_ZOMBIES = Arrays.asList(new Basic_Zombie(), new BucketHead_Zombie(),
			new Sporty_Zombie(), new ExplosiveZombie());

	public static List<Zombie> getAvailableZombies() {
		return Collections.unmodifiableList(AVAILABLE_ZOMBIES);
	}

	public static boolean isValidZombie(int indice) {
		return indice >= 0 && indice < AVAILABLE_ZOMBIES.size();
	}

	public static Zombie spawnZombie(int indice, GameWorld game, int col, int row) throws GameException {
		if (!isValidZombie(indice))
			throw new GameException(Messages.INVALID_GAME_OBJECT);

		Zombie zombie;
		zombie = Zombie.create(indice, col, row, game);
		return zombie;
	}

}
