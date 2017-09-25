package com.dsce.util;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class EncryptDecrypt {
	public static byte[] encryptData(String data, PublicKey pubKey)
			throws IOException {
		byte[] dataToEncrypt = data.getBytes();
		byte[] encryptedData = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			encryptedData = cipher.doFinal(dataToEncrypt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedData;
	}

	public static String decryptData(byte[] data, PrivateKey privateKey)
			throws IOException {
		byte[] descryptedData = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			descryptedData = cipher.doFinal(data);
			return new String(descryptedData);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
