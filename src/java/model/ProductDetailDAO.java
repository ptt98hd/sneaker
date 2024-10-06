package model;

import entity.Product;
import entity.ProductDetail;
import entity.Size;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailDAO extends Connector {

	public List<ProductDetail> getProductDetails(int productId) {
		List<ProductDetail> productDetails = new ArrayList<>();
		String sql = "SELECT * FROM ProductDetail WHERE productId = ? and stock > 0";
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int productDetailId = resultSet.getInt("productDetailId");
				Product product = new ProductDAO().getProductyId(productId);
				Size size = new SizeDAO().getSizeById(resultSet.getInt("sizeId"));
				int stock = resultSet.getInt("stock");
				ProductDetail productDetail = new ProductDetail(productDetailId, product, size, stock);
				productDetails.add(productDetail);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return productDetails;
	}

	public ProductDetail getProductDetail(int productDetailId) {
		ProductDetail productDetail = null;
		String sql = "SELECT * FROM ProductDetail WHERE productDetailId = ? and stock > 0";
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			preparedStatement.setInt(1, productDetailId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Product product = new ProductDAO().getProductyId(resultSet.getInt("productId"));
				Size size = new SizeDAO().getSizeById(resultSet.getInt("sizeId"));
				int stock = resultSet.getInt("stock");
				productDetail = new ProductDetail(productDetailId, product, size, stock);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return productDetail;
	}
}
