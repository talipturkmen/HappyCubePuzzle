package org.talipturkmen.cube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Every Piece has 4 edges. Each of this edge is decoded by 1 and 0
 *   	 ABCDEF         1st edge => From A to B 
 *       RXXXXG			2nd edge => From F to K
 *       SXXXXH 		3rd edge => From L to K
 *       TXXXXJ 		4th edge => From A to L
 *       LMNOPK     
 *      
 *       * * *      	10101		 		
 *        ****     		0	1			 
 *        ***	=>		0	0		=> This puzzle can be decoded as edges= 21, 25, 19, 19 
 *       ****			1	0			
 *       *  **			10011			
 * 
 */

public class Piece {

	private Edge[] edges = new Edge[4];
	private int name;

	public Piece(Edge[] edges, int name) {
		this.edges = edges;
		this.name = name;
	}

	public Piece(int name, int topEdge, int rightEdge, int bottomEdge, int leftEdge) {
		this.edges[0] = new Edge(topEdge);
		this.edges[1] = new Edge(rightEdge);
		this.edges[2] = new Edge(bottomEdge);
		this.edges[3] = new Edge(leftEdge);
		this.name = name;
		this.draw();
		System.out.println();
	}

	public List<Piece> getAllPositions() {

		List<Piece> positions = new ArrayList<Piece>();
		Piece copy = this;

		for (int i = 0; i < 4; i++) {
			positions.add(copy);
			copy.rotateClockWise();
		}
		copy.flipTopDown();
		for (int i = 0; i < 3; i++) {
			positions.add(copy);
			copy.rotateClockWise();
		}

		return positions;
	}

	public Edge[] getEdges() {
		return edges;
	}

	public Edge getEdge(int index) {
		return edges[index];
	}

	public void setEdges(Edge[] edges) {
		this.edges = edges;
	}

	public void setEdge(int index, Edge edge) {
		this.edges[index] = edge;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	/*
	 * Rotate Piece 90 degree clockwise
	 */
	public void rotateClockWise() {
		Edge temp = edges[3];

		for (int i = 1; i < edges.length; i++) {
			edges[i] = edges[i - 1];
		}

		edges[0] = temp;
	}

	/*
	 * Flip piece in direction of up-bottom
	 */
	public void flipTopDown() {
		Edge temp = edges[2];
		edges[1].setValue(Toolkit.reverseBinary(edges[1].getValue()));
		edges[3].setValue(Toolkit.reverseBinary(edges[3].getValue()));
		edges[2] = edges[0];
		edges[0] = temp;
	}

	/*
	 * Flip piece in direction of right-left
	 */
	public void flipRightLeft() {
		Edge temp = edges[1];
		edges[0].setValue(Toolkit.reverseBinary(edges[0].getValue()));
		edges[2].setValue(Toolkit.reverseBinary(edges[2].getValue()));
		edges[1] = edges[3];
		edges[3] = temp;
	}

	public void draw() {
		String[] rep = new String[5];

		for (int i = 0; i < 5; i++) {
			if (i == 0) {
				rep[i] = edges[i].getValue();
			} else if (i == 4) {
				rep[i] = edges[2].getValue();

			} else {
				rep[i] = edges[3].getValue().charAt(i) + "111" + edges[1].getValue().charAt(i);
			}
			rep[i] = rep[i].replace("1", "X");
			rep[i] = rep[i].replace("0", " ");
			System.out.println(rep[i]);
		}
	}

	public void drawLine(int i) {
		String[] rep = new String[1];
		if (i == 0) {
			rep[i] = edges[i].getValue();
		} else if (i == 4) {
			rep[i] = edges[2].getValue();

		} else {
			rep[i] = edges[3].getValue().charAt(i) + "111" + edges[1].getValue().charAt(i);
		}
		rep[i] = rep[i].replace("1", "X");
		rep[i] = rep[i].replace("0", " ");
		System.out.print(rep[i]);
	}

	@Override
	public String toString() {
		return "Piece [edges=" + Arrays.toString(edges) + ", name=" + name + "]";
	}

}
