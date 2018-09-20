package com.bugfree.common;

import java.util.Random;

public class GenerateRandomString {

	public static String generateHexString(int requiredLength){

		Random randomService = new Random();
		StringBuilder sb = new StringBuilder();
		while (sb.length() < requiredLength) {
		    sb.append(Integer.toHexString(randomService.nextInt()));
		}
		sb.setLength(requiredLength);
		return sb.toString();
		
	}
}
