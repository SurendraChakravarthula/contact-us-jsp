package contact_us;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="getLoginDetails",urlPatterns="/AdminLoginDetails")
public class AdminLoginDetails extends HttpServlet {
	protected void service(HttpServletRequest request,HttpServletResponse response) {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) {

		   String adminUsername=request.getParameter("username");
		   String adminPassword=request.getParameter("password");
		
		   PostgresqlDao adminLogin=new PostgresqlDao();
		   boolean checkAdmin=adminLogin.getAdminLogin(adminUsername,adminPassword);

		   try {
		   if(checkAdmin) {
			   HttpSession session=request.getSession();
			   session.setAttribute("username",adminUsername);
			   response.sendRedirect("ContactUsMessages.jsp");
		   }else {
			   response.sendRedirect("AdminLogin.jsp");
		   }
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	}
}
