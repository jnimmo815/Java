package com.revature.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.model.AccountApp;

public interface AccountAppDAO {

	public AccountApp insertAccountApp(String username, AccountApp accountApp, Connection con)
	throws SQLException;
	
}
