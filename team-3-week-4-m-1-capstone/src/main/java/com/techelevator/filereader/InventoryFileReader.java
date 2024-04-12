package com.techelevator.filereader;

import com.techelevator.ApplicationCLI;
import com.techelevator.CandyStore;
import com.techelevator.items.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/*
    This class should contain any and all details of access to the inventory file
 */
public class InventoryFileReader {

    private File inventoryFileName;
    private Map<String, CandyStoreItem> candiesMap = new TreeMap<String, CandyStoreItem>();

    public InventoryFileReader() {

    }

    public Map<String, CandyStoreItem> fileToMap (File fileName) throws FileNotFoundException {
        try (Scanner fileReader = new Scanner(fileName)) {

            while (fileReader.hasNextLine()) {
                String fileLine = fileReader.nextLine();
                String[] partsOfLine = fileLine.split("\\|");

                CandyStoreItem item = candyStoreItemAssembly(partsOfLine);
                candiesMap.put(item.getCandyId(), item);

            }

        }
        return candiesMap;
    }
    private CandyStoreItem candyStoreItemAssembly(String[] partsOfLine){
        CandyStoreItem item = null;

        String candyType = partsOfLine[0];
        String candyId = partsOfLine[1];
        String candyName = partsOfLine[2];
        String candyWrapper = partsOfLine[4];
        String candyPrice = partsOfLine[3];

        if (candyType.equalsIgnoreCase("CH")){
            item = new Chocolates(candyId);
        } else if (candyType.equalsIgnoreCase("SR")) {
            item = new Sours(candyId);
        }else if (candyType.equalsIgnoreCase("HC")){
            item = new HardCandy(candyId);
        } else if (candyType.equalsIgnoreCase("LI")){
            item = new Licorice(candyId);
        }

        item.setCandyType(candyType);
        item.setCandyName(candyName);
        item.setCandyPrice(Double.parseDouble(candyPrice));
        item.setCandyWrapper(candyWrapper);

        return item;
    }



    public InventoryFileReader(File inventoryFileName) {
        this.inventoryFileName = inventoryFileName;

    }

    public Map<String, CandyStoreItem> getCandies() {
        return candiesMap;
    }


}
