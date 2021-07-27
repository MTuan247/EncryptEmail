package security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Utils {
	public static String FAILED_VERIFY = "Verify failed, not trusted!";
	public static String SUCCESS_VERIFY = "Verified!";
	public static String NO_SECURITY = "This mail don't use our encrypt and digital signature!";

	// Alice gửi thư cho Bob
	public static String makeSecurityMessage(String msg, byte[] pkBobBytes, byte[] skAliceBytes) throws Exception {
		byte[] plainTextBytes = msg.getBytes("UTF-8");
		PublicKey pkBob = RSAUtils.recoverPublicKey(pkBobBytes);
		PrivateKey skAlice = RSAUtils.recoverPrivateKey(skAliceBytes);

		// Ký
		byte[] signatureBytes = SignUtils.sign(plainTextBytes, skAlice);

		// AES
		SecretKey aesKey = AESUtils.generateKey(128);
		byte[] aesIv = AESUtils.generateIv(128);
		IvParameterSpec aesIvSpec = new IvParameterSpec(aesIv);
		byte[] cipherTextBytes = AESUtils.encrypt(plainTextBytes, aesKey, aesIvSpec);

		// RSA
		byte[] cipherKeyBytes = RSAUtils.encryptRSA(aesKey.getEncoded(), pkBob);

		// Ghép bytes
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		os.write(signatureBytes);
		os.write(cipherKeyBytes);
		os.write(aesIv);
		os.write(cipherTextBytes);
		byte[] res = os.toByteArray();

		return Base64.getEncoder().encodeToString(res);
	}

	// Bob nhận thư từ Alice
	public static List<String> readSecurityMessage(String securityMsg, byte[] pkAliceBytes, byte[] skBobBytes)
			throws Exception {
		List<String> result = new ArrayList<>();
		result.add(securityMsg);
		result.add(NO_SECURITY);
		
		try {
			// Chú ý trim()
			byte[] securityMsgBytes = Base64.getDecoder().decode(securityMsg.trim());
			InputStream in = new ByteArrayInputStream(securityMsgBytes);

			PublicKey pkAlice = RSAUtils.recoverPublicKey(pkAliceBytes);
			PrivateKey skBob = RSAUtils.recoverPrivateKey(skBobBytes);

			// Lấy chữ ký
			byte[] signatureBytes = new byte[256];
			in.read(signatureBytes);

			// Lấy khóa AES đã mã hóa
			byte[] cipherKeyBytes = new byte[256];
			in.read(cipherKeyBytes);
			// Giải mã RSA lấy khóa AES
			byte[] aesKeyBytes = RSAUtils.decryptRSA(cipherKeyBytes, skBob);
			SecretKey aesKey = AESUtils.recoverSecretKey(aesKeyBytes);

			// Lấy iv
			byte[] aesIv = new byte[128 / 8];
			in.read(aesIv);
			IvParameterSpec aesIvSpec = new IvParameterSpec(aesIv);

			// Lấy cipher text
			byte[] cipherTextBytes = in.readAllBytes();
			// Lấy plain text
			byte[] plainTextBytes = AESUtils.decrypt(cipherTextBytes, aesKey, aesIvSpec);
			String plainText =  new String(plainTextBytes, "UTF-8");

			// Xác thực chữ ký
			boolean verify = SignUtils.verify(plainTextBytes, signatureBytes, pkAlice);

			if (verify) {
				result.set(0, plainText);
				result.set(1, SUCCESS_VERIFY);
			} else {
				result.set(0, plainText);
				result.set(1, FAILED_VERIFY);
			}
		} catch (Exception e) {
			
		} 
		
		return result;
	}

	public static void main(String[] args) throws Exception {

		KeyPair pair = RSAUtils.generateKeyPairRSA(2048);
		String message = "the answer to life the universe and everything";
		System.out.println(pair.getPublic().getEncoded().length);
		System.out.println(pair.getPrivate().getEncoded().length);
		String cipherText = RSAUtils.encryptRSA(message, pair.getPublic());
		String decipheredMessage = RSAUtils.decryptRSA(cipherText, pair.getPrivate());

		System.out.println(decipheredMessage);

		String signature = SignUtils.sign("foobar", pair.getPrivate());

		boolean isCorrect = SignUtils.verify("foobar", signature, pair.getPublic());
		System.out.println("Signature correct: " + isCorrect);
	}
}
