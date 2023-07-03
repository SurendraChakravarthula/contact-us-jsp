package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

@WebServlet(name = "adminLogin", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher("login.jsp");

		try {
			requestDispatcher.forward(httpRequest, httpResponse);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		String adminUsername = httpRequest.getParameter("username");
		String adminPassword = httpRequest.getParameter("password");
		User user = new User();
		user.setUsername(adminUsername);
		user.setPassword(adminPassword);		
		UserDao userDao = new UserDao();
		boolean checkAdmin = userDao.isValidUser(user);

		try {
			if (checkAdmin) {
				HttpSession session = httpRequest.getSession(true);
				session.setAttribute("username", adminUsername);
				httpResponse.sendRedirect("dashboard");
			} else {
				httpRequest.setAttribute("error", "Invalid Username or Password");
				RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher("/login.jsp");
				requestDispatcher.include(httpRequest, httpResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
