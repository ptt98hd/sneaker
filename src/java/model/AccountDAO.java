package model;

import entity.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO extends Connector {

//	READ
//	===============================================================================================
	public Account login(String email, String password) {
		Account account = null;
		String sql = """
               SELECT * FROM [Account]
               WHERE [email] = ? AND [password] = ?;""";
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			preparedStatement.setNString(1, email);
			preparedStatement.setNString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int accountId = resultSet.getInt("accountId");
				String fullname = resultSet.getNString("fullname");
				boolean admin = resultSet.getBoolean("admin");
				password = password.replaceAll(".", "* ");
				account = new Account(accountId, fullname, email, password, admin);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return account;
	}

//	CREATE
//	===============================================================================================
	public int createUser(Account account) {
		account.setAdmin(false);
		return createAccount(account);
	}

	public int createAdmin(Account account) {
		account.setAdmin(true);
		return createAccount(account);
	}

	private int createAccount(Account account) {
		int rowAffected = 0;
		String sql = """
               INSERT INTO [Account]
               ([fullname], [email], [password], [admin])
               VALUES (?, ?, ?, ?)""";
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			preparedStatement.setNString(1, account.getFullname());
			preparedStatement.setNString(2, account.getEmail());
			preparedStatement.setNString(3, account.getPassword());
			preparedStatement.setBoolean(4, account.isAdmin());
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}

//	DELETE
//	===============================================================================================
	public int deleteAccount(String email, String password) {
		int rowAffected = 0;
		String sql = """
               DELETE FROM [Account]
               WHERE [email] = ? AND [password] = ?""";
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(sql);
			preparedStatement.setNString(1, email);
			preparedStatement.setNString(2, password);
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}
}
