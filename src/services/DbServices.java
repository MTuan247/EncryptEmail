package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConnectionUtils;
import models.Account;

public class DbServices {
	static Connection conn = ConnectionUtils.getConnection();
	
	public static Account findUser(Connection conn, String userName, String password) {

		String sql = "Select * from Account where email = ? and password= ?";
		
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, userName);
			pstm.setString(2, password);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				String userID = rs.getString("id");
				String applicationPassword = rs.getString("application_password");
				Account user = new Account(userID, userName, password, applicationPassword);
				
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}