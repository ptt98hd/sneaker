package model;

import entity.Size;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SizeDAO extends Connector {

	public Size getSizeById(int sizeId) {
		Size size = null;
		String sql = "SELECT * FROM Size WHERE sizeId = ?";
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			preparedStatement.setInt(1, sizeId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				short eu = resultSet.getShort("eu");
				size = new Size(sizeId, eu);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return size;
	}
}
