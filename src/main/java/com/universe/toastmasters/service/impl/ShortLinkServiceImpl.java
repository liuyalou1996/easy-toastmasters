package com.universe.toastmasters.service.impl;

import com.google.common.hash.Hashing;
import com.universe.toastmasters.service.ShortLinkService;
import com.universe.toastmasters.util.SnowFlakeUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author Nick Liu
 * @date 2022/12/11
 */
@Service
public class ShortLinkServiceImpl implements ShortLinkService {

	private static final char[] TO_BASE_62 = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
	};

	private static final int SCALE = 62;

	/**
	 * Hash时的盐
	 */
	private static final String SALT = "cd3829bd6fe143228fcc9e1fb2bfaf76";

	public static String encodeToBase62String(long num) {
		StringBuilder sb = new StringBuilder();
		int remainder = 0;
		while (num > 0) {
			remainder = (int) (num % SCALE);
			sb.append(TO_BASE_62[remainder]);
			num /= SCALE;
		}
		sb.append(TO_BASE_62[(int) num]);
		return sb.reverse().toString();
	}

	public String generateShortLink(String longLink) {
		return null;
	}

	public static void main(String[] args) {
		long uniqueId = SnowFlakeUtils.nextId();
		System.out.println(String.valueOf(uniqueId).length());
		System.out.println(encodeToBase62String(uniqueId));
		System.out.println(Hashing.murmur3_128().hashString("123", StandardCharsets.UTF_8).toString().length());
	}
}
