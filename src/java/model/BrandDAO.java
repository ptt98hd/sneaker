package model;

import entity.Brand;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO extends Connector {

	public List<Brand> getAllBrand() {
		List<Brand> brands = new ArrayList<>();
		String sql = "SELECT * FROM Brand";
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int brandId = resultSet.getInt("brandId");
				String brandName = resultSet.getNString("brandName");
				String brandImage = resultSet.getNString("brandImage");
				Brand brand = new Brand(brandId, brandName, brandImage);
				brands.add(brand);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return brands;
	}

	public Brand getBrandById(int brandId) {
		Brand brand = null;
		String sql = "SELECT * FROM Brand WHERE brandId = ?";
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			preparedStatement.setInt(1, brandId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String brandName = resultSet.getNString("brandName");
				String brandImage = resultSet.getNString("brandImage");
				brand = new Brand(brandId, brandName, brandImage);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return brand;
	}
}
