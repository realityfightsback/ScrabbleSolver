package model;

public class PossibleMove {

	int x;
	int y;
	boolean canGoRight = false;
	boolean canGoDown = false;

	public PossibleMove(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean hasMove() {
		return canGoDown || canGoRight;
	}

	@Override
	public String toString() {
		return "PossibleMove [x=" + x + ", y=" + y + ", canGoRight="
				+ canGoRight + ", canGoDown=" + canGoDown + "]";
	}

}
