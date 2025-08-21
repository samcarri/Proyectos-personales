#include <iostream>
#include <vector>
using namespace std;

const int ROWS = 5;
const int COLS = 5;
const int numFlowers = 4;

void initializeFlowerField(vector<vector<char>>& field) {
	for (int i = 0; i < ROWS; ++i) {
		for (int j = 0; j < COLS; ++j) {
			field[i][j] = '-';
		}
	}
	srand(time(0));
	int randomX = rand() % ROWS;
	int randomY = rand() % COLS;
	for (int i = 0; i < numFlowers; i++) {
		while (field[randomX][randomY] == '*') {
			randomX = rand() % ROWS;
			randomY = rand() % COLS;
		}
		field[randomX][randomY] = '*';
	}
}
void initializeNumericalField(vector<vector<int>>& numericalField, const vector<vector<char>>& flowerField) {
	for (int i = 0; i < ROWS; i++) {
		for(int j=0; j<COLS; j++) {
			if (flowerField[i][j] == '*') {
				numericalField[i][j] = -1; // Flower position
			} else {
				int count = 0;
				for (int x = max(0, i - 1); x <= min(ROWS - 1, i + 1); x++) {
					for (int y = max(0, j - 1); y <= min(COLS - 1, j + 1); y++) {
						if (flowerField[x][y] == '*') {
							count++;
						}
					}
				}
				numericalField[i][j] = count;
			}
		}
	}
}
void printFlowerField(const vector<vector<char>>& field) {
	for (const auto& row : field) {
		for (char cell : row) {
			cout << cell << " ";
		}
		cout << endl;
	}
}

void printNumericalField(const vector<vector<int>>& numericalField) {
	for (const auto& row : numericalField) {
		for (int cell : row) {
			if (cell == -1) {
				cout << "* ";
			} else {
				cout << cell << " ";
			}
		}
		cout << endl;
	}
}

void playableLoop(vector<vector<int>>& numericalField, const vector<vector<char>>& flowerField) {
	bool gameOver = false;
	vector<vector<int>> discoveredField(ROWS, vector<int>(COLS, -2));
	while(!gameOver) {
		int x, y;
		printNumericalField(discoveredField);
		cout << "Ingrese las coordenadas (x y) para descubrir una celda: ";
		cin >> x >> y;
		if (x < 0 || x >= ROWS || y < 0 || y >= COLS) {
			cout << "Coordenadas fuera de rango. Intente de nuevo." << endl;
			continue;
		}
		if (numericalField[x][y] == -1) {
			cout << "¡Has encontrado una flor! Fin del juego." << endl;
			gameOver = true;
		} else {
			cout << "Celda descubierta: " << numericalField[x][y] << endl;
			discoveredField[x][y] = numericalField[x][y]; 
			numericalField[x][y] = -2; // Mark as discovered
			

		}
	}
}

int main() {
	vector<vector<char>> flowerField(ROWS, vector<char>(COLS, 0));
	vector<vector<int>> numericalField(ROWS, vector<int>(COLS, 0));
	initializeFlowerField(flowerField);
	initializeNumericalField(numericalField, flowerField);
	
	cout << "Todo inicializado en orden" << endl;
	playableLoop(numericalField, flowerField);

	return 0;
}