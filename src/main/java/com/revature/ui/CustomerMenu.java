package com.revature.ui;

import java.sql.SQLException;
import java.util.List;

import com.revature.exceptions.UserNotFoundException;
import com.revature.model.CustomerAccount;
import com.revature.model.User;
import com.revature.services.CustomerAccService;
import com.revature.services.UserService;

public class CustomerMenu implements Menu {

	private User user;
	public UserService userService;
	public CustomerAccService customerAccService;

	public CustomerMenu(User user) {
		super();
		this.user = user;
		this.customerAccService = new CustomerAccService();
	}

	@Override
	public void display() {
		String username = user.getUsername();

		System.out.println("Welcome to the Customer Portal " + user.getFirstName() + " " + user.getLastName());

		int choice = 0;
		do {
			System.out.println("=== CUSTOMER MENU ===");
			System.out.println("Please select an option below: ");
			System.out.println("1. Back");
			System.out.println("2. Apply for an account");
			System.out.println("3. View all of my accounts");
			System.out.println("4. Make a withdraw from an account");
			System.out.println("5. Make a deposit from an account");

			try {
				choice = Integer.parseInt(Menu.sc.nextLine());
			} catch (NumberFormatException e) {
			}

			switch (choice) {
			case 1:
				break;
			case 2:
				AccountAppMenu accoutApplicationMenu = new AccountAppMenu(user);
				accoutApplicationMenu.display();
				break;
			case 3:
				try {
					List<CustomerAccount> customerAccs = customerAccService.getCustomerAccByUserName(username);
					customerAccs.stream().forEach(elm -> System.out.println(elm));						
				} catch (SQLException e) {
					System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
				} catch (UserNotFoundException e) {
					System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
				}
				
				break;
			case 4:
				makeWithdraw(username);
				break;
			case 5:
				makeDeposit(username);
				break;
			default:
				System.out.println("No valid choice entered, please try again");
			}

		} while (choice != 1);

	}

	private int getAccountNum() throws NumberFormatException {
		System.out.println("Enter an account number:");
		int input = Integer.parseInt(Menu.sc.nextLine());		
		return input;
		}

	private double getAmountW() {
		System.out.println("Enter the amount that you want to withdraw from your account: ");
		double input = Double.parseDouble(Menu.sc.nextLine());
		if (input < 0) {
			System.out.println("The amount that you enter has to be greater than zero!");
			System.out.println("The trransaction is rejected!");
		}
		return input;
	}

	private double getAmountD() {
		System.out.println("Enter the amount that you want to deposite into your account: ");
		double input = Double.parseDouble(Menu.sc.nextLine());
		if (input < 0) {
			System.out.println("The amount that you enter has to be greater than zero!");
			System.out.println("The trransaction is rejected!");
		}
		return input;
	}

	private void viewAccount(String username) {
		int customerAccountNum = getAccountNum();
		try {
			CustomerAccount customerAcc = customerAccService.getCustomerAccountByNum(customerAccountNum, username);
			if (customerAcc == null) {
				System.out.println("The account number that you entered doesn exit for: " + "username = " + username);
			} else {
				System.out.println("Customer account information:");
				System.out.println("number = " + customerAcc.getAccountNumber() + "     type = "
						+ customerAcc.getAccountTypeName() + "     balance = " + customerAcc.getAccountBalance()
						+ "     status = " + customerAcc.getStatusName());
			}

		} catch (SQLException e) {
			System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
		}
	}

	private void makeWithdraw(String username) {
		try {
		int customerAccountNum1 = getAccountNum();
		try {
			CustomerAccount customerAcc = customerAccService.getCustomerAccountByNum(customerAccountNum1, username);

			if (customerAcc == null) {
				System.out.println("The account number that you entered doesn exit for: " + "username = " + username);
			} else {
				if (customerAcc.getStatusName().equals("accepted")) {
					double customerAccBalance = customerAccService.getCustomerAccBalanceByNum(customerAccountNum1,
							username);

					double myAmount = getAmountW();
					if (myAmount > customerAccBalance) {
						System.out.println("The amount you entered is more than is in your account!");
						System.out.println("The trransaction is rejected!");
					} else {
					double newBalance = customerAccBalance - myAmount;
					customerAccService.updateCustomerAccountWNum(customerAccountNum1, newBalance);
					double customerAccBalance1 = customerAccService.getCustomerAccBalanceByNum(customerAccountNum1,
							username);
					System.out.println("The withdraw was successful!");
					System.out.println("Your new account balance is:  " + customerAccBalance1);
					}
				} else {
					System.out.println(
							"You can not make a withdraw from this account since it" + " has not been accepted yet!");
				}

			}

		} catch (SQLException e) {
			System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
		}
		} catch (NumberFormatException e) {
			System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
		}
		
	}

	public void makeDeposit(String username) {
		try {
		int customerAccountNum2 = getAccountNum();
		try {
			CustomerAccount customerAcc = customerAccService.getCustomerAccountByNum(customerAccountNum2, username);
			if (customerAcc == null) {
				System.out.println("The account number that you entered doesn exit for: " + "username = " + username);
			} else {
				if (customerAcc.getStatusName().equals("accepted")) {
					double customerAccBalance = customerAccService.getCustomerAccBalanceByNum(customerAccountNum2,
							username);

					double myAmount = getAmountD();
					double newBalance = customerAccBalance + myAmount;
					customerAccService.updateCustomerAccountWNum(customerAccountNum2, newBalance);
					double customerAccBalance1 = customerAccService.getCustomerAccBalanceByNum(customerAccountNum2,
							username);
					System.out.println("The deposite was successful!");
					System.out.println("Your new account balance is:  " + customerAccBalance1);
				} else {
					System.out.println(
							"You can not make a deposit to this account since it" + " has not been accepted yet!");
				}
			}

		} catch (SQLException e) {
			System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
		}
		} catch (NumberFormatException e) {
			System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
		}
	}

}
