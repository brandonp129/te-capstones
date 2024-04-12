package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.items.*;
import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;


/*
    This class should encapsulate all the functionality of the Candy Store application, meaning it should
    contain all the "work"
 */
public class CandyStore {
    private InventoryFileReader reader;
    private double currentBalance = 0;

    public Map<String, CandyStoreItem> getInventory() {
        Menu menu = new Menu();
        File inventoryFile = menu.userFile();
        reader = new InventoryFileReader();

        Map<String, CandyStoreItem> inventoryMap = null;

        try {
            inventoryMap = reader.fileToMap(inventoryFile);
        } catch (FileNotFoundException e) {
            printMessage("file " + inventoryFile.getName() + " was not found. Exiting");
            System.exit(1);
        }

        return inventoryMap;


    }


    public void printMessage(String message) {
        System.out.println(message);
    }


    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }



}
