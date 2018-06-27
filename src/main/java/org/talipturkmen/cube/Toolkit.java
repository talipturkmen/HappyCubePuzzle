package org.talipturkmen.cube;

public class Toolkit {

	/**
	 * Converts given decimal number into 5 fixed lenght binary
	 * 
	 * @param num
	 * @return
	 */
	public static String decimalToBinary(int num) {

		StringBuilder output = new StringBuilder(Integer.toBinaryString(num));

		while (output.length() < 5) {
			output.insert(0, "0");
		}

		return output.toString();
	}

	/**
	 * Converts binary to decimal
	 * 
	 * @param num
	 * @return
	 */
	public static int binaryToDecimal(String num) {
		return Integer.parseInt(num, 2);
	}

	/**
	 * Reverse bit order of binary number
	 * 
	 * @param num
	 * @return
	 */
	public static String reverseBinary(String num) {
		StringBuilder binaryNum = new StringBuilder(num);
		return binaryNum.reverse().toString();
	}
}
