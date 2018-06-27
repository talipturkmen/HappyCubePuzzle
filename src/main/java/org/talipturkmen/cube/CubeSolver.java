package org.talipturkmen.cube;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cube Solver Solves 6 pieces Happy Cube by Brute Force
 * 
 * @author talip.turkmen
 *
 */
public class CubeSolver {
	private List<Piece> solvedPieces = new ArrayList<Piece>();
	Map<Integer, List<Integer>> unmatchedPieces = new HashMap<Integer, List<Integer>>();

	/**
	 * 
	 * Loops over every possible matches in all edge rotations. If cube is completed
	 * index reaches 6
	 * 
	 * @param index
	 * @param pieces
	 */
	public void solve(int index, List<Piece> pieces) {
		if (index == 6) {
			System.out.println("Solved!!!!");
			draw();
			return;
		}
		boolean unmatchedPiece = true;
		Piece unmatchedCheckPiece = null;
		for (int i = 0; i < pieces.size(); i++) {

			if (!isUnmatchedPairs(pieces.get(i), unmatchedCheckPiece)) {
				for (Piece rotated : pieces.get(i).getAllPositions()) {
					addSolvedPieces(index + 1, rotated);
					if (solvedPieces.size() == 1 || checkPieceConsistency(index)) {
						unmatchedPiece = false;
						Piece temp = pieces.get(i);
						pieces.remove(i);
						System.out.println(index);
						solve(index + 1, pieces);
						pieces.add(i, temp);
					}
					solvedPieces.remove(rotated);
					unmatchedCheckPiece = rotated;
				}

				// In order to prevent redundant check we omit unmatched pairs
				if (unmatchedPiece && i != 0) {
					addUnmatchedPairs(pieces.get(i), unmatchedCheckPiece);
				}
			}
		}
	}

	private boolean isUnmatchedPairs(Piece current, Piece pre) {
		return pre != null && unmatchedPieces.containsKey(current.getName())
				&& unmatchedPieces.get(current.getName()).contains(pre.getName());
	}

	private void addUnmatchedPairs(Piece current, Piece pre) {
		if (!unmatchedPieces.containsKey(current.getName())) {
			List<Integer> list = new ArrayList<Integer>();
			list.add(pre.getName());
			unmatchedPieces.put(current.getName(), list);
		} else if (!unmatchedPieces.get(current.getName()).contains(pre.getName())) {
			unmatchedPieces.get(current.getName()).add(pre.getName());
		}
		if (!unmatchedPieces.containsKey(pre.getName())) {
			List<Integer> list = new ArrayList<Integer>();
			list.add(current.getName());
			unmatchedPieces.put(pre.getName(), list);
		} else if (!unmatchedPieces.get(pre.getName()).contains(current.getName())) {
			unmatchedPieces.get(pre.getName()).add(current.getName());
		}
	}

	public void draw() {
		if (solvedPieces.size() != 6) {
			System.out.println("Please firstly solve problem");
			return;
		}
		for (int i = 0; i < 5; i++) {
			System.out.print("     ");
			solvedPieces.get(3).drawLine(i);
			System.out.print("     ");
			System.out.println();
		}
		for (int i = 0; i < 5; i++) {
			solvedPieces.get(1).drawLine(i);
			solvedPieces.get(0).drawLine(i);
			solvedPieces.get(2).drawLine(i);
			System.out.println();
		}
		for (int j = 4; j < 6; j++)// draw 5th and 6th
			for (int i = 0; i < 5; i++) {
				System.out.print("     ");
				solvedPieces.get(j).drawLine(i);
				System.out.print("     ");
				System.out.println();
			}
	}

	/**
	 * Check two pieces compatibleness
	 * 
	 * @param index
	 * @return
	 */
	private boolean checkPieceConsistency(int index) {
		boolean isConsistent = true;
		for (Edge edge : solvedPieces.get(index).getEdges()) {
			if (!isConsistent)
				return false;
			if (edge.getPre() != null) {
				isConsistent = checkEdgeConsistency(edge.getPre(), edge);
				if (isConsistent)
					for (Corner corner : edge.getCorners()) {
						isConsistent = checkCornerConsistency(corner);
					}
			}
		}
		return isConsistent;
	}

	private boolean checkEdgeConsistency(Edge preEdge, Edge currentEdge) {
		boolean isConsistent = true;
		char[] pre = preEdge.getValue().toCharArray();
		char[] current = currentEdge.getValue().toCharArray();
		for (int i = 0; i < 5; i++) {
			if (!isConsistent)
				return false;
			boolean firstBool = pre[i] == '1' ? true : false;
			boolean secondBool = current[i] == '1' ? true : false;

			if (i % 4 == 0) {
				isConsistent = firstBool ? !secondBool : true; // NAND if corner allow 1-0 or 0-1 or 0-0 because third
																// piece may fill it and we control it on
																// checkCornerConsistency
			}
			isConsistent = firstBool ^ secondBool; // XOR 1-0 or 0-1 is consistent
		}
		return isConsistent;
	}

	private boolean checkCornerConsistency(Corner corner) {
		boolean X = corner.getX() == '1' ? true : false;
		boolean Y = corner.getY() == '1' ? true : false;
		boolean Z = corner.getZ() == '1' ? true : false;
		if (X && Y && Z) // 1-1-1 is not compatible
			return false;
		else
			return X ^ Y ^ Z; // XOR 101 010 001 is compatible

	}

	private void addSolvedPieces(int index, Piece piece) {
		switch (index) {
		case 1:
			addFirstPiece(piece);
			break;
		case 2:
			addSecondPiece(piece);
			break;
		case 3:
			addThirdPiece(piece);
			break;
		case 4:
			addFourthPiece(piece);
			break;
		case 5:
			addFifthPiece(piece);
			break;
		case 6:
			addSixthPiece(piece);
			break;
		default:
			break;
		}
	}

	private void addFirstPiece(Piece piece) {
		solvedPieces.add(piece);
	}

	private void addSecondPiece(Piece piece) {
		piece.getEdge(1).setPre(solvedPieces.get(0).getEdge(3));
		solvedPieces.add(piece);
	}

	private void addThirdPiece(Piece piece) {
		piece.getEdge(3).setPre(solvedPieces.get(0).getEdge(1));
		solvedPieces.add(piece);
	}

	private void addFourthPiece(Piece piece) {
		Edge firstEdgeOfPiece1 = solvedPieces.get(0).getEdge(0);
		Edge firstEdgeOfPiece2 = solvedPieces.get(1).getEdge(0);
		Edge firstEdgeOfPiece3 = solvedPieces.get(2).getEdge(0);
		piece.getEdge(1).setPre(firstEdgeOfPiece3);
		piece.getEdge(2).setPre(firstEdgeOfPiece1);
		piece.getEdge(3).setPre(firstEdgeOfPiece2);

		// Create Corner
		Corner corner1 = createCorner(piece.getEdge(2), 0, firstEdgeOfPiece1, 0, firstEdgeOfPiece2, 4);
		Corner corner2 = createCorner(piece.getEdge(2), 4, firstEdgeOfPiece1, 4, firstEdgeOfPiece3, 0);
		piece.getEdge(2).addCorner(corner1);
		piece.getEdge(2).addCorner(corner2);

		solvedPieces.add(piece);

	}

	private void addFifthPiece(Piece piece) {
		Edge thirdEdgeOfPiece1 = solvedPieces.get(0).getEdge(2);
		Edge forthEdgeOfPiece2 = solvedPieces.get(1).getEdge(3);
		Edge thirdEdgeOfPiece3 = solvedPieces.get(2).getEdge(2);

		piece.getEdge(0).setPre(thirdEdgeOfPiece1);
		piece.getEdge(1).setPre(thirdEdgeOfPiece3);
		piece.getEdge(3).setPre(forthEdgeOfPiece2);

		// Add corner Info
		Corner corner1 = createCorner(piece.getEdge(0), 0, thirdEdgeOfPiece1, 0, forthEdgeOfPiece2, 4);
		Corner corner2 = createCorner(piece.getEdge(0), 4, thirdEdgeOfPiece1, 4, thirdEdgeOfPiece3, 0);
		piece.getEdge(0).addCorner(corner1);
		piece.getEdge(0).addCorner(corner2);

		solvedPieces.add(piece);

	}

	private void addSixthPiece(Piece piece) {
		Edge firstEdgeOfPiece4 = solvedPieces.get(3).getEdge(0);
		Edge secondEdgeOfPiece3 = solvedPieces.get(2).getEdge(1);
		Edge thirdEdgeOfPiece5 = solvedPieces.get(4).getEdge(2);
		Edge forthEdgeOfPiece2 = solvedPieces.get(1).getEdge(3);
		piece.getEdge(0).setPre(firstEdgeOfPiece4);
		piece.getEdge(1).setPre(secondEdgeOfPiece3);
		piece.getEdge(2).setPre(thirdEdgeOfPiece5);
		piece.getEdge(3).setPre(forthEdgeOfPiece2);

		// Add corner Info
		Corner corner1 = createCorner(piece.getEdge(0), 0, firstEdgeOfPiece4, 0, forthEdgeOfPiece2, 0);
		Corner corner2 = createCorner(piece.getEdge(0), 4, firstEdgeOfPiece4, 4, secondEdgeOfPiece3, 0);
		Corner corner3 = createCorner(piece.getEdge(2), 0, thirdEdgeOfPiece5, 0, forthEdgeOfPiece2, 4);
		Corner corner4 = createCorner(piece.getEdge(2), 4, thirdEdgeOfPiece5, 4, secondEdgeOfPiece3, 4);
		piece.getEdge(0).addCorner(corner1);
		piece.getEdge(0).addCorner(corner2);
		piece.getEdge(2).addCorner(corner3);
		piece.getEdge(2).addCorner(corner4);

		solvedPieces.add(piece);

	}

	private Corner createCorner(Edge edge1, int index1, Edge edge2, int index2, Edge edge3, int index3) {
		char x = edge1.getValue().charAt(index1);
		char y = edge2.getValue().charAt(index2);
		char z = edge3.getValue().charAt(index3);
		Corner corner = new Corner(x, y, z);
		return corner;
	}

}
