package user.controller;

import entity.Product;
import entity.ProductDetail;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.ProductDAO;
import model.ProductDetailDAO;

@WebServlet(name = "ProductDetailServlet", urlPatterns = {"/detail"})
public class ProductDetailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String productIdStr = request.getParameter("productId");
		int productId = Integer.parseInt(productIdStr);

		ProductDAO productDAO = new ProductDAO();
		Product product = productDAO.getProductyId(productId);
		request.setAttribute("product", product);

		ProductDetailDAO productDetailDAO = new ProductDetailDAO();
		List<ProductDetail> productDetails = productDetailDAO.getProductDetails(productId);
		request.setAttribute("productDetails", productDetails);

		request.getRequestDispatcher("/detail.jsp").forward(request, response);
	}
}
