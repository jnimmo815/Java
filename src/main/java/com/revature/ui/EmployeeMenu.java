package com.revature.ui;

import java.sql.SQLException;
import java.util.List;

import com.revature.exceptions.UserNotFoundException;
import com.revature.model.CustomerAccount;
import com.revature.model.User;
import com.revature.services.CustomerAccService;
import com.revature.services.UserService;

public class EmployeeMenu implements Menu {
	private User user;
	public UserService userService;
	public CustomerAccService customerAccService;
	
	public EmployeeMenu(User user) {
		super();
		this.user = user;
		this.customerAccService = new CustomerAccService();
	}
	
	@Override
	public void display() {
		String username = user.getUsername();
		
		System.out.println("Welcome to the Employee Portal " + user.getFirstName() + " " + user.getLastName());

		int choice = 0;
		do {
			System.out.println("=== Employee MENU ===");
			System.out.println("Please select an option below: ");
			System.out.println("1. Back");
			System.out.println("2. View a customer's bank accounts");
			
			try {
				choice = Integer.parseInt(Menu.sc.nextLine());
			} catch (NumberFormatException e) {
			}
			
			switch (choice) {
			case 1:
				break;
			case 2: 
				try {
					String customerUserName =getCustomerUserName();
					List<CustomerAccount> customerAccs = customerAccService.getCustomerAccByUserName(customerUserName);
					customerAccs.stream().forEach(elm -> System.out.println(elm));						
				} catch (SQLException e) {
					System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
				} catch (UserNotFoundException e) {
					System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
				}				
				break;
			default:
				System.out.println("No valid choice entered, please try again");
			}
		
		
		} while(choice != 1);		

	}
	
	public String getCustomerUserName() {
		System.out.println("Eneter a customer's username.");
		String input =  Menu.sc.nextLine();
		return input;
	}

}
