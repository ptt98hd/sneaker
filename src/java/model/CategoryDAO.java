package model;

import entity.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends Connector {

	public List<Category> getAllCategory() {
		List<Category> categories = new ArrayList<>();
		String sql = "SELECT * FROM Category";
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int categoryId = resultSet.getInt("categoryId");
				String categoryName = resultSet.getNString("categoryName");
				String categoryImage = resultSet.getNString("categoryImage");
				Category category = new Category(categoryId, categoryName, categoryImage);
				categories.add(category);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return categories;
	}

	public Category getCategoryById(int categoryId) {
		Category category = null;
		String sql = "SELECT * FROM Category WHERE categoryId = ?";
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			preparedStatement.setInt(1, categoryId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String categoryName = resultSet.getNString("categoryName");
				String categoryImage = resultSet.getNString("categoryImage");
				category = new Category(categoryId, categoryName, categoryImage);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return category;
	}
}
