package org.talipturkmen.cube;

/**
 * 
 * @author talip.turkmen
 * 
 *         Corner contains three bits
 */
public class Corner {
	private char x;
	private char y;
	private char z;

	public Corner(char x, char y, char z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public char getX() {
		return x;
	}

	public char getY() {
		return y;
	}

	public char getZ() {
		return z;
	}

}
