package org.plarity.server.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

	public static String generatePassword() {
		return PwGen.generatePassword();
	}

	public static String md5Sum(byte[] data) {

		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(data);
			byte[] hash = digest.digest();

			// convert to hex string
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				result.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
			}

			return result.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String md5Sum(String data) {
		return md5Sum(data.getBytes());
	}

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			String pw = PwGen.generatePassword();
			String md5Hash = md5Sum(pw);
			System.out.println(pw + " -> " + md5Hash);
		}
	}

}
