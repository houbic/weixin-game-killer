package com.weixin.utils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 签名验证类
 *
 * @author bink
 * @since 1.0.0
 */
public class SignUtil {

	private static String token = "binkweixin";

	public static boolean checkSignature(String signature,String timestamp, String nonce) {

		String[] paramArr = new String[] { token, timestamp, nonce };

		Arrays.sort(paramArr);

		String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
		
		System.out.println(content);

		String ciphertext = null;

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(content.toString().getBytes());
			ciphertext = byteToStr(digest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        System.out.println(ciphertext);
		System.out.println(signature);

		return ciphertext != null ? ciphertext.equalsIgnoreCase(signature) : false;

	}

	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}
	
	private static String byteToHexStr(byte myByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(myByte >>> 4) & 0X0F];
		tempArr[1] = Digit[myByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}
	
	public static void main(String[] args) {
		while (true) {
			
		}
	}
}
