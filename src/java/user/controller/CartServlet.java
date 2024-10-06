package user.controller;

import entity.ProductDetail;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import model.ProductDetailDAO;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		Map<ProductDetail, Integer> carts = (Map<ProductDetail, Integer>) session.getAttribute("carts");
		if (carts == null) {
			carts = new HashMap<>();
		}

		double total = 0;
		for (ProductDetail productDetail : carts.keySet()) {
			total += productDetail.getProduct().getPrice() * carts.get(productDetail);
		}
		request.setAttribute("total", total);

		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String submit = request.getParameter("submit");

		switch (submit) {
			case "add" -> addCart(request, response);
			case "delete" -> deleteCart(request, response);
			case "update" -> updateCart(request, response);
		}
	}

	private void addCart(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		Map<ProductDetail, Integer> carts = (Map<ProductDetail, Integer>) session.getAttribute("carts");
		if (carts == null) carts = new HashMap<>();

		String productDetailIdStr = request.getParameter("productDetailId");
		int productDetailId = Integer.parseInt(productDetailIdStr);

		ProductDetailDAO productDetailDAO = new ProductDetailDAO();
		ProductDetail productDetail = productDetailDAO.getProductDetail(productDetailId);

		String quantityStr = request.getParameter("quantity");
		int quantity = Integer.parseInt(quantityStr);

		if (carts.containsKey(productDetail)) {
			int value = carts.get(productDetail) + quantity;
			carts.replace(productDetail, value);
		} else {
			carts.put(productDetail, quantity);
		}

		session.setAttribute("carts", carts);
		response.sendRedirect("/sneaker/detail?productId=" + productDetail.getProduct().getProductId());
	}

	private void deleteCart(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		Map<ProductDetail, Integer> carts = (Map<ProductDetail, Integer>) session.getAttribute("carts");

		String productDetailIdStr = request.getParameter("productDetailId");
		int productDetailId = Integer.parseInt(productDetailIdStr);

		for (ProductDetail productDetail : carts.keySet()) {
			if (productDetail.getProductDetailId() == productDetailId) {
				carts.remove(productDetail);
			}
		}

		session.setAttribute("carts", carts);
		response.sendRedirect("/sneaker/cart");
	}

	private void updateCart(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		Map<ProductDetail, Integer> carts = (Map<ProductDetail, Integer>) session.getAttribute("carts");

		String productDetailIdStr = request.getParameter("productDetailId");
		int productDetailId = Integer.parseInt(productDetailIdStr);

		String quantityStr = request.getParameter("quantity");
		int quantity = Integer.parseInt(quantityStr);

		for (ProductDetail productDetail : carts.keySet()) {
			if (productDetail.getProductDetailId() == productDetailId) {
				carts.replace(productDetail, quantity);
			}
		}

		session.setAttribute("carts", carts);
		response.sendRedirect("/sneaker/cart");
	}
}
