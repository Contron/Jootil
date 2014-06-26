package com.connorhaigh.jootil.utilities;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Cryptography 
{
	/**
	 * Returns the secret key spec for the key.
	 * @param key the key
	 * @return the secret key spec
	 * @throws NoSuchAlgorithmException if the algorithm does not exist
	 */
	public static SecretKeySpec getSecretKeySpec(String key) throws NoSuchAlgorithmException
	{
		//digest and return
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(key.getBytes());
		SecretKeySpec secretKeySpec = new SecretKeySpec(messageDigest.digest(), 0, messageDigest.getDigestLength(), "AES");
		
		return secretKeySpec;
	}
	
	/**
	 * Returns a cipher for the specified key.
	 * @param mode the mode of the cipher
	 * @param secretKeySpec the secret key spec
	 * @return the cipher
	 * @throws NoSuchPaddingException if the padding does not exist
	 * @throws NoSuchAlgorithmException if the algorithm does not exist
	 * @throws InvalidKeyException if the key is invalid
	 */
	public static Cipher getCipherInstance(int mode, SecretKeySpec secretKeySpec) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException
	{
		//get instance
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(mode, secretKeySpec);
		
		return cipher;
	}
}
