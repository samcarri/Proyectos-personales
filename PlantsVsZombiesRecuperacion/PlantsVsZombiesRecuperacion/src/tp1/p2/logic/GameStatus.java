package tp1.p2.logic;

public interface GameStatus {
	static final int suncoins=50;
	static final int ciclos=0;
	static final int points=0;
	
	
	int getCycle();

	int getSuncoins();

	String positionToString(int col, int row);

	int getRemainingZombies();

	boolean isPlayerQuits();

	boolean playerLoose();

	void addSuncoins();

	void addPoints(int points);
	
}
