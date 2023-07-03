package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Request;
import util.Database;

public class RequestDao {
	private Connection connection;

	public boolean saveRequest(Request request) {
		PreparedStatement preparedStatement = null;
		int update = 0;
		String contactUsSaveRequest = "INSERT INTO contactus_info(name,email,message) values(?,?,?)";
		
		try {
			connection = Database.getConnection();
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

	public List<Request> fetchAllRequests(boolean status) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Request> allRequests = new ArrayList<>();
		String getAllRequests = "SELECT id,name,email,message,time_stamp FROM contactus_info WHERE status=? ORDER BY id";

		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(getAllRequests);

			if (status) {
				preparedStatement.setInt(1, 1);
			} else {
				preparedStatement.setInt(1, 0);
			}
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String message = resultSet.getString("message");
				String timestamp = resultSet.getString("time_stamp");

				Request request = new Request();
				request.setId(id);
				request.setName(name);
				request.setEmail(email);
				request.setMessage(message);
				request.setTimestamp(timestamp);			
				allRequests.add(request);
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allRequests;
	}

	public boolean changeStatus(int id) {
		PreparedStatement preparedStatement = null;
		int update = 0;
		String updateStatus = "UPDATE contactus_info SET status = CASE WHEN status = 1 THEN 0 ELSE 1 END WHERE id = ?";

		try {
			connection = Database.getConnection();
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
