package contact_us;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

interface UserDao {
	boolean addContactUsMessages(String name,String email,String message);
	boolean getAdminLogin(String username,String Password);

}
public class PostgresqlDao implements UserDao{
	
	  private String url="jdbc:postgresql://localhost:5432/user_info";
	  private String username="surendra_postgres";
	  private String password="password";

	@Override
	public boolean addContactUsMessages(String name, String email, String message) {
		Connection connection=null;
		PreparedStatement statement=null;
		int updateQuery=0;
		
		String contactUsMessages= "INSERT INTO contact_us(name,email,message) values(?,?,?)";

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url,username,password);   
			statement = connection.prepareStatement(contactUsMessages);
			
			statement.setString(1, name);
			statement.setString(2, email);
			statement.setString(3, message);
			
			updateQuery = statement.executeUpdate();
			
			connection.close();
			statement.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return updateQuery>0;		
	}


	@Override
	public boolean getAdminLogin(String adminUsername, String adminPassword) {
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		boolean result=false;
		
		String fetchAdmin="SELECT username,password FROM admin WHERE username=? AND password=?";
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url,username,password);   
			statement = connection.prepareStatement(fetchAdmin);
			   
		    statement.setString(1,adminUsername);
			statement.setString(2,adminPassword);
			   
			resultSet = statement.executeQuery();
			result=resultSet.next();
			
			connection.close();
			statement.close();
			resultSet.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
