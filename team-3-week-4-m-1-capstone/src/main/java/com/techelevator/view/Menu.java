package com.techelevator.view;

import com.techelevator.*;
import com.techelevator.items.CandyStoreItem;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/*
 * This is the only class that should have any usage of System.out or System.in
 *
 * Usage of System.in or System.out should not appear ANYWHERE else in your code outside of this class.
 *
 * Work to get input from the user or display output to the user should be done in this class, however, it
 * should include no "work" that is the job of the candy store.
 */
public class Menu {
	
	private static final Scanner in = new Scanner(System.in);
	private double currentBalance = 0;
	private double itemPriceTimesQuantity = 0;
	String  enteredId = "";
	String enteredQuantity = "";
	double runningTotal = 0;

	public void showWelcomeMessage() {
		System.out.println("***************************");
		System.out.println("**     Silver Shamrock   **");
		System.out.println("**      Candy Company    **");
		System.out.println("***************************");
		System.out.println();
		System.out.println();
	}
	public String printMainMenu() {
		System.out.println("Please Select an Option: ");
		System.out.println("(1) Show Inventory");
		System.out.println("(2) Make Sale");
		System.out.println("(3) Quit");
		String userChoice = in.nextLine();
		System.out.println(userChoice);
		return userChoice;
	}
	public File userFile() {
		System.out.println("What file do you want us to use");
		String fileName = in.nextLine();
		File file = null;
		file = new File(fileName);
		return file;
	}

	public String option2SubMenu(double currentBalance) {
		System.out.println("Please Select an Option: ");
		System.out.println("(1) Take Money");
		System.out.println("(2) Select Products");
		System.out.println("(3) Complete Sale");
		System.out.printf("CURRENT BALANCE: $%.2f%n", currentBalance);
		String userChoice = in.nextLine();
		return userChoice;
	}
	public double addBalance(double amountToAdd) {
		//double amountToAdd =askAddToCurrentBalance(currentBalance);
		currentBalance += amountToAdd;
		if (currentBalance > 1000){
			//undo math, and return if 100 is exceeded
			currentBalance =  currentBalance - amountToAdd;
			System.out.println("Current balance cannot exceed $1000\n");
			return currentBalance;
		}
		return currentBalance;
	}
	public double askAddToCurrentBalance(double currentBalance) throws InvalidAmountException {
		System.out.println("How much would you like to add? (MAX $100)");
		System.out.println("Whole numbers only");
		String userChoice = in.nextLine();
		double amountToAdd = Double.parseDouble(userChoice);
		if (amountToAdd > 100 || amountToAdd % 1 != 0){
			System.out.println("Invalid amount entered.\n" );
			throw new InvalidAmountException();
		}
		return amountToAdd;
	}
	public void displayInventory(Map<String, CandyStoreItem> inventory){
		System.out.println();

		System.out.printf("%-10s %-20s %-12s %-8s %-10s%n", "Id", "Name", "Wrapper", "Qty", "Price");


		for (Map.Entry<String, CandyStoreItem> currentEntry : inventory.entrySet()){

			String id = currentEntry.getValue().getCandyId();
			String name =currentEntry.getValue().getCandyName();
			String wrapper =currentEntry.getValue().getCandyWrapper();

			double quantity = currentEntry.getValue().getQuantity();
			String stringQuantity = "SOLD OUT";
			double price = currentEntry.getValue().getCandyPrice();

			if (quantity < 1){
				String formattedLine = String.format("%-10s %-20s %-12s %-8s $%.2f", id, name, wrapper, stringQuantity, price);
				System.out.println(formattedLine);
			} else {
				String formattedLine = String.format("%-10s %-20s %-12s %-8s $%.2f", id, name, wrapper, quantity, price);
				System.out.println(formattedLine);
			}
		}
		System.out.println();
	}


	public double checkInventoryId (Map<String, CandyStoreItem> inventory, double balance) throws InvalidIdException,InvalidAmountException {
		System.out.println("Please select a valid Candy ID: \n");
		 enteredId = in.nextLine();
		if (!inventory.containsKey(enteredId)) {
			System.out.println("Selection does not exist ");
			throw new InvalidIdException();
		}
		System.out.println("Please select a quantity: \n");
		enteredQuantity = in.nextLine();
		ShoppingCart shoppingCart = new ShoppingCart();

		shoppingCart.setNewQuantity(enteredId,enteredQuantity, inventory);

		double newBalanceIfItemAddedToCart = shoppingCart.checkPriceGivenQuantity(enteredId,enteredQuantity, inventory, balance);
		return newBalanceIfItemAddedToCart;
	}

	public void printReceipt(Map<String, CandyStoreItem> inventory) {
		ShoppingCart shoppingCart = new ShoppingCart();
		Map<String, ReceiptItems> receiptMap = new TreeMap<>();
		ReceiptItems item = new ReceiptItems("", "", "", "",
				"", "", "");

		ReceiptItems receiptLine = shoppingCart.receiptCreation( enteredId, inventory, enteredQuantity);
		receiptLine.toString();
		receiptMap.put (enteredId,receiptLine);

		System.out.println("Silver Shamrock Candy Company Receipt");
		System.out.println();

		for (Map.Entry<String, ReceiptItems> currentEntry : receiptMap.entrySet()){

			String quantity = currentEntry.getValue().getQuantity();
			String name = currentEntry.getValue().getCandyName();
			String companyName =currentEntry.getValue().getCompanyName();
			String individualPrice = currentEntry.getValue().getIndividualPrice();
			String totalPrice = currentEntry.getValue().getIndividualCandySum();
			double parsedIndividualPrice = Double.parseDouble(individualPrice);
			double parsedTotalPrice = Double.parseDouble(totalPrice);
			runningTotal += Double.parseDouble(totalPrice);
			//System.out.printf("%-5s %-10s %-10s $%-15.2sf $%-15.2sf", quantity, name, companyName, individualPrice, totalPrice);
			System.out.printf("%-5s %-15s %-25s $%-5.2f  $%-5.2f", quantity, name, companyName, parsedIndividualPrice, parsedTotalPrice);
			System.out.println();
		}

		double change = currentBalance - runningTotal;
		System.out.println();
		System.out.printf("Total: $%.2f",  runningTotal);
		System.out.println();
		System.out.printf("Change: $%.2f",  change);
		System.out.println();
		int twentyCounter = 0;
		int tensCounter = 0;
		int onesCounter = 0;
		int quarterCounter = 0;
		int dimeCounter = 0;
		int nickelCounter = 0;
		boolean changeCalculating = true;
		while(changeCalculating) {
			boolean calculatingTwentys = true;
			while (calculatingTwentys) {
				if (change - 20 >= 0) {
					change -= 20;
					twentyCounter++;
				} else {
					calculatingTwentys = false;
				}
			}
			boolean calculatingTens = true;
			while (calculatingTens) {
				if (change - 10 >= 0) {
					change -= 10;
					tensCounter++;
				} else {
					calculatingTens = false;
				}
			}
			boolean calculatingOnes = true;
			while (calculatingOnes) {
				if (change - 1 >= 0) {
					change -= 1;
					onesCounter++;
				} else {
					calculatingOnes = false;
				}
			}
			boolean calculatingQuarters = true;
			while (calculatingQuarters) {
				if (change - 0.25 >= 0) {
					change -= 0.25;
					quarterCounter++;
				} else {
					calculatingQuarters = false;
				}
			}
			boolean calculatingDimes = true;
			while (calculatingDimes) {
				if (change - 0.1 > 0) {
					change -= 0.1;
					dimeCounter++;
				} else {
					calculatingDimes = false;
				}
			}
			boolean calculatingNickel = true;
			while (calculatingNickel) {
				if (change - 0.05 > 0) {
					change -= 0.05;
					nickelCounter++;
				} else {
					calculatingNickel = false;
				}
			}
			changeCalculating = false;
		}
		System.out.println("(" + twentyCounter +")" + " Twenties, " + "(" + tensCounter +")" + " Tens, " +
				"(" + onesCounter +")" + " Ones, " + "(" + quarterCounter +")" + " Quarters, " + "(" + dimeCounter +")" +
				" Dimes, " + "(" + nickelCounter +")" + " Nickels\n");
	}


	public double getIetmPriceTimesQuantity() {
		return itemPriceTimesQuantity;
	}

	public void setItemPriceTimesQuantity(double itemPriceTimesQuantity) {
		this.itemPriceTimesQuantity = itemPriceTimesQuantity;
	}
}
