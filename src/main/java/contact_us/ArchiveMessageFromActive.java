package contact_us;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="archiveMessage",urlPatterns="/archiveFromActive")
public class ArchiveMessageFromActive extends HttpServlet {

	protected void service(HttpServletRequest request,HttpServletResponse response) {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) {
		 PostgresqlDao adminLogin=new PostgresqlDao();
		 
		 try {
		 int id=Integer.parseInt(request.getParameter("id"));
		 boolean archiveMessage=adminLogin.archiveMessage(id);
		 
		 if(archiveMessage) {
			 HttpSession session=request.getSession(false);
			 
			 List<ContactUsPOJO> activeMessages=adminLogin.getActiveMessages();
			 session.setAttribute("activeMessages", activeMessages);
			   
			 List<ContactUsPOJO> archiveMessages=adminLogin.getArchiveMessages();
			 session.setAttribute("archiveMessages", archiveMessages);
			   
			 response.sendRedirect("ContactUsMessages.jsp");
		 }else {
			 response.getWriter().println("<html><body><h1>Something Wrong! Please try Again</h1></body></html>");
		 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	}		
}
