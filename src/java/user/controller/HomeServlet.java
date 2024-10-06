package user.controller;

import entity.Brand;
import entity.Category;
import entity.LineUp;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.BrandDAO;
import model.CategoryDAO;
import model.LineUpDAO;

@WebServlet(name = "HomeServlet", urlPatterns = {"/"})
public class HomeServlet extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> categories = categoryDAO.getAllCategory();
		request.setAttribute("categories", categories);

		LineUpDAO lineUpDAO = new LineUpDAO();
		List<LineUp> lineUps = lineUpDAO.getAllLineUp();
		request.setAttribute("lineUps", lineUps);

		BrandDAO brandDAO = new BrandDAO();
		List<Brand> brands = brandDAO.getAllBrand();
		request.setAttribute("brands", brands);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}
