package tp1.p2.logic.actions;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.GameObject;

public class ExplosionAction implements GameAction {

	private int col;

	private int row;

	private int damage;

	boolean explotadura;//true explota planta false explota zombie
	
	public ExplosionAction(int col, int row, int damage, boolean explotadura) {
		this.col = col;
		this.row = row;
		this.damage = damage;
		this.explotadura=explotadura;
	}

	@Override
	public void execute(GameWorld game) {
		GameObject objeto;
		//UTILIZAMOS FORS PARA QUE LA ACCION SE REALICE EN AREA
	for(int i=row-1; i<=this.row+1; i++) {
		for(int j=this.col-1; j<=this.col+1; j++) {
			GameItem item=null;
			item=game.getGameItemInPos(j, i);
			if(item!=null) {
				if(this.explotadura==true) {//SI EL BOOLEANO EL TRUE LA PLANTA EXPLOTA
					item.receivePlantAttack(damage);
				}
				else {//SI EL BOOLEANO ES FALSE EXPLOTA EL ZOMBIE
					item.receiveZombieAttack(damage);
				}
			}
		}
	}
		
	
		
	}

}