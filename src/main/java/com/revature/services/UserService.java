package com.revature.services;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.exceptions.LoginException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.model.User;
import com.revature.ui.Menu;
import com.revature.util.ConnectionUtil;
import com.sun.tools.sjavac.Log;

public class UserService {
	
	private static Logger log = Logger.getLogger(UserService.class);
	
	public UserDAO userDAO; 	
	public UserService userService;
	
	public UserService() {
		this.userDAO = new UserDAOImpl();
		
	}
	
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public User getUserByUsername(String username) throws UserNotFoundException, SQLException {
		try (Connection con = ConnectionUtil.getConnection()) {
			User user;
			
			user = userDAO.getUserByUsername(username, con);
			
			if (user == null) {
				throw new UserNotFoundException("User with username '" + username + "' was not found!");
			}
			
			return user;
		}
	}
	
	public User insertUserAsCustomer(String username, String firstName, String lastName, String passwrd) throws SQLException {
		try (Connection con = ConnectionUtil.getConnection()) {
			User user;
			
			user = userDAO.insertUserAsCustomer(username, firstName, lastName, passwrd, con);
			
			
			return user;
		}
	}
	
	public User login(String username, String passwrd) throws LoginException, SQLException, NullPointerException {
				
		try (Connection con = ConnectionUtil.getConnection()) {
			User user;
			user = userDAO.getUserByUsername(username, con);	
			
			if (user == null) {
				log.info("getUserByUsername(): attempted to get User with username " 
						+ username + ", but this user was not found");
				throw new LoginException("Username does not exist!");				
			}
			if (passwrd.equals(user.getPasswrd())) { 
				System.out.println("Logged in successfully");
				return user;
			} else {
				
				throw new LoginException("Password was incorrect!");								
			}			
		}		
	}	
		
}
