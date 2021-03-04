package com.revature.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.revature.dao.CustomerAccountDAO;
import com.revature.dao.UserDAO;
import com.revature.exceptions.LoginException;
import com.revature.exceptions.UserNotFoundException;

import com.revature.model.Role;
import com.revature.model.User;
import com.revature.services.CustomerAccService;
import com.revature.services.UserService;
import com.revature.util.ConnectionUtil;


public class CustomerAccountServiceTest {
	
	public CustomerAccService customerAccService;
	public static CustomerAccountDAO customerAccountDAO;
	
	public static Connection mockConnection;
	public static MockedStatic<ConnectionUtil> mockedStatic;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Create fake objects (mock objects)
		customerAccountDAO = mock(CustomerAccountDAO.class);
		mockConnection = mock(Connection.class);		
	}
	
	@Before
	public void setUp() throws Exception {
		// Inject the fake objects into the actual UserService object being created
		customerAccService = new CustomerAccService();
		
	}
	
	@Test
	public void myTest() {
		
	}
	

}
