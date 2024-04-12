package com.techelevator.items;

public class Sours extends CandyStoreItem{
    public Sours(String candyType, String candyId, String candyName, String candyWrapper, double candyPrice) {
        super(candyType, candyId, candyName, candyWrapper, candyPrice);
    }

    public Sours(String candyId) {
        super(candyId);
    }
}
