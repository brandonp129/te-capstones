package com.techelevator.items;

public class Licorice extends CandyStoreItem{
    public Licorice(String candyType, String candyId, String candyName, String candyWrapper, double candyPrice) {
        super(candyType, candyId, candyName, candyWrapper, candyPrice);
    }

    public Licorice(String candyId) {
        super(candyId);
    }


}
