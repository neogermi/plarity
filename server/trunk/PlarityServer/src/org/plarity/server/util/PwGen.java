package org.plarity.server.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PwGen {

	public static final int PASSWORD_LENGTH = 17;

	public static final char[] LETTERS = new char[] { '!', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ':', '<',
			'=', '>', '?', '@', '[', '\\', ']', '^', '_', '-', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
			'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public static String generatePassword() {

		try {
			SecureRandom randomGenerator = SecureRandom.getInstance("SHA1PRNG");

			StringBuffer generatedPw = new StringBuffer();

			for (int i = 0; i < PASSWORD_LENGTH; i++) {
				int rand = randomGenerator.nextInt(LETTERS.length);
				generatedPw.append(LETTERS[rand]);
			}

			return generatedPw.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		for (int i = 0; i < PASSWORD_LENGTH; i++) {
			System.out.println(PwGen.generatePassword());
		}
	}

}
