package tp1.p2.logic.gameobjects;

import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class wallnut extends Plant{
	private static int DAMAGE = 0;
	private static int ENDURANCE = 10;
	private static int COST = 50;
	private static String identificator1 = "wallnut";
	private static String identificator2 = "w";

	int ciclo_wallnut;
	

	public wallnut(int col, int row, GameWorld game) {
		super(col, row, ENDURANCE, DAMAGE, game);
		this.ciclo_wallnut=0;
	}

	public wallnut() {
		
	}
	
	public void setCiclo_girasol() {
		this.ciclo_wallnut++;
	}

	public String getDescription() {
		return String.format(Messages.WALLNUT_NAME+": cost:'"+ COST+"' damage='"+DAMAGE+"' endurance='" +ENDURANCE+"'");
	}

	public String getSymbol() {
		return Messages.WALLNUT_SYMBOL;
	}

	@Override
	public boolean update() {
	
		return false;
	}


	@Override
	public boolean checkPlantName(String PlantName) {

		if (PlantName.equals(identificator1)  || PlantName.equals(identificator2)) {
			return true;
		}
		return false;

	}

	public Plant spawnPlant( GameWorld game, int col, int row) {
		
			wallnut wallnut=new wallnut(col, row, game);
			return wallnut;
		
	
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
	return this.ciclo_wallnut;
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
