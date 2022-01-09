package com.phonepe.scoreboard.constants;

import java.lang.reflect.Field;

public interface BallPossibilities {

	String WICKET = "W";
	String WIDE = "Wd";
	String NO_BALL = "Nb";

	static boolean isValid(String ballUpdate) {
		Field[] fields = BallPossibilities.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				if (field.get(null).equals(ballUpdate)) {
					return true;
				}
			} catch (Exception e) {
				//TODO logging
			}
		}
		return false;
	}

}
