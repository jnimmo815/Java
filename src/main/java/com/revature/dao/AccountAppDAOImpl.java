package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.model.AccountApp;

public class AccountAppDAOImpl implements AccountAppDAO {

	@Override
	public AccountApp insertAccountApp(String username, AccountApp accountApp, Connection con) throws SQLException {
		String sql = "INSERT INTO application VALUES (default, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		pstmt.setString(1, username);
		pstmt.setInt(2, accountApp.getAccountTypeID());
		pstmt.setDouble(3, accountApp.getStartingBalance());
		pstmt.setDouble(4, accountApp.getSalary());
		pstmt.setInt(5, 1); 	// The value 1 corresponds to "pending"
		
		int count = pstmt.executeUpdate();
		
		if (count != 1) {
			throw new SQLException("Did not successfully insert Account Application " + accountApp);
		}
		
		// Retrieve auto generated ID, and set it on our Post object
		ResultSet rs = pstmt.getGeneratedKeys();		
		
		if (rs.next()) {
			int id = rs.getInt(1);
			accountApp.setId(id);
		}
		return accountApp;
		
	}			
	

}
