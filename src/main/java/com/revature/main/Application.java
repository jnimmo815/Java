package com.revature.main;

import com.revature.ui.MainMenu;
import com.revature.ui.Menu;

public class Application {

	public static void main(String[] args) {
		
		Menu mainMenu = new MainMenu(); // shortcut for importing is ctrl + shift + o
		mainMenu.display();
		
		// This is the end of the application
		// So we can close our scanner
		Menu.sc.close();
		System.out.println("Application closing!");
	}

}
