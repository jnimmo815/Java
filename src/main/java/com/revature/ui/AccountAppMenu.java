package com.revature.ui;

import java.sql.SQLException;

import com.revature.exceptions.UserNotFoundException;
import com.revature.model.AccountApp;
import com.revature.model.AccountType;
import com.revature.model.CustomerAccount;
import com.revature.model.Status;
import com.revature.model.User;
import com.revature.services.AccountAppService;
import com.revature.services.CustomerAccService;
import com.revature.services.UserService;

public class AccountAppMenu implements Menu {
	
	private User user;	
	public UserService userService;	
	public AccountAppService accountAppService;
	public CustomerAccService customerAccService;

	public AccountAppMenu(User user) {
		super();
		this.user = user;
		this.accountAppService = new AccountAppService();
		this.customerAccService = new CustomerAccService();
	}	

	@Override
	public void display() {
		String username = user.getUsername();		
		
		System.out.println("Welcome to the Application Portal " + user.getFirstName() + " " + user.getLastName());
		
		int choice = 0;
		do {
			System.out.println("=== ACCOUNT APPLICATION MENU ===");
			System.out.println("Please select an option below: ");
			System.out.println("1. Back");
			System.out.println("2. Apply for a checking account");
			System.out.println("3. Apply for a savings account");
			System.out.println("4. Apply for a money market account");
			
			try {
				choice = Integer.parseInt(Menu.sc.nextLine());
			} catch (NumberFormatException e) {
			}			
			
			switch (choice) {
			case 1: 
				break;
			case 2: 
						createCheckingAcc(username);						
				break;
			case 3:
						createSavingsAcc(username);						
				break;
			case 4:
						createMoneyMarketAcc(username);						
				break;
			default:
				System.out.println("No valid choice entered, please try again");
			} 
			
		} while (choice != 1);	
		
	}	
	private int generateAccountNum() {
		int AccountNumber =  Math.round((int) (10000*Math.random()));
		return AccountNumber;
	}
	
	private double getStaringBalance() {
		System.out.println("Enter starting balance: ");
		double input = Double.parseDouble(Menu.sc.nextLine()) ;
		
		return input;
	}
	
	private double getSalary() {
		System.out.println("Enter your salary: ");
		double input = Double.parseDouble(Menu.sc.nextLine()) ;
		return input;
	}	
	
	private int getChoice() {
		System.out.println("Please select an option below: ");
		System.out.println("1. Yes");
		System.out.println("2. No");
		int input = Integer.parseInt(Menu.sc.nextLine());
		return input;
	}
	
	private void createCheckingAcc(String username) {
		System.out.println("Confirm start of checking account application: ");
		int conChoice2 = getChoice();
		if (conChoice2 == 1) {
			double startingBalance = getStaringBalance();
			double salary = getSalary();
			int AccountNum = generateAccountNum();
			try {
				CustomerAccount customerAccount = 
						this.customerAccService.insertCustomerAcc(AccountNum, username, 1, startingBalance);
				System.out.println("The checking account with " + customerAccount.getAccountNumber());
				System.out.println("was created and is pending.");
			} catch (SQLException e) {
				System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());						
			}
			
			AccountApp accountApp = new AccountApp(0, 1, startingBalance, salary, 1);
			System.out.println(accountApp);
			
			try {
				accountApp = this.accountAppService.createAccountApp(username, accountApp);
				System.out.println("Account Application created successfully: " + accountApp);
			} catch (SQLException | UserNotFoundException e) {
				System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
			}
		}
	}
	
	private void createSavingsAcc(String username) {
		System.out.println("Confirm start of saving account application: ");
		int conChoic3 = getChoice();
		if (conChoic3 == 1) {
			double startingBalance = getStaringBalance();
			double salary = getSalary();
			int AccountNum = generateAccountNum();
			try {
				CustomerAccount customerAccount = 
						this.customerAccService.insertCustomerAcc(AccountNum, username, 2, startingBalance);
				System.out.println("The savings account with " + customerAccount.getAccountNumber());
				System.out.println("was created and is pending.");
			} catch (SQLException e) {
				System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());						
			}
			AccountApp accountApp = new AccountApp(0, 2, startingBalance, salary, 1);
			System.out.println(accountApp);
			try {
				accountApp = this.accountAppService.createAccountApp(username, accountApp);
				System.out.println("Account Application created successfully: " + accountApp);
			} catch (SQLException | UserNotFoundException e) {
				System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
			}
		}
	}
	
	private void createMoneyMarketAcc(String username) {
		System.out.println("Confirm start of money markert account application: ");
		int conChoic4 = getChoice();
		if (conChoic4 == 1) {
			double startingBalance = getStaringBalance();
			double salary = getSalary();
			int AccountNum = generateAccountNum();
			try {
				CustomerAccount customerAccount = 
						this.customerAccService.insertCustomerAcc(AccountNum, username, 3, startingBalance);
				System.out.println("The money market account with " + customerAccount.getAccountNumber());
				System.out.println("was created and is pending.");
			} catch (SQLException e) {
				System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());						
			}
			AccountApp accountApp = new AccountApp(0, 3, startingBalance, salary, 1);
			System.out.println(accountApp);
			try {
				accountApp = this.accountAppService.createAccountApp(username, accountApp);
				System.out.println("Account Application created successfully: " + accountApp);
			} catch (SQLException | UserNotFoundException e) {
				System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
			}
		}
	}

}
