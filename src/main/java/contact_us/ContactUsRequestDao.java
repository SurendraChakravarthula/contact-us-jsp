package contact_us;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import admin.login.User;

public class ContactUsRequestDao implements RequestDao {
	private final String URL = "jdbc:postgresql://localhost:5432/contactus_db";
	private final String USERNAME = "surendra_postgres";
	private final String PASSWORD = "password";

	@Override
	public boolean saveRequest(Request request) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int update = 0;

		String contactUsSaveRequest = "INSERT INTO contactus_info(name,email,message) values(?,?,?)";

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			preparedStatement = connection.prepareStatement(contactUsSaveRequest);

			String name = request.getName();
			String email = request.getEmail();
			String message = request.getMessage();

			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, message);

			update = preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update > 0;
	}

	@Override
	public List<User> fetchAllRequests(boolean status) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> allRequests = new ArrayList<>();

		String getAllRequests = "SELECT id,name,email,message,time_stamp FROM contactus_info WHERE status=? ORDER BY id";

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			preparedStatement = connection.prepareStatement(getAllRequests);

			if (status)
				preparedStatement.setInt(1, 1);
			else
				preparedStatement.setInt(1, 0);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String message = resultSet.getString("message");
				String timestamp = resultSet.getString("time_stamp").substring(0, 19);

				User user = new User(id, name, email, message, timestamp);
				allRequests.add(user);
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allRequests;
	}

	@Override
	public boolean changeStatus(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int update = 0;

		String updateStatus = "UPDATE contactus_info SET status = CASE WHEN status = 1 THEN 0 ELSE 1 END WHERE id = ?";

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			preparedStatement = connection.prepareStatement(updateStatus);

			preparedStatement.setInt(1, id);

			update = preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update > 0;
	}

}
