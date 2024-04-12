package com.techelevator;

import com.techelevator.items.CandyStoreItem;
import com.techelevator.view.Menu;
import com.techelevator.view.ReceiptItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ShoppingCart extends Menu {

    private double parsedEnteredQuantity;

    public double setNewQuantity(String itemID,String enteredQuantity, Map<String, CandyStoreItem> inventory) throws InvalidAmountException{

        parsedEnteredQuantity = Integer.parseInt(enteredQuantity);
        double selectedItemAvailableQuantity = inventory.get(itemID).getQuantity();
        double remainingQuantity = selectedItemAvailableQuantity - parsedEnteredQuantity;

        inventory.get(itemID).setQuantity(remainingQuantity);

        if (selectedItemAvailableQuantity < remainingQuantity) {
            System.out.println("Insufficient Quantity!!\n");
            throw new InvalidAmountException();
        }

        return remainingQuantity;
    }


    //return type still in question
    public double checkPriceGivenQuantity(String itemID,String enteredQuantity, Map<String, CandyStoreItem> inventory,
                                          double currentBalance) {
        double itemPrice = inventory.get(itemID).getCandyPrice();
        parsedEnteredQuantity = Double.parseDouble(enteredQuantity);
		double calculatedTotal = (parsedEnteredQuantity)*(itemPrice);

        double changedBalance = currentBalance - calculatedTotal;

        if (currentBalance - calculatedTotal >= 0) {
            return changedBalance;
        } else{
            return currentBalance;
        }
    }


    public ReceiptItems receiptCreation (String itemID, Map<String, CandyStoreItem> inventory, String enteredQuantity){

        ReceiptItems item = new ReceiptItems("", "", "", "",
                "", "", "");

        String companyName = "";
        if (inventory.get(itemID).getCandyType().equalsIgnoreCase("CH")){
            companyName = "Chocolate Confectionery";
        } else if (inventory.get(itemID).getCandyType().equalsIgnoreCase("SR")) {
            companyName = "Sour Flavored Candy";
        } else if (inventory.get(itemID).getCandyType().equalsIgnoreCase("HC")) {
            companyName = "Hard Tack Confectionery";
        } else {
            companyName = "Licorce and Jellies";
        }


        //double enteredQuantityAsString = String.valueOf(enteredQuantity);
        String candyPrice = String.valueOf(inventory.get(itemID).getCandyPrice());

        double itemPrice = inventory.get(itemID).getCandyPrice();
        double parsedDoubleEnteredQuantity = Double.parseDouble(enteredQuantity);
        double calculatedTotal = (double)(parsedDoubleEnteredQuantity)*(itemPrice);
        String total = String.valueOf(calculatedTotal);

        String[] receiptLineArray = new String[5];
        receiptLineArray[0] = enteredQuantity;
        receiptLineArray[1] = inventory.get(itemID).getCandyName();
        receiptLineArray[2] = companyName;
        receiptLineArray[3] = candyPrice;
        receiptLineArray[4] = total;

        item.setQuantity(enteredQuantity);
        item.setCandyName(inventory.get(itemID).getCandyName());
        item.setCompanyName(companyName);
        item.setIndividualPrice(candyPrice);
        item.setIndividualCandySum(total);

        return item;
    }

}
