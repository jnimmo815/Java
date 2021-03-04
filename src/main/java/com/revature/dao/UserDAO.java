package com.revature.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.model.Role;
import com.revature.model.User;

public interface UserDAO {

	User getUserByUsername(String username, Connection con) throws SQLException;
	
	User insertUserAsCustomer(String username,String firstName, String lastName, String passwrd, Connection con) throws SQLException;
		
}
