package com.universe.toastmasters.util;

/**
 * @author Nick Liu
 * @date 2022/12/13
 */
public abstract class Base62Utils {

	private static final int SCALE = 62;

	private static final char[] BASE_62_ARRAY = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
	};

	public static String encodeToBase62String(long num) {
		StringBuilder sb = new StringBuilder();
		int remainder = 0;
		while (num > 0) {
			remainder = (int) (num % SCALE);
			sb.append(BASE_62_ARRAY[remainder]);
			num /= SCALE;
		}
		sb.append(BASE_62_ARRAY[(int) num]);
		return sb.reverse().toString();
	}

}
