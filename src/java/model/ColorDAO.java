package model;

import entity.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColorDAO extends Connector {

	public List<Color> getAllColors() {
		List<Color> colors = new ArrayList<>();
		String sql = "SELECT * FROM Color";
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int colorId = resultSet.getInt("colorId");
				String colorName = resultSet.getNString("colorName");
				String hex = resultSet.getNString("hex");
				Color color = new Color(colorId, colorName, hex);
				colors.add(color);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return colors;
	}

	public Color getColorById(int colorId) {
		Color color = null;
		String sql = "SELECT * FROM Color WHERE colorId = ?";
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			preparedStatement.setInt(1, colorId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String colorName = resultSet.getNString("colorName");
				String hex = resultSet.getNString("hex");
				color = new Color(colorId, colorName, hex);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return color;
	}
}
