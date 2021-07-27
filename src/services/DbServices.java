package services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import db.ConnectionUtils;
import models.Account;
import security.RSAUtils;

public class DbServices {
	static Connection conn = ConnectionUtils.getConnection();
	
	public static Account findUser(Connection conn, String email) {

		String sql = "Select * from Account where email = ?";
		
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, email);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				String userID = rs.getString("id");
				String password = rs.getString("password");
				String applicationPassword = rs.getString("application_password");
				byte[] privateKey = rs.getBytes("private_key");
				
				Account user = new Account(userID, email, password, applicationPassword);
				user.setPrivateKey(privateKey);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Account saveKeyPair(Connection conn, String email) throws Exception {

		String sql = "update Account set public_key = ?, private_key = ? where email = ?";
		KeyPair kPair = RSAUtils.generateKeyPairRSA(2048);
		
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setBytes(1, kPair.getPublic().getEncoded());
			System.out.println(kPair.getPublic().getEncoded().length);
			pstm.setBytes(2, kPair.getPrivate().getEncoded());
			System.out.println(kPair.getPrivate().getEncoded().length);
			pstm.setString(3, email);
			pstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static byte[] getPublicKey(Connection conn, String email) throws Exception {

		String sql = "select public_key from Account where email = ?";
		
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, email);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				return rs.getBytes("public_key");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static byte[] getPrivateKey(Connection conn, String email) throws Exception {

		String sql = "select private_key from Account where email = ?";
		
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, email);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				return rs.getBytes("private_key");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
//	public static void main(String[] args) throws IOException {
//		String s1 = "Hello";
//		String s2 = " World";
//		
//		String s1Encoded = Base64.getEncoder().encodeToString(s1.getBytes("UTF-8"));
//		String s2Encoded = Base64.getEncoder().encodeToString(s2.getBytes("UTF-8"));
//
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		outputStream.write(s1.getBytes("UTF-8"));
//		outputStream.write(s2.getBytes("UTF-8"));
//		byte c[] = outputStream.toByteArray();
//		String enc = Base64.getEncoder().encodeToString(c);
//		
//		byte[] res = Base64.getDecoder().decode(enc);
//		System.out.println(new String(res, "UTF-8"));
//	}
	
	public static void main(String[] args) throws Exception {
		saveKeyPair(conn, "pthieu.mailtest@gmail.com");
		System.out.println("Done");
	}

}