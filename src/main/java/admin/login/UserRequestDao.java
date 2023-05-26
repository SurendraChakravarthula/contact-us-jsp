package admin.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRequestDao implements UserDao {

	private final String URL = "jdbc:postgresql://localhost:5432/contactus_db";
	private final String USERNAME = "surendra_postgres";
	private final String PASSWORD = "password";

	@Override
	public boolean isValidUser(String adminUsername, String adminPassword) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean checkAdmin = false;

		String fetchAdmin = "SELECT username,password FROM admin_login WHERE username=? AND password=?";

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			preparedStatement = connection.prepareStatement(fetchAdmin);

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
