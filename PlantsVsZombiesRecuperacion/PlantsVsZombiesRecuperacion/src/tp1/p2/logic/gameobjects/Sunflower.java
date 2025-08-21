package tp1.p2.logic.gameobjects;

import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sunflower extends Plant {
	private static int DAMAGE = 0;
	private static int ENDURANCE = 1;
	private static int COST = 25;
	private static String identificator1 = "sunflower";
	private static String identificator2 = "s";
	int ciclo_girasol;
	private static int SUN_PRODUCE = 3;

	public Sunflower(int col, int row, GameWorld game) {
		super(col, row, ENDURANCE, DAMAGE, game);
		this.ciclo_girasol = 0;
	}

	public Sunflower() {
	}

	public void setCiclo_girasol() {
		this.ciclo_girasol++;
	}

	public String getDescription() {
		return String.format(Messages.SUNFLOWER_NAME + ": cost:'" + COST + "' damage='" + DAMAGE + "' endurance='"
				+ ENDURANCE + "'");
	}

	public String getSymbol() {
		return Messages.SUNFLOWER_SYMBOL;
	}

	@Override
	public boolean update() {

		boolean generate_suncoins = false;
		this.ciclo_girasol++;
		if (this.ciclo_girasol == 3) {
			generate_suncoins = true;
			this.ciclo_girasol = 0;
		}

		return generate_suncoins;
	}

	@Override
	public boolean checkPlantName(String PlantName) {

		if (PlantName.equals(identificator1) || PlantName.equals(identificator2)) {

			return true;
		}
		return false;

	}

	public Plant spawnPlant(GameWorld game, int col, int row) {
		Sunflower sunflower = new Sunflower(col, row, game);
		return sunflower;

	}

	@Override
	public int getCost() {
		return this.COST;
	}

	public int getDamge() {
		return this.DAMAGE;
	}

	@Override
	public double getCycles() {
		return this.ciclo_girasol;
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean Catch(int col, int row) {
		// TODO Auto-generated method stub
		return false;
	}

}
