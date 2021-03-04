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

import com.revature.dao.UserDAO;
import com.revature.exceptions.LoginException;
import com.revature.exceptions.UserNotFoundException;

import com.revature.model.Role;
import com.revature.model.User;
import com.revature.services.UserService;
import com.revature.util.ConnectionUtil;

public class UserServiceTest {

	public UserService userService;	
	public static UserDAO userDAO;
	
	public static int id = 1;	//	Customer
	public static String name = "Customer";
	public static Role role = new Role(id, name);
	
	
	public static Connection mockConnection;
	public static MockedStatic<ConnectionUtil> mockedStatic;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Create fake objects (mock objects)
		userDAO = mock(UserDAO.class);		
		mockConnection = mock(Connection.class);		
		
		
//		mockedStatic = Mockito.mockStatic(ConnectionUtil.class);
//		mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
		
		// Instruct the methods from these fake objects on what to return when certain parameters are passed into them
		// utilize matchers from org.mockito.ArgumentMatchers
		when(userDAO.getUserByUsername(eq("janedoe"), 
				eq(mockConnection))).thenReturn(new User("janedoe", "Jane", "Doe", "jd123", role));				
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
//		mockedStatic.close();
	}

	@Before
	public void setUp() throws Exception {
		// Inject the fake objects into the actual UserService object being created
		userService = new UserService(userDAO);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// Actually test those methods
		@Test
		public void testValidUserName() throws UserNotFoundException, SQLException {
			// As of 3.4.0 Mockito, we can actually mock static methods as well! No longer just objects!
			try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
			
				mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
				
				User actual = userService.getUserByUsername("janedoe");
				
				User expected = new User("janedoe", "Jane", "Doe", "jd123", role);				
				
				assertEquals(expected, actual);			
			}
		}
		
		@Test
		public void TestInsertNewCustomerAccount() throws SQLException {
			try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
				
				mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
				
				User actual = userService.insertUserAsCustomer("tomjone1", "Tom", "Jones", "tj123");
				
				User expected = new User("tomjone1", "Tom", "Jones", "tj123", role);
				
				assertEquals(expected, actual);
			}
		}
		
		@Test(expected = UserNotFoundException.class)
		public void testInvalidUserName() throws UserNotFoundException, SQLException {
			try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
				mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
				
				User actual = userService.getUserByUsername("janedow");
			}
			
		}		
		
		
		@Test
		public void TestValidUserNameAndPassword() throws LoginException, SQLException {
			try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
				
				mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
				
				User actual = userService.login("janedoe", "jd123");
				
				User expected = new User("janedoe", "Jane", "Doe", "jd123", role);				
				
				assertEquals(expected, actual);
			
			}
			
			
		}
		
		
		@Test(expected = LoginException .class)
		public void TestInvalidUserNameLogin() throws LoginException, SQLException {
			try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
				mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
				
				User actual = userService.login("janedow", "jd123");
			}
		}	
		
		@Test(expected = LoginException.class)
		public void TestInvalidPasswordLogin() throws LoginException, SQLException {
			try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
				mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
				
				User actual = userService.login("janedoe", "jd1234");
			}
		}		
		
		
		
}
