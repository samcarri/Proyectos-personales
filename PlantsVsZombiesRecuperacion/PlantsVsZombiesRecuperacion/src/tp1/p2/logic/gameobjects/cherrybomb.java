package tp1.p2.logic.gameobjects;

import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class cherrybomb extends Plant {
	private static int DAMAGE = 10;
	private static int ENDURANCE = 3;
	private int ciclo_cherrybomb;
	private static String identificator1 = "cherrybomb";
	private static String identificator2 = "c";
	private static int COST = 50;

	public cherrybomb(int col, int row, GameWorld game) {
		super(col, row, ENDURANCE, DAMAGE, game);
		this.ciclo_cherrybomb = 0;
	}

	public cherrybomb() {

	}

	public int getDamge() {
		return DAMAGE;
	}

	public String getSymbol() {
		if (ciclo_cherrybomb == 1) {
			return Messages.CHERRYBOMB_EXPLOTION;
		} else {
			return Messages.CHERRYBOMB_NORMAL;
		}
	}

	public String getDescription() {
		return String.format(Messages.CHERRYBOMB_NAME + ": cost:'" + COST + "' damage='" + DAMAGE + "' endurance='"
				+ ENDURANCE + "'");
	}

	public boolean update() {

		this.ciclo_cherrybomb++;
		if (this.ciclo_cherrybomb == 2) {
			this.setLessVida();

		}
		return false;

	}

	@Override
	public boolean checkPlantName(String PlantName) {

		if (PlantName.equals(identificator1) || PlantName.equals(identificator2)) {

			return true;
		}
		return false;
	}

	@Override
	public Plant spawnPlant(GameWorld game, int col, int row) {

		cherrybomb cherrybomb = new cherrybomb(col, row, game);
		return cherrybomb;

	}

	@Override
	public int getCost() {
		return COST;
	}

	@Override
	public double getCycles() {
		return this.ciclo_cherrybomb;
	}

	@Override
	public void onEnter() {

	}

	@Override
	public void onExit() {
		game.pushAction(new ExplosionAction(getCol(), getRow(), DAMAGE, true));
	}

	@Override
	public boolean Catch(int col, int row) {
		// TODO Auto-generated method stub
		return false;
	}

}
