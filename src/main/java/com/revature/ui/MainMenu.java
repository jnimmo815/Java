package com.revature.ui;

public class MainMenu implements Menu {

	@Override
	public void display() {
		System.out.println("Welcome to the Jim's bank application!");
		
int choice = 0;
		
		do {
			System.out.println("=== MAIN MENU ===");
			System.out.println("Please select an option below: ");
			System.out.println("1.) Exit Application");
			System.out.println("2.) User Menu");			
			
			try {
				choice = Integer.parseInt(Menu.sc.nextLine());
			} catch (NumberFormatException e) {
			}
			
			switch (choice) {
				case 1:
					break;
				case 2:
					Menu userMenu = new UserMenu();
					userMenu.display();
					break;				
				default:
					System.out.println("No valid choice entered, please try again");
			}
			
		} while(choice != 1);
	}

}
