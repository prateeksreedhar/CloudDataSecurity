import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

public class Test {
	public static void main(String[] args) throws Exception {
		KeyPairGenerator keyPairGenerator;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();

			KeyFactory kf = KeyFactory.getInstance("RSA");

			RSAPublicKeySpec rsaPubKeySpec = kf.getKeySpec(publicKey,
					RSAPublicKeySpec.class);
			BigInteger puMod = rsaPubKeySpec.getModulus();
			BigInteger puExp = rsaPubKeySpec.getPublicExponent();

			RSAPrivateKeySpec rsaPrivKeySpec = kf.getKeySpec(privateKey,
					RSAPrivateKeySpec.class);
			BigInteger prMod = rsaPrivKeySpec.getModulus();
			BigInteger prExp = rsaPrivKeySpec.getPrivateExponent();

			System.out.println("public key .. ");
			System.out.println(puMod);
			System.out.println(puExp);
			System.out.println("Private Key .. ");

			System.out.println(prMod);
			System.out.println(prExp);

			PublicKey pub = kf
					.generatePublic(new RSAPublicKeySpec(puMod, puExp));
			PrivateKey priv = kf.generatePrivate(new RSAPrivateKeySpec(prMod,
					prExp));

			String str = "Good Morning ! $ #@ ' ";
			System.out.println("Original text .. " + str);
			byte[] bb = encrypt(str, pub);
			String ss = new String(bb);

			System.out.println("\nEncrypted text \n " + ss);
			String strDec = decryptData(bb, priv);
			System.out.println("\n\nDecrypted text \n" + strDec);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] encrypt(String data, PublicKey pub) {
		byte[] dataToEncrypt = data.getBytes();
		byte[] encryptedData = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, pub);
			encryptedData = cipher.doFinal(dataToEncrypt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedData;
	}

	public static String decryptData(byte[] data, PrivateKey priv)
			throws IOException {
		byte[] descryptedData = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, priv);
			descryptedData = cipher.doFinal(data);
			return new String(descryptedData);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}