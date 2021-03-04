package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.CustomerAccount;

public class CustomerAccountDAOImpl implements CustomerAccountDAO {

	@Override
	public CustomerAccount getCustomerAccountByNum(int accountNumber, String username, Connection con)
			throws SQLException {
		CustomerAccount customerAccount = null;
		
		String sql = "SELECT account_number,username, type_name ,account_balance, status_name\r\n"
				+ "FROM customer_account JOIN account_type ON customer_account.account_type_id = account_type .id \r\n"
				+ "JOIN app_status ON customer_account.status_id = app_status.id \r\n"
				+ "WHERE username = ? AND  customer_account.account_number = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);		
		
		pstmt.setString(1, username);
		pstmt.setInt(2, accountNumber);	
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			int accountNum = rs.getInt("account_number");			
			String accountName = rs.getString("type_name");
			double accountBalance = rs.getDouble("account_balance");
			String statusName = rs.getString("status_name");
			
			customerAccount = new CustomerAccount(accountNum, accountName, accountBalance, statusName);
		}
		
		
		return customerAccount;
	}

	@Override
	public double getCustomerAccBalanceByNum(int accountNumber, String username, Connection con) throws SQLException {
		
		double accountBalance = 0.00;
		String sql = "SELECT account_balance FROM customer_account WHERE username = ? AND account_number = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);		
		
		pstmt.setString(1, username);
		pstmt.setInt(2, accountNumber);	
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			double accountBal = rs.getDouble("account_balance");
			
			accountBalance = accountBal;
		}
	
		return accountBalance;
	}

	@Override
	public void updateCustomerAccountWNum(int accountNumber, double newBalance, Connection con) throws SQLException {
		con.setAutoCommit(false);
		con.beginRequest();
		String sql = "UPDATE customer_account SET account_balance = ? WHERE account_number = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setDouble(1, newBalance);
		pstmt.setInt(2, accountNumber);
		
		int rowsAffected = pstmt.executeUpdate();
		
		if (rowsAffected > 0) {
			con.commit();
		} else {
			con.rollback();
		}
	}

	@Override
	public CustomerAccount insertCustomerAcc(int accountNumber, String username, int accountTypeID,
			double startingBalance, Connection con) throws SQLException {
		
		
		String sql = "INSERT INTO customer_account VALUES (?, ?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, accountNumber);
		pstmt.setString(2, username);
		pstmt.setInt(3, accountTypeID);
		pstmt.setDouble(4, startingBalance);
		pstmt.setInt(5, 1);
		
		int count = pstmt.executeUpdate();
		if (count != 1) {
			throw new SQLException("Did not successfully insert account informaton");
		}
		
		CustomerAccount customerAccount = new CustomerAccount(accountNumber, accountTypeID, startingBalance, 1 );
		return customerAccount;
	}

	@Override
	public List<CustomerAccount> getCustomerAccByUserName(String username, Connection con) throws SQLException {

		String sql = "SELECT account_number,username, type_name ,account_balance, status_name\r\n"
				+ "FROM customer_account JOIN account_type ON customer_account.account_type_id = account_type .id \r\n"
				+ "JOIN app_status ON customer_account.status_id = app_status.id \r\n"
				+ "WHERE username = ?";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, username);

		ResultSet rs = pstmt.executeQuery();
		
		List<CustomerAccount> customerAccs = new ArrayList<>();
		
		while (rs.next()) {
			int accountNumber = rs.getInt("account_number");
			String accountTypeName = rs.getString("type_name");
			double accountBalance = rs.getDouble("account_balance");
			String statusName = rs.getString("status_name");
			
			CustomerAccount customerAcc = new CustomerAccount(accountNumber, accountTypeName, accountBalance,statusName);
			customerAccs.add(customerAcc);
		}
		
		return customerAccs;
	}

	@Override
	public List<CustomerAccount> getAllCustomerAccs(Connection con) throws SQLException {
		String sql = "SELECT account_number,username, type_name ,account_balance, status_name\r\n"
				+ "FROM customer_account JOIN account_type ON customer_account.account_type_id = account_type .id \r\n"
				+ "JOIN app_status ON customer_account.status_id = app_status.id";
				

		PreparedStatement pstmt = con.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();
		
		List<CustomerAccount> customerAccs = new ArrayList<>();
		
		while (rs.next()) {
			int accountNumber = rs.getInt("account_number");
			String accountTypeName = rs.getString("type_name");
			double accountBalance = rs.getDouble("account_balance");
			String statusName = rs.getString("status_name");
			
			CustomerAccount customerAcc = new CustomerAccount(accountNumber, accountTypeName, accountBalance,statusName);
			customerAccs.add(customerAcc);
		}
		
		return customerAccs;

	}

}
