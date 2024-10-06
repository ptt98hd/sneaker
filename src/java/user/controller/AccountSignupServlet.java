package user.controller;

import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AccountDAO;

@WebServlet(name = "AccountSignupServlet", urlPatterns = {"/account/signup"})
public class AccountSignupServlet extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.getRequestDispatcher("/signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			String fullname = request.getParameter("fullname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String rePassword = request.getParameter("rePassword");

			if (fullname.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
				throw new Exception("Please fill in all input fields!");
			}

			if (!password.equals(rePassword)) {
				throw new Exception("Password and rePassword must be the same!");
			}

			Account account = new Account(0, fullname, email, password, false);
			AccountDAO accountDAO = new AccountDAO();

			if (accountDAO.createUser(account) == 0) {
				throw new Exception("Email already exists!");
			}

			response.sendRedirect("/sneaker/account/login");
		} catch (Exception e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		}
	}
}
