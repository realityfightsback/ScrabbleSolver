package model;

import java.io.IOException;
import java.util.ArrayList;

import constants.Constants;

public class Simulator {

	static Board b = new Board();

	static String tiles = "adswedf";

	public static void main(String[] args) throws IOException {

		// Sets up board + tiles in hand
		b.X_DIMENSION = 15;
		b.Y_DIMENSION = 1;	
		loadBoard(Constants.SINGLE_ROW_BOARD, "adswedf");

		b.printBoard();

		findTilesThatCanReachStructure();

		//
		// generatePossibleMoves();
		//
		// scoreMoves();
		//
		// printMoves();

	}

	private static void findTilesThatCanReachStructure() {
		ArrayList<PossibleMove> moves = new ArrayList<PossibleMove>();

		for (int y = 0; y < Board.Y_DIMENSION; y++) {
			for (int x = 0; x < Board.X_DIMENSION; x++) {

				System.out.println(String.format("Checking(x,y): (%d,%d)",x,y));
				// Already occupied
				if (b.getTile(x, y).equals("") == false) {
					System.out.println("Already Occupied");
					continue;
				}

				PossibleMove pMove = new PossibleMove(x, y);
				// Empty, but can it connect to the Scrabble structure?
				checkRight(x, y, pMove);

				checkDown(x, y, pMove);

				if (pMove.hasMove()) {
					moves.add(pMove);
				}
			}

		}

		for (PossibleMove possibleMove : moves) {
			System.out.println(possibleMove);
		}
	}

	private static void checkDown(int x, int y, PossibleMove pMove) {
		boolean tileFound = false;

		for (int i = y; i < y + tiles.length() + 1; i++) {
			if (b.getTile(x, i).equals("") == false)
				tileFound = true;
		}

		for (int i = y; i < y + tiles.length(); i++) {
			if (b.getTile(x + 1, i).equals("") == false)
				tileFound = true;
		}

		for (int i = y; i < y + tiles.length(); i++) {
			if (b.getTile(x - 1, i).equals("") == false)
				tileFound = true;
		}

		pMove.canGoDown = tileFound;
	}

	/**
	 * Checks for contacted tiles to the right, above, below, and on the line
	 * moving to the right from the x,y
	 * 
	 * @param x
	 * @param y
	 * @param pMove
	 * @return contactMade?
	 */
	private static void checkRight(int x, int y, PossibleMove pMove) {

		boolean tileFound = false;
		int minimumLength = -1;

//		if(b.getTile(x-1, y))
		
		for (int i = x; i < x + tiles.length() + 1; i++) {
			if (b.getTile(i, y).equals("") == false) {
				tileFound = true;
				// pMove.setMinimumRight()
			}
		}

		for (int i = x; i < x + tiles.length(); i++) {
			if (b.getTile(i, y + 1).equals("") == false)
				tileFound = true;
		}

		for (int i = x; i < x + tiles.length(); i++) {
			if (b.getTile(i, y - 1).equals("") == false)
				tileFound = true;
		}

		pMove.canGoRight = tileFound;
	}

	private static void loadBoard(String fileName, String tiles)
			throws IOException {
		b.readInBoard(fileName);
	}

}
