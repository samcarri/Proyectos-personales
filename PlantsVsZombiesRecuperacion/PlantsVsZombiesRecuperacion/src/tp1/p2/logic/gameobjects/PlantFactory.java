package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;


public class PlantFactory {

	/* @formatter:off */
	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(
new Sunflower(),
new Peashooter(),
new wallnut(),
new cherrybomb()
	);
	/* @formatter:on */

	public static boolean isValidPlant(String plantName) {
		for (Plant plants : AVAILABLE_PLANTS) {
			if (plants.checkPlantName(plantName) == true) {
				return true;
			}
		}
		return false;
	}

	public static Plant spawnPlant(String plantName, GameWorld gameworld, int col, int row) throws GameException {
		Plant planta;
		planta = Plant.createPlant(plantName, gameworld, col, row);
		return planta;
	}

	public static List<Plant> getAvailablePlants() {
		return Collections.unmodifiableList(AVAILABLE_PLANTS);
	}

}
