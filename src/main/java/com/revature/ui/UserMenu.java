package com.revature.ui;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.exceptions.LoginException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.model.User;
import com.revature.services.UserService;
import com.sun.tools.sjavac.Log;

public class UserMenu implements Menu {
	
	public UserService userService;

	public UserMenu() {
		this.userService = new UserService();
	}

	@Override
	public void display() {

		int choice = 0;

		do {
			System.out.println("=== USER MENU ===");
			System.out.println("Please select an option below: ");
			System.out.println("1.) Back");
			System.out.println("2.) Get user by username");
			System.out.println("3.) Login");
			System.out.println("4.) Register as a Customer");

			try {
				choice = Integer.parseInt(Menu.sc.nextLine());
			} catch (NumberFormatException e) {
			}

			switch (choice) {
			case 1:
				break;
			case 2:
				String username = getUsernameInput();

				try {
					User user = userService.getUserByUsername(username);
					System.out.println(user);
				} catch (SQLException | UserNotFoundException e) {
					System.out.println(e.getClass().getSimpleName() + " " + e.getMessage());
				}
				break;
			case 3:
				try {
					getLogIn();					
				} catch (LoginException | SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				String newUserName = getNewUserName();
				String firstName = getFirstName();
				String lastName = getLastName();
				String passwrd = getPasswrd();
				//try {
				User user;
				try {
					user = userService.insertUserAsCustomer(newUserName, firstName, lastName, passwrd);
					System.out.println("You have been successfully established a customer account as follows: ");
					System.out.println(user);					
				} catch (SQLException e) {
					System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
				}					
				
				break;
			default:
				System.out.println("No valid choice entered, please try again");
			}

		} while (choice != 1);
	}
	
	private String getNewUserName() {
		System.out.println("Enter username: ");
		String input = Menu.sc.nextLine().trim();
		return input;
	}
	
	private String getFirstName() {
		System.out.println("Enter your first name: ");
		String input = Menu.sc.nextLine().trim();
		return input;
	}	
	
	private String getLastName() {
		System.out.println("Enter your last name: ");
		String input = Menu.sc.nextLine().trim();
		return input;
	} 
	
	private String getPasswrd() {
		System.out.println("Enter a password: ");
		String input = Menu.sc.nextLine().trim();
		return input;
	}

	private String getUsernameInput() {
		System.out.println("Enter a username that you would like to lookup: ");
		String input = Menu.sc.nextLine().trim();

		return input;
	}
	
	private void getLogIn() throws LoginException, SQLException {
		System.out.println("Enter a username: ");
		String username = Menu.sc.nextLine().trim();
		
		System.out.println("Enter password: ");
		String passwrd = Menu.sc.nextLine().trim();
		
		User loggedUser = userService.login(username, passwrd);		
		
		
		if (loggedUser.getRole().getId().equals(1)) {
			CustomerMenu customerMenu = new CustomerMenu(loggedUser);
			customerMenu.display();
		}
		EmployeeMenu employeeMenu = new EmployeeMenu(loggedUser);
		employeeMenu.display();
	}	
	
}


