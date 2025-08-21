package tp1.p2.logic.gameobjects;

import tp1.p2.logic.Game;
import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Peashooter extends Plant {
	private static int DAMAGE = 1;
	private static int ENDURANCE = 3;
	private static final int FRECUENCY = 1;
	private static String identificator1 = "peashooter";
	private static String identificator2 = "p";
	private static int COST = 50;

	private int ciclo_peashooter;

	public Peashooter(int col, int row, GameWorld game) {
		super(col, row, ENDURANCE, DAMAGE, game);
		this.ciclo_peashooter = 0;
	}

	public Peashooter() {

	}

	public int getDamge() {
		return this.DAMAGE;
	}

	public String getSymbol() {
		return Messages.PEASHOOTER_SYMBOL;
	}

	public String getDescription() {
		return String.format(Messages.PEASHOOTER_NAME + ": cost:'" + COST + "' damage='" + DAMAGE + "' endurance='"
				+ ENDURANCE + "'");
	}

	public boolean update() {
		GameItem item;
		this.ciclo_peashooter++;
		int i = this.getCol() + 1;
		boolean found = false;
		
		if (this.ciclo_peashooter >= FRECUENCY) {
			this.ciclo_peashooter = 0;
			while(i < GameWorld.NUM_COLS && !found){ 
				item = this.game.getGameItemInPos(i, this.getRow());
				if(item != null) { 
				found = item.receivePlantAttack(Peashooter.DAMAGE); 
				}	
				i++;
			}
		}
		return false;

	}

	public boolean checkZombiePosition(int col, int row) {
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
		Peashooter peashooter = new Peashooter(col, row, game);
		return peashooter;

	}

	@Override
	public int getCost() {
		return this.COST;
	}

	@Override
	public double getCycles() {
		return this.ciclo_peashooter;
	}

	@Override
	public void onEnter() {
	}

	@Override
	public void onExit() {
	}

	@Override
	public boolean Catch(int col, int row) {
		return false;
	}

}
