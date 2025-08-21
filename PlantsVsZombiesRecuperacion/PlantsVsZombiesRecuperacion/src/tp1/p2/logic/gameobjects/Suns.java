package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.SunManager;

public class Suns extends GameObject {
	private int ciclos_sun;
	private int vida;
	private int col, row;

	public Suns(GameWorld game, int col, int row, int ciclos) {
		super(game, col, row);
		this.vida = 10;// AUNQUE REALMENTE NO TIENE
		this.ciclos_sun =ciclos;// AÑADIMOS UNO MAS PARA QUE COMIENCE EN 10 AL PRINCIPIO

	};

	@Override
	public boolean receiveZombieAttack(int damage) {
		return false;
	}

	@Override
	public boolean receivePlantAttack(int damage) {
		return false;
	}


	@Override
	public String getSymbol() {
		return ("*");
	}

	@Override
	public String getDescription() {
		return ("*");
	}

	@Override
	public boolean isEmpty(int row, int col) {// ironicamente devuelve si esta ocupado
		return false; //no es un objeto tangible
	}

	@Override
	public boolean update() {
		if(this.ciclos_sun==11) {
			this.onEnter();
		}
		this.ciclos_sun--;
		if (this.ciclos_sun == 0) {
			this.vida = 0;
			return true;
		}

		return false;
	}

	@Override
	public void onEnter() {
		SunManager.generated++;
	}

	@Override
	public void onExit() {
	}

	@Override
	public int getDamge() {
		return 0;
	}

	@Override
	public int getVida() {
		return this.ciclos_sun;//esta es la vida realmente.
	}

	public boolean checkSuns() {// DEVUELVE SI EL OBJETO ES UN SOL
		return true;
	}

	@Override
	public double getCycles() {
		return this.ciclos_sun;
	}

	@Override
	public boolean isAlive() {
		if (this.ciclos_sun == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean fillPosition() {
	//El sol no ocupa una posicion fisica dentro del tablero
		return false;
	}

	@Override
	public boolean Catch(int col, int row) {
		this.ciclos_sun=0;
		this.vida = 0;
		return true;
	}


	


}
