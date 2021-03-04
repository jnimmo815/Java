package com.revature.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.model.CustomerAccount;

public interface CustomerAccountDAO {
	
	public List<CustomerAccount> getCustomerAccByUserName(String username, Connection con)throws SQLException;
	
	public List<CustomerAccount> getAllCustomerAccs(Connection con) throws SQLException;
	
	public CustomerAccount insertCustomerAcc(int accountNumber, String username, int accountTypeID,
			double startingBalance, Connection con) throws SQLException;
	
	public CustomerAccount getCustomerAccountByNum(int accountNumber, String username, Connection con)
			throws SQLException;
	
	public double getCustomerAccBalanceByNum(int accountNumber, String username, Connection con)
			throws SQLException;
	
	public void updateCustomerAccountWNum(int accountNumber, double newBalance, Connection con) throws SQLException;
}
