package tp1.p2.logic;

import java.util.Random;

import tp1.p2.control.Level;
import tp1.p2.logic.gameobjects.Suns;

public class SunManager {

	Game game;
	Level level;
	Random rand;
	GameObjectContainer lista;
	public static int generated = 0;
	public static int catched = 0;
	public static boolean cogido = false;
	public static final int SUNS_FOR_CYCLES = 11;// para que comience en 10

	public SunManager(Game game, Level level, Random rand, GameObjectContainer Lista) {
		generated=0;
		catched=0;
		cogido=false;
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.lista = Lista;

	}

	public boolean canAdd() {
		if (this.game.getCycle() % 5 == 0) {
			return true;
		}
		return false;
	}

	public int getCatchedSuns() {
		return catched;
	}

	public int getGeneratedSuns() {
		return generated;
	}

	public void addSuns(boolean canAdd, boolean random) {// AÑADE UN SOL

		if (canAdd == true) {
			int col = rand.nextInt(Game.NUM_COLS);
			int row = rand.nextInt(Game.NUM_ROWS);
			if (random == true) {
				Suns sun = new Suns(game, col, row, SUNS_FOR_CYCLES);
				lista.addToList(sun);
			} else {
				Suns sun = new Suns(game, col, row, SUNS_FOR_CYCLES - 1);// FIX PARA QUE TODOS EMPIECEN A 10
				lista.addToList(sun);
			}

		}
	}

	public void addRandomSun() {// AÑADE UN SOL CADA 5 TURNOS
		addSuns(canAdd(), true);
	}

}