package com.techelevator.items;

public class Chocolates extends CandyStoreItem{
    public Chocolates(String candyType, String candyId, String candyName, String candyWrapper, double candyPrice) {
        super(candyType, candyId, candyName, candyWrapper, candyPrice);
    }

    public Chocolates(String candyId) {
        super(candyId);
    }
}
