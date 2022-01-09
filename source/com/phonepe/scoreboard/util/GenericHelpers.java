package com.phonepe.scoreboard.util;

public class GenericHelpers {

	public static void printHorizontalLine(int length) {
		for (int i = 0; i < length; i++) {
			System.out.print("-");
		}
	}

	public static String formatWidth(int player, int index) {
		String append = index == 1 ? "| ":"";
		return append + "%" + index + "$" + player + "s |";
	}

	public static String printableOver(int balls, int over) {
		return (balls == 0 ? ""+over:"" + (over - 1) + "." + (6 - balls));
	}

	public static String strikeRate(int balls, int score) {
		if (balls == 0) {
			return "NA";
		}
		return (int)(((double)score/balls)*100) + "";
	}
}
