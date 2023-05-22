package contact_us;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "logout", urlPatterns = "/Logout")
public class LogOut extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		session.setAttribute("username", null);
		session.invalidate();
		try {
			response.sendRedirect("LoginPage.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
