package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RequestDao;
import model.Request;

@WebServlet(name = "ContactUs", urlPatterns = "/contactus")
public class ContactUsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher("contactus.jsp");

		try {
			requestDispatcher.forward(httpRequest, httpResponse);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		String name = httpRequest.getParameter("name");
		String email = httpRequest.getParameter("email");
		String message = httpRequest.getParameter("message").trim();
		Request request = new Request();
		request.setName(name);
		request.setEmail(email);
		request.setMessage(message);
		RequestDao requestDao = new RequestDao();
		boolean saveRequest = requestDao.saveRequest(request);
		
		try {
			if (saveRequest) {
				httpResponse.getWriter().println(
						"<html><body><h1> Thanks for your Interest. We'll get back to you soon.</h1></body></html>");
			} else {
				httpResponse.getWriter()
						.println("<html><body><h1>Oops! something's wrong. Please try again.</h1></body></html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}