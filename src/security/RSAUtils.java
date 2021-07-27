package security;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSAUtils {
	public static KeyPair generateKeyPairRSA(int n) throws Exception {
	    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
	    generator.initialize(n, new SecureRandom());
	    KeyPair pair = generator.generateKeyPair();

	    return pair;
	}
	
	public static PublicKey recoverPublicKey(byte[] bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory kf = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
		return kf.generatePublic(ks);
	}
	
	public static PrivateKey recoverPrivateKey(byte[] bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
		return kf.generatePrivate(ks);
	}
	
	public static String encryptRSA(String plainText, PublicKey publicKey) throws Exception {
	    Cipher encryptCipher = Cipher.getInstance("RSA");
	    encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
	    byte[] cipherText = encryptCipher.doFinal(plainText.getBytes("UTF-8"));
	    return Base64.getEncoder().encodeToString(cipherText);
	}
	
	public static String decryptRSA(String cipherText, PrivateKey privateKey) throws Exception {
	    byte[] bytes = Base64.getDecoder().decode(cipherText);

	    Cipher decriptCipher = Cipher.getInstance("RSA");
	    decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

	    return new String(decriptCipher.doFinal(bytes), "UTF-8");
	}
	
	public static byte[] encryptRSA(byte[] plainTextBytes, PublicKey publicKey) throws Exception {
	    Cipher encryptCipher = Cipher.getInstance("RSA");
	    encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
	    byte[] cipherTextBytes = encryptCipher.doFinal(plainTextBytes);
	    return cipherTextBytes;
	}
	
	public static byte[] decryptRSA(byte[] cipherTextBytes, PrivateKey privateKey) throws Exception {

	    Cipher decriptCipher = Cipher.getInstance("RSA");
	    decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

	    return decriptCipher.doFinal(cipherTextBytes);
	}
}
