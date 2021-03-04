package com.revature.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.model.Role;
import com.revature.model.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public User getUserByUsername(String username, Connection con) throws SQLException {
		User user = null;
		
		String sql = "SELECT username, first_name, last_name, passwrd, id, name "
				+ "FROM users JOIN user_role ON role_id = user_role.id  WHERE username = ?";
						
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, username);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			String un = rs.getString("username");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String passwrd = rs.getString("passwrd");
			
			int id = rs.getInt("id");	
			String name = rs.getString("name");
			Role role = new Role(id, name);			
			
			user = new User(username, firstName, lastName, passwrd, role);
		}
		
		return user;		
	}

	@Override
	public User insertUserAsCustomer(String username, String firstName, String lastName, String passwrd, Connection con) throws SQLException {
		String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, username);
		pstmt.setString(2, firstName);
		pstmt.setString(3, lastName);
		pstmt.setString(4, passwrd);
		pstmt.setInt(5, 1);
		
		int count = pstmt.executeUpdate();			
		
		if (count != 1) {
			throw new SQLException("Did not successfully insert account informaton");
		}
		
		Role role = new Role(1, "Customer");
		
		User user = new User(username, firstName, lastName, passwrd, role);
		
		return user;
	}


	
}
