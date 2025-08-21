package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class ExplosiveZombie extends Zombie {
	private static int DAMAGE = 1;
	private static int EXPLOSIVE_DAMAGE = 10;
	private static int ENDURANCE = 5;
	private double ciclo_zombie;
	private static double SPEED = 0.5;
	private static int COST = 0; // AUNQUE REALMENTE NO TIENE PERO BUE
	private int NUM = 3;

	public ExplosiveZombie(int col, int row, GameWorld game) {
		super(game, col, row, ENDURANCE, DAMAGE);
		this.ciclo_zombie = 0;

	}

	public ExplosiveZombie() {

	}

	public String getSymbol() {
		return Messages.EXPLOSIVEZOMBIE_SYMBOL;
	}

	public String getDescription() {
		return String.format(Messages.EXPLOSIVEZOMBIE_NAME + ": speed:'" + SPEED + "' damage='" + DAMAGE
				+ "' endurance='" + ENDURANCE + "'");
	}

	public boolean update() {
		boolean move = false;
		this.ciclo_zombie = this.ciclo_zombie + SPEED;
		if (game.positionFilled(this.col - 1, this.row) == false) {
			if (this.ciclo_zombie == 1) {
				this.col--;
				this.ciclo_zombie = 0;
			}
		} else {
			this.ciclo_zombie = 0;
			GameItem item=game.getGameItemInPos(col-1, row);
			if(item!=null) item.receiveZombieAttack(DAMAGE);

		}
		if (this.getVida() == 0) {
			game.pushAction(new ExplosionAction(this.col, this.row, EXPLOSIVE_DAMAGE, false));
			this.onExit();
		}

		return false;
	}

	public boolean isInPosition(int col, int row) {
		if (col == this.col && row == this.row) {
			return true;
		}
		return false;
	}

	public boolean checkZombiePosition(int col, int row) {
		return isInPosition(col, row);
	}

	public int getDamge() {
		if (this.getVida() <= 0) {

			return EXPLOSIVE_DAMAGE;
		}
		return DAMAGE;
	}

	@Override
	public boolean matchNum(int num) {
		if (num == this.NUM) {
			return true;
		}
		return false;
	}

	@Override
	public Zombie createZombie(int col, int row, GameWorld game) {
		return new ExplosiveZombie(col, row, game);
	}

	@Override
	public boolean checkZombieNum(int ZombieIndice) {
		if (ZombieIndice == this.NUM) {
			return true;
		}
		return false;
	}

	@Override
	public double getCycles() {
		return this.ciclo_zombie;
	}

	@Override
	public void onEnter() {

	}

	@Override
	public void onExit() {
		game.pushAction(new ExplosionAction(getCol(), getRow(), EXPLOSIVE_DAMAGE, false));
	}

	@Override
	public boolean Catch(int col, int row) {
		return false;
	}

}
