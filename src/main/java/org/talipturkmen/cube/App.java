package org.talipturkmen.cube;

/**
 * 
 * @author talip.turkmen
 *
 */
public class App {

	public static void main(String[] args) {

		Cube cube = new Cube(PUZZLE.BLUE);
		CubeSolver solver = new CubeSolver();
		solver.solve(0, cube.getPieces());

	}

}
