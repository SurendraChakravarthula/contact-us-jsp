package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RequestDao;
import model.Request;

@WebServlet(name = "AdminDashboard", urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet {
	private RequestDao requestDao = new RequestDao();

	protected void doGet(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		HttpSession session = httpRequest.getSession();

		if (session == null) {
			try {
				httpResponse.sendRedirect("login");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		List<Request> activeRequests = requestDao.fetchAllRequests(true);
		List<Request> archiveRequests = requestDao.fetchAllRequests(false);
		session.setAttribute("activeRequests", activeRequests);
		session.setAttribute("archiveRequests", archiveRequests);
		RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher("dashboard.jsp");

		try {
			requestDispatcher.forward(httpRequest, httpResponse);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		int id = Integer.parseInt(httpRequest.getParameter("id"));
		boolean saveRequest = requestDao.changeStatus(id);

		try {
			if (saveRequest) {
				httpResponse.sendRedirect("dashboard");
			} else {
				httpResponse.getWriter()
						.println("<html><body><h1>Oops! something's wrong. Please try again.</h1></body></html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}