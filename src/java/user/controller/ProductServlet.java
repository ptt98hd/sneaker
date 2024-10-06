package user.controller;

import entity.Brand;
import entity.Category;
import entity.Color;
import entity.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.BrandDAO;
import model.CategoryDAO;
import model.ColorDAO;
import model.ProductDAO;

@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> categories = categoryDAO.getAllCategory();
		request.setAttribute("categories", categories);

		BrandDAO brandDAO = new BrandDAO();
		List<Brand> brands = brandDAO.getAllBrand();
		request.setAttribute("brands", brands);

		ColorDAO colorDAO = new ColorDAO();
		List<Color> colors = colorDAO.getAllColors();
		request.setAttribute("colors", colors);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);

		String productName = request.getParameter("productName");
		request.setAttribute("productName", productName);

		Map<String, Integer> filters = getFilters(request);

		String pageStr = request.getParameter("page");
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
		request.setAttribute("page", page);

		ProductDAO productDAO = new ProductDAO();
		List<Product> products = productDAO.getProducts(productName, filters, page);
		request.setAttribute("products", products);

		int pages = productDAO.getPages(productName, filters, page);
		request.setAttribute("pages", pages);

		request.getRequestDispatcher("/product.jsp").forward(request, response);
	}

	private Map<String, Integer> getFilters(HttpServletRequest request) {
		Map<String, Integer> filters = new HashMap<>();

		String categoryIdStr = request.getParameter("categoryId");
		int categoryId = categoryIdStr == null ? 0 : Integer.parseInt(categoryIdStr);
		filters.put("categoryId", categoryId);
		request.setAttribute("categoryId", categoryId);

		String brandIdStr = request.getParameter("brandId");
		int brandId = brandIdStr == null ? 0 : Integer.parseInt(brandIdStr);
		filters.put("brandId", brandId);
		request.setAttribute("brandId", brandId);

		String colorIdStr = request.getParameter("colorId");
		int colorId = colorIdStr == null ? 0 : Integer.parseInt(colorIdStr);
		filters.put("colorId", colorId);
		request.setAttribute("colorId", colorId);

		return filters;
	}
}
