package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;
import util.Database;

public class UserDao {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private boolean checkAdmin;
	
	public boolean isValidUser(User user) {
		String fetchAdmin = "SELECT username,password FROM admin_login WHERE username=? AND password=?";
		
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(fetchAdmin);
			String adminUsername = user.getUsername();
			String adminPassword = user.getPassword();			
			preparedStatement.setString(1, adminUsername);
			preparedStatement.setString(2, adminPassword);
			resultSet = preparedStatement.executeQuery();
			checkAdmin = resultSet.next();

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkAdmin;
	}
}
