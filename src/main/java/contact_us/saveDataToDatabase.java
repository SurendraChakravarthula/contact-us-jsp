package contact_us;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

@WebServlet(name="saveData",urlPatterns="/saveData")
public class saveDataToDatabase extends HttpServlet{
	
		protected void service(HttpServletRequest request,HttpServletResponse response) {
			doPost(request,response);
		}
		
		protected void doPost(HttpServletRequest request,HttpServletResponse response) {

			   String name=request.getParameter("name");
			   String email=request.getParameter("email");
			   String message=request.getParameter("message");
			
			   String url="jdbc:postgresql://localhost:5432/ipl_project";
			   String insertData="INSERT INTO contact_us(name,email,message) values(?,?,?)";
			   String username="surendra_postgres";
			   String password="password";
			  
			   try {
			   Class.forName("org.postgresql.Driver");
			   Connection connection=DriverManager.getConnection(url,username,password);   
			   PreparedStatement statement=connection.prepareStatement(insertData);
			   
			   statement.setString(1, name);
			   statement.setString(2, email);
			   statement.setString(3, message);
			   
			   int updateQuery = statement.executeUpdate();

			   if(updateQuery>0) {
				   response.getWriter().println("<html><body><h1> Thanks for your Interest.We'll contact you later </h1></body></html>");
			   }

			   connection.close();
			   statement.close();
			   }catch(Exception e) {
				   e.printStackTrace();
			   }
		}
}