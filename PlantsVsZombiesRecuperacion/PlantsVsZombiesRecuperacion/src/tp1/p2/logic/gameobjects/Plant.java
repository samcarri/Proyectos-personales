package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.p2.control.Command;
import tp1.p2.control.commands.AddPlantCommand;
import tp1.p2.control.commands.ExitCommand;
import tp1.p2.control.commands.HelpCommand;
import tp1.p2.control.commands.ListPlantsCommand;
import tp1.p2.control.commands.NoneCommand;
import tp1.p2.control.commands.ResetCommand;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public abstract class Plant extends GameObject {

	private int vida;
	private int DAMAGE;
	private int col;
	private int row;

	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(new Peashooter(), new Sunflower(), new wallnut(),
			new cherrybomb());

	public Plant() {

	}

	public Plant(int col, int row, int vida, int damage, GameWorld game) {
		super(game, col, row);
		this.vida = vida;
		this.DAMAGE = damage;

	}

	public abstract String getSymbol();

	abstract public String getDescription();

	abstract public boolean checkPlantName(String plantName);

	abstract public int getCost();

	abstract public Plant spawnPlant(GameWorld game, int col, int row);
	
	public static List<Plant> getList() {
		return AVAILABLE_PLANTS;
	}

	public int getVida() {
		return this.vida;
	}

	public boolean isEmpty(int row, int col) {// ironicamente devuelve si esta ocupado
		boolean ocupado = false;
		if (row == this.row && col == this.col) {
			ocupado = true;
		}
		return ocupado;
	}

	public int[] getPos() {
		int pos[] = new int[2];
		pos[0] = this.col;
		pos[1] = this.row;
		return pos;
	}

	@Override
	public boolean receiveZombieAttack(int damage) {
		this.vida = this.vida - damage;
		return true;
	}

	public boolean checkPlantPosition(int columna, int row) {

		boolean checked = false;

		if (this.col == columna && this.row == row) {

			checked = true;
		}
		return checked;
	}

	@Override
	public boolean receivePlantAttack(int damage) {
		return false;
	}

	public static Plant createPlant(String name, GameWorld game, int col, int row) {
		for (Plant plant : AVAILABLE_PLANTS) {
			if (plant.checkPlantName(name) == true) {
				return plant.spawnPlant(game, col, row);
			}

		}
		return null;
	}

	public void setLessVida() {
		this.vida = 0;
	}

	public boolean isAlive() {
		if (this.vida == 0) {
			return true;
		}
		return true;
	}

	public boolean Catch() {
		return false;
	}

	public boolean fillPosition() {// EN UN PRINCIPIO TODAS LAS PLANTAS VAN A SER TANGIBLES
		return true;
	}

}
