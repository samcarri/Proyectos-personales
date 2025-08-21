package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.p2.control.Command;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public abstract class Zombie extends GameObject {
	private int vida;
	private int damage;

	private static final List<Zombie> AVAILABLE_ZOMBIES = Arrays.asList(new Basic_Zombie(), new Sporty_Zombie(),
			new BucketHead_Zombie(), new ExplosiveZombie());

	public Zombie(GameWorld game, int col, int row, int vida, int damage) {
		super(game, col, row);
		this.vida = vida;
		this.damage = damage;
	}

	public Zombie() {

	}

	@Override
	public abstract String getDescription();

	abstract public boolean checkZombieNum(int ZombieIndice);

	public abstract boolean matchNum(int num);

	public static List<Zombie> getList() {
		return AVAILABLE_ZOMBIES;
	}

	public boolean checkSuns() {
		return false;
	}

	@Override
	public int getVida() {
		return this.vida;
	}

	@Override
	public boolean isEmpty(int row, int col) {
		boolean ocupado = false;
		if (row == this.row && col == this.col) {
			ocupado = true;
		}
		return ocupado;
	}

	public boolean receiveZombieAttack(int damage) {
		return false;
	}

	@Override
	public boolean receivePlantAttack(int damage) {
		this.vida = this.vida - damage;
		if (this.vida <= 0) {
			if (damage == 10) {
				game.addPoints(20);
			}
			else {
				game.addPoints(10);
			}
		game.zombieDied();
	}
		return true;
	}

	public boolean Catch() {
		return false;
	}

	public abstract Zombie createZombie(int col, int row, GameWorld game);

	public static Zombie create(int num, int col, int row, GameWorld game) {
		for (Zombie zombie : AVAILABLE_ZOMBIES) {
			if (zombie.matchNum(num) == true) {
				return zombie.createZombie(col, row, game);
			}

		}
		return null;
	}

	public boolean isAlive() {
		if (this.vida == 0) {
			return true;
		}
		return true;
	}

	public boolean fillPosition() {// EN UN PRINCIPIO TODOS LOS ZOMBIES VAN A SER TANGIBLES
		return true;
	}

}
