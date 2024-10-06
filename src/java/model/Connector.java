package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	private Connection connection = null;

	public Connector() {
		final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		final String url = """
                     jdbc:sqlserver://localhost:1433;databaseName=Sneaker;
                     encrypt=false;trustServerCertificate=false;""";
		final String user = "sa";
		final String password = "Sh@wn98HD";
		setConnection(driver, url, user, password);
	}

	private void setConnection(String driver, String url, String user, String password) {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println(ex.getMessage());
		}
	}

	public final Connection connection() {
		return connection;
	}
}
