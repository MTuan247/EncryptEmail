package security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
	public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
	    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
	    keyGenerator.init(n);
	    SecretKey key = keyGenerator.generateKey();
	    return key;
	}
	
	public static SecretKey recoverSecretKey(byte[] bytes) throws NoSuchAlgorithmException {
	    return new SecretKeySpec(bytes, "AES");
	}
	
	public static IvParameterSpec generateIvParameterSpec(int n) {
	    byte[] iv = new byte[n/8];
	    new SecureRandom().nextBytes(iv);
	    return new IvParameterSpec(iv);
	}
	
	public static byte[] generateIv(int n) {
	    byte[] iv = new byte[n/8];
	    new SecureRandom().nextBytes(iv);
	    return iv;
	}
	
	public static String encrypt(String input, SecretKey key, IvParameterSpec iv) 
		throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
		InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		    
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    cipher.init(Cipher.ENCRYPT_MODE, key, iv);
	    byte[] cipherText = cipher.doFinal(input.getBytes());
	    return Base64.getEncoder().encodeToString(cipherText);
	}
	
	public static String decrypt(String cipherText, SecretKey key, IvParameterSpec iv) 
		throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
		InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
		    
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    cipher.init(Cipher.DECRYPT_MODE, key, iv);
	    byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
	    return new String(plainText, "UTF-8");
	}
	
	public static byte[] encrypt(byte[] inputBytes, SecretKey key, IvParameterSpec iv) throws Exception {
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    cipher.init(Cipher.ENCRYPT_MODE, key, iv);
	    byte[] cipherTextBytes = cipher.doFinal(inputBytes);
	    return cipherTextBytes;
	}
	
	public static byte[] decrypt(byte[] cipherTextBytes, SecretKey key, IvParameterSpec iv) throws Exception {    
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    cipher.init(Cipher.DECRYPT_MODE, key, iv);
	    byte[] plainTextBytes = cipher.doFinal(cipherTextBytes);
	    return plainTextBytes;
	}
}
