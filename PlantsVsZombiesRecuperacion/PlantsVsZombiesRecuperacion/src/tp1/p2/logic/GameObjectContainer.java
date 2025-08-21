package tp1.p2.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Plant;

public class GameObjectContainer {
	private List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<GameObject>();
	}

	public String getSymbol(int col, int row) {
		String objeto_string = "", sol = "", cadena_entera = "";
		GameObject objeto;
		for (int i = 0; i < gameObjects.size(); i++) {
			objeto = gameObjects.get(i);

			if (objeto.getCol() == col && objeto.getRow() == row && objeto.fillPosition() == false) {
				sol = objeto.getSymbol() + "[0" + (int) objeto.getCycles() + "]";

			}
		}
		for (int i = 0; i < gameObjects.size(); i++) {
			objeto = gameObjects.get(i);

			if (objeto.getCol() == col && objeto.getRow() == row && objeto.fillPosition() == true) {
				objeto_string = objeto.getSymbol() + "[0" + objeto.getVida() + "]";

			}

		}
		cadena_entera = sol + objeto_string;
		return cadena_entera;
	}

	public boolean isEmpty(int col, int row) {
		boolean vacio = true;
		GameObject objeto;
		for (int i = 0; i < gameObjects.size(); i++) {
			objeto = gameObjects.get(i);
			if (objeto.getCol() == col && objeto.getRow() == row && objeto.fillPosition()) {
				return false;
			}
		}
		return vacio;
	}

	public void addToList(GameObject object) {
		gameObjects.add(object);
	}

	public boolean deleteDead() {
		boolean muertos = false;
		GameObject objeto = null;
		for (int i = 0; i < gameObjects.size(); i++) {
			objeto = gameObjects.get(i);
			if (objeto.getVida() <= 0) {
				objeto.onExit();
				gameObjects.remove(i);
				muertos = true;
			}
		}
		return muertos;
	}

	public int update() {
		GameObject objeto;
		int contador_generate = 0;
		for (int i = 0; i < gameObjects.size(); i++) {
			objeto = gameObjects.get(i);
			if (objeto.update() == true) {
				contador_generate++;
			}

		}

		return contador_generate;
	}

	public GameItem getObjectInPos(int col, int row) {
		for (GameObject objeto : gameObjects) {
			if (objeto.fillPosition() && objeto.isInPosition(col, row)) {
				return objeto;
			}
		}
		return null;
	}

	public GameItem getCatchableObjectInPos(int col, int row) {
		GameObject objeto = null;

		for (int i = 0; i < gameObjects.size(); i++) {
			objeto = gameObjects.get(i);
			if (objeto != null) {
				if (objeto.isInPosition(col, row) && objeto.fillPosition() == false) {
					return objeto;
				}
			}
		}
		return null;
	}

	public int getSize() {
		return this.gameObjects.size();
	}

	public boolean checkPos(int col, int row) {
		for (GameObject objeto : gameObjects) {
			if (objeto.isInPosition(col, row) && objeto.fillPosition()) {// LOS SOLES NO SON ¨TANGIBLES¨
				return true;
			}
		}
		return false;
	}

}