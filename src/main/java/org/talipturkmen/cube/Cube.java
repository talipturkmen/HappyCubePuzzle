package org.talipturkmen.cube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cube {
	private Piece[] pieces = new Piece[6];
	private PUZZLE type;

	public Cube(PUZZLE type) {
		this.type = type;
		construct();

	}

	public List<Piece> getPieces() {
		return new ArrayList<Piece>(Arrays.asList(pieces));
	}

	public PUZZLE getType() {
		return type;
	}

	public void setType(PUZZLE type) {
		this.type = type;
	}

	/**
	 * Construction of Puzzle
	 */
	private void construct() {
		switch (this.type) {
		case BLUE:
			pieces[0] = new Piece(0, 4, 4, 4, 4);
			pieces[1] = new Piece(1, 21, 27, 21, 27);
			pieces[2] = new Piece(2, 4, 10, 4, 4);
			pieces[3] = new Piece(3, 10, 5, 26, 26);
			pieces[4] = new Piece(4, 10, 11, 20, 10);
			pieces[5] = new Piece(5, 10, 11, 27, 4);
			break;

		case RED:
			pieces[0] = new Piece(0, 3, 21, 11, 4);
			pieces[1] = new Piece(1, 10, 4, 8, 10);
			pieces[2] = new Piece(2, 13, 27, 19, 26);
			pieces[3] = new Piece(3, 4, 4, 4, 10);
			pieces[4] = new Piece(4, 6, 10, 20, 10);
			pieces[5] = new Piece(5, 12, 5, 27, 20);
			break;

		case PURPLE:
			pieces[0] = new Piece(0, 26, 2, 4, 28);
			pieces[1] = new Piece(1, 3, 20, 10, 12);
			pieces[2] = new Piece(2, 8, 4, 4, 10);
			pieces[3] = new Piece(3, 27, 24, 10, 20);
			pieces[4] = new Piece(4, 5, 28, 22, 7);
			pieces[5] = new Piece(5, 11, 20, 26, 3);
			break;

		case YELLOW:
			pieces[0] = new Piece(0, 4, 4, 10, 10);
			pieces[1] = new Piece(1, 5, 24, 10, 10);
			pieces[2] = new Piece(2, 5, 26, 20, 7);
			pieces[3] = new Piece(3, 21, 26, 20, 27);
			pieces[4] = new Piece(4, 4, 10, 26, 5);
			pieces[5] = new Piece(5, 10, 5, 11, 4);
			break;
		default:
			pieces[0] = new Piece(0, 31, 31, 31, 31);
			pieces[1] = new Piece(1, 31, 31, 31, 31);
			pieces[2] = new Piece(2, 31, 31, 31, 31);
			pieces[3] = new Piece(3, 31, 31, 31, 31);
			pieces[4] = new Piece(4, 31, 31, 31, 31);
			pieces[5] = new Piece(5, 31, 31, 31, 31);
			break;

		}
	}

}
