package contact_us;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

@WebServlet(name="saveData",urlPatterns="/saveData")
public class AddContactUs extends HttpServlet{
	
		protected void service(HttpServletRequest request,HttpServletResponse response) {
			doPost(request,response);
		}
		
		protected void doPost(HttpServletRequest request,HttpServletResponse response) {

			   String name=request.getParameter("name");
			   String email=request.getParameter("email");
			   String message=request.getParameter("message");
			   
			   PostgresqlDao contactUsDao=new PostgresqlDao();
			   boolean addedToContactUs=contactUsDao.addContactUsMessages(name,email,message);

			   try {
			   if(addedToContactUs) {
				   response.getWriter().println("<html><body><h1> Thanks for your Interest. We'll get back to you soon.</h1></body></html>");
			   }else {
				   response.sendRedirect("<html><body><h1>Oops! something's wrong. Please try again.</h1></body></html>");
			   }
			   }catch(Exception e) {
				   e.printStackTrace();
			   }

		}
}