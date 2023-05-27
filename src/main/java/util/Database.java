package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/contactus_db";
	private static final String USERNAME = "surendra_postgres";
	private static final String PASSWORD = "password";

	public static Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
