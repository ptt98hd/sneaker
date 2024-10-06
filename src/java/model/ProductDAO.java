package model;

import entity.Color;
import entity.LineUp;
import entity.Product;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDAO extends Connector {

	private final int pageSize = 12;

	public List<Product> getProducts(String name, Map<String, Integer> filters, int page) {
		List<Product> products = new ArrayList<>();
		String sql = "SELECT * FROM Product P JOIN LineUp L on P.lineUpId = L.lineUpId";
		sql = filterBuilder(sql, name, filters);
		sql += " ORDER BY productId OFFSET " + ((page - 1) * pageSize);
		sql += " ROWS FETCH NEXT " + pageSize + " ROWS ONLY";
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int productId = resultSet.getInt("productId");
				String productName = resultSet.getNString("productName");
				LineUp lineUp = new LineUpDAO().getLineUpById(resultSet.getInt("lineUpId"));
				Color color = new ColorDAO().getColorById(resultSet.getInt("colorId"));
				double price = resultSet.getDouble("price");
				Date date = resultSet.getDate("date");
				String productImage = resultSet.getNString("productImage");
				Product product = new Product(productId, productName, lineUp, color, price, date, productImage);
				products.add(product);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return products;
	}

	public Product getProductyId(int productId) {
		Product product = null;
		String sql = "SELECT * FROM Product WHERE productId = ?";
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String productName = resultSet.getNString("productName");
				LineUp lineUp = new LineUpDAO().getLineUpById(resultSet.getInt("lineUpId"));
				Color color = new ColorDAO().getColorById(resultSet.getInt("colorId"));
				double price = resultSet.getDouble("price");
				Date date = resultSet.getDate("date");
				String productImage = resultSet.getNString("productImage");
				product = new Product(productId, productName, lineUp, color, price, date, productImage);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return product;
	}

	public int getPages(String productName, Map<String, Integer> filters, int page) {
		int pages = 1;
		String sql = "SELECT COUNT(*) AS countProducts FROM Product P JOIN LineUp L on P.lineUpId = L.lineUpId";
		sql = filterBuilder(sql, productName, filters);
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int countProducts = resultSet.getInt("countProducts");
				pages = Math.ceilDiv(countProducts, pageSize);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return pages;
	}

	private String filterBuilder(String sql, String productName, Map<String, Integer> filters) {
		sql += " WHERE 1 = 1";
		sql += productName != null ? (" AND productName LIKE '%" + productName.toUpperCase() + "%'") : "";
		for (String key : filters.keySet()) {
			int value = filters.get(key);
			if (value != 0) {
				sql += " AND " + key + " = " + value;
			}
		}
		return sql;
	}
}
