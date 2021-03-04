package com.revature.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.dao.CustomerAccountDAO;
import com.revature.dao.CustomerAccountDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.exceptions.UserNotFoundException;
import com.revature.model.CustomerAccount;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class CustomerAccService {

	public CustomerAccountDAO customerAccountDAO;
	public CustomerAccService customerService;
	public UserDAO userDAO;

	public CustomerAccService() {
		this.customerAccountDAO = new CustomerAccountDAOImpl();
		this.userDAO = new UserDAOImpl();
	}
	
	public CustomerAccount insertCustomerAcc(int accountNumber, String username, int accountTypeID,
			double startingBalance) throws SQLException {
		try (Connection con = ConnectionUtil.getConnection()) {
			CustomerAccount customerAccount;
			customerAccount = 
					customerAccountDAO.insertCustomerAcc(accountNumber, username, accountTypeID, startingBalance, con);
			
			return customerAccount;
		}
		
	}

	public CustomerAccount getCustomerAccountByNum(int accountNumber, String username) throws SQLException {
		try (Connection con = ConnectionUtil.getConnection()) {
			CustomerAccount customerAccount;
			
			customerAccount = customerAccountDAO.getCustomerAccountByNum(accountNumber, username, con);
			
			return customerAccount;
		}
		
	}
	
	public double getCustomerAccBalanceByNum(int accountNumber, String username) throws SQLException {
		double customerAccBalance = 0.0;
		try (Connection con = ConnectionUtil.getConnection()) {
			customerAccBalance = customerAccountDAO.getCustomerAccBalanceByNum(accountNumber, username, con);
			
			return customerAccBalance;
		}		
		
	}
	
	public void updateCustomerAccountWNum(int accountNumber, double newBalance) throws SQLException {
		try (Connection con = ConnectionUtil.getConnection()) {
			customerAccountDAO.updateCustomerAccountWNum(accountNumber, newBalance, con);
		}
	}
	
	public List<CustomerAccount> getCustomerAccByUserName(String username) throws SQLException, UserNotFoundException {
		try (Connection con = ConnectionUtil.getConnection()) {

			User user = userDAO.getUserByUsername(username, con);

			if (user == null) {
				throw new UserNotFoundException("User with username '" + username + "' was not found!");
			}

			List<CustomerAccount> customerAccs = customerAccountDAO.getCustomerAccByUserName(username, con);

			return customerAccs;
		}
	}
		
			
}
