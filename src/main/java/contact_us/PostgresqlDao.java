package contact_us;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

interface UserDao {
	boolean addContactUsMessages(String name,String email,String message);
	boolean getAdminLogin(String username,String Password);
	List<ContactUsPOJO> getActiveMessages();
	List<ContactUsPOJO> getArchiveMessages();

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


	@Override
	public List<ContactUsPOJO> getActiveMessages() {
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		List<ContactUsPOJO> activeMessagesData=new ArrayList<>();
			
		String activeMessages="SELECT * FROM contact_us";
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url,username,password);   
			statement = connection.prepareStatement(activeMessages);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				 int id=resultSet.getInt("id");
				 String name=resultSet.getString("name");
				 String email=resultSet.getString("email");
				 String message=resultSet.getString("message");
				 
				 ContactUsPOJO contactUsPOJO=new ContactUsPOJO(id,name,email,message);
				 activeMessagesData.add(contactUsPOJO);
			}
			
			connection.close();
			statement.close();
			resultSet.close();
		}catch(Exception e) {
			e.printStackTrace();
		}	
			return activeMessagesData;
	}
	
	public List<ContactUsPOJO> getArchiveMessages() {
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		List<ContactUsPOJO> archiveMessagesData=new ArrayList<>();
			
		String archiveMessages="SELECT * FROM archive";
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url,username,password);   
			statement = connection.prepareStatement(archiveMessages);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				 String name=resultSet.getString("name");
				 String email=resultSet.getString("email");
				 String message=resultSet.getString("message");
				 
				 ContactUsPOJO contactUsPOJO=new ContactUsPOJO(name,email,message);
				 archiveMessagesData.add(contactUsPOJO);
			}
			
			connection.close();
			statement.close();
			resultSet.close();
		}catch(Exception e) {
			e.printStackTrace();
		}	
			return archiveMessagesData;
	}
	
	
}
