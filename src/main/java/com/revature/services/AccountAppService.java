package com.revature.services;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.dao.AccountAppDAO;
import com.revature.dao.AccountAppDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.exceptions.UserNotFoundException;
import com.revature.model.AccountApp;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class AccountAppService {
	
	public AccountAppDAO accountAppDAO;
	public UserDAO userDAO;
	
	public AccountAppService() {
		this.accountAppDAO = new AccountAppDAOImpl();
		this.userDAO = new UserDAOImpl();		
	}
	
	public AccountApp createAccountApp(String username, AccountApp accountApp)
	throws SQLException, UserNotFoundException {
		try (Connection con = ConnectionUtil.getConnection()) {
			con.setAutoCommit(false);
			
			User user = userDAO.getUserByUsername(username, con);
			
			if (user == null) {
				throw new UserNotFoundException("User with username '" + username + "' was not found!");
			}
			AccountApp accountApplicationWithID =  accountAppDAO.insertAccountApp(username, accountApp, con);
			con.commit();
			
			return accountApplicationWithID;
			
		}
		
	}

}
