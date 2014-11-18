package model;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import constants.Constants;

public class Board {

	public static int X_DIMENSION = 15;
	public static int Y_DIMENSION = 15;

	String[][] positions = null;

	public static void main(String[] args) throws IOException {

		Board b = new Board();

		b.readInBoard(Constants.DEFAULT_BOARD);

		b.placeTilesRight(3, 0, "cat");
		b.printBoard();
		System.out.println(b.getTile(3, 0));

	}

	private void initializeArray() {

		positions = new String[Y_DIMENSION][X_DIMENSION];

		for (int i = 0; i < Y_DIMENSION; i++) {
			for (int j = 0; j < X_DIMENSION; j++) {
				positions[i][j] = "";
			}
		}
	}

	public void readInBoard(String fileName) throws IOException {

		initializeArray();

		List<String> rows = FileUtils.readLines(new File(fileName));
		int x = 0;
		int y = 0;
		for (String row : rows) {
			String splitRows[] = row.split("  ");

			for (String splitRow : splitRows) {
				if (splitRow.equals("_") == false) {
					setTile(x, y, splitRow);
				}
				x++;

			}
			x = 0;
			y++;

		}

	}

	public void printBoard() {
		for (int i = 0; i < positions.length; i++) {

			String[] row = positions[i];
			for (int j = 0; j < row.length; j++) {
				if (row[j].equals("")) {
					System.out.print(" _ ");
				} else {
					System.out.print(String.format(" %s ", row[j]));
				}

			}
			System.out.print("\n");

		}
	}

	public void placeTilesRight(int x, int y, String word) {

		int wordCtr = 0;

		int xGuard = x + word.length();

		while (x < xGuard) {
			setTile(x, y, word.substring(wordCtr, wordCtr + 1));
			x++;
			wordCtr++;

		}

	}

	public String getTile(int x, int y) {
		if (x < 0 || y < 0 || y > Y_DIMENSION - 1 || x > X_DIMENSION - 1) {
			return "";
		}

		return positions[y][x];
	}

	public void setTile(int x, int y, String s) {
		if (x < 0 || y < 0 || y > Y_DIMENSION - 1 || x > X_DIMENSION - 1) {
			throw new IllegalArgumentException(String.format(
					"Argument x:%d or y:%d out of bounds", x, y));
		}
		positions[y][x] = s;

	}

	public void locationCanReachExistingStructure(int x, int y) {

	}
}
