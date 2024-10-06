package model;

import entity.Brand;
import entity.Category;
import entity.LineUp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineUpDAO extends Connector {

	public List<LineUp> getAllLineUp() {
		List<LineUp> lineUps = new ArrayList<>();
		String sql = "SELECT * FROM LineUP";
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int lineUpId = resultSet.getInt("lineUpId");
				String lineUpName = resultSet.getNString("lineUpName");
				String lineUpImage = resultSet.getNString("lineUpImage");
				Brand brand = new BrandDAO().getBrandById(resultSet.getInt("brandId"));
				Category category = new CategoryDAO().getCategoryById(resultSet.getInt("categoryId"));
				LineUp lineUp = new LineUp(lineUpId, lineUpName, lineUpImage, brand, category);
				lineUps.add(lineUp);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return lineUps;
	}

	public LineUp getLineUpById(int lineUpId) {
		LineUp lineUp = null;
		String sql = "SELECT * FROM LineUP WHERE lineUpId = ?";
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			preparedStatement.setInt(1, lineUpId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String lineUpName = resultSet.getNString("lineUpName");
				String lineUpImage = resultSet.getNString("lineUpImage");
				Brand brand = new BrandDAO().getBrandById(resultSet.getInt("brandId"));
				Category category = new CategoryDAO().getCategoryById(resultSet.getInt("categoryId"));
				lineUp = new LineUp(lineUpId, lineUpName, lineUpImage, brand, category);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return lineUp;
	}
}
