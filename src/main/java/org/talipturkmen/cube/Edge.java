package org.talipturkmen.cube;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author talip.turkmen
 * 
 *         Edge contains previous Edge,value and corner list
 */
public class Edge {

	private Edge pre;
	private String value;
	private List<Corner> corners = new ArrayList<Corner>();

	public Edge(int value) {
		this.value = Toolkit.decimalToBinary(value);
	}

	public Edge getPre() {
		return pre;
	}

	public void setPre(Edge pre) {
		this.pre = pre;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Corner> getCorners() {
		return corners;
	}

	public void addCorner(Corner corner) {
		corners.add(corner);
	}
}
