package user.controller;

import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AccountDAO;

@WebServlet(name = "AccountLoginServlet", urlPatterns = {"/account/login"})
public class AccountLoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if (email.isEmpty() || password.isEmpty()) {
				throw new Exception("Email & Password can not be empty!");
			}
			AccountDAO accountDAO = new AccountDAO();
			Account account = accountDAO.login(email, password);
			if (account == null) {
				throw new Exception("Wrong email or password!");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("account", account);
				response.sendRedirect("/sneaker/account/info");
			}
		} catch (Exception exception) {
			String message = exception.getMessage();
			request.setAttribute("message", message);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
