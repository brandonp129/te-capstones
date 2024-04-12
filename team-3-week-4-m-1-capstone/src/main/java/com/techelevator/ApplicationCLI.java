package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.items.CandyStoreItem;
import com.techelevator.view.Menu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
/*
 * This class should control the workflow of the application, but not do any other work
 * 
 * The menu class should communicate with the user, but do no other work
 * 
 * This class should control the logical workflow of the application, but it should do no other
 * work.  It should communicate with the user (System.in and System.out) using the Menu class and ask
 * the CandyStore class to do any work and pass the results between those 2 classes.
 */
public class ApplicationCLI {
	/*
	 * The menu class is instantiated in the main() method at the bottom of this file.  
	 * It is the only class instantiated in the starter code.  
	 * You will need to instantiate all other classes using the new keyword before you can use them.
	 * 
	 * Remember every class and data structure is a data types and can be passed as arguments to methods or constructors.
	 */
	private Menu menu;
	private Map<String, CandyStoreItem> productMap= new TreeMap<>();
	public ApplicationCLI() {
		menu = new Menu();
	}
	/*
	 * Your application starts here
	 */
	public void run() {
		CandyStore candyStore = new CandyStore();
		productMap = candyStore.getInventory();
		boolean isRunning = true;
		// gets current balance
		double balance = candyStore.getCurrentBalance();
		//gets amount to add
		double toAdd = 0;
		menu.showWelcomeMessage();
		while (isRunning) {
			/*
			Display the Starting Menu and get the users choice.
			Remember all uses of System.out and System.in should be in the menu

			IF the User Choice is Show Inventory,
				THEN show the candy store items for sale
			ELSE IF the User's Choice is Make Sale,
				THEN go to the make sale menu
			ELSE IF the User's Choice is Quit
				THEN break the loop so the application stops
			*/
			String choice = menu.printMainMenu();
			if (choice.equals("1")) {
				menu.displayInventory(productMap);
			} else if (choice.equals("2")) {
				boolean choice2IsRunning = true;
				while (choice2IsRunning) {
					String choice2 = menu.option2SubMenu(candyStore.getCurrentBalance());
					if (choice2.equals("1")) {
						try {
							toAdd = menu.askAddToCurrentBalance(balance);
							candyStore.setCurrentBalance(menu.addBalance(toAdd));
							balance = toAdd;
						} catch (InvalidAmountException e) {
							break; //back to main menu. holds current balance!
						}
					} else if (choice2.equals("2")) {
						menu.displayInventory(productMap);
						try {
							double newBalance = menu.checkInventoryId(productMap, balance);
							candyStore.setCurrentBalance(newBalance);
						} catch (InvalidIdException e) {
							//we know we shouldnt have an empty catch... but it's working so well, sorry
						} catch (InvalidAmountException e ){
							//we know we shouldnt have an empty catch... but it's working so well, sorry
						}
					} else if (choice2.equals("3")) {
						// create method in menu. create method here to call menu
						// method that was created.
						menu.printReceipt(productMap);
						choice2IsRunning = false;
					}
				}
			} else if (choice.equals("3")) {
				isRunning = false;
			} else {
			}
		}
	}
	/*
	 * This starts the application, but you shouldn't need to change it.  
	 */
	public static void main(String[] args) {
		ApplicationCLI cli = new ApplicationCLI();
		cli.run();
	}
}
