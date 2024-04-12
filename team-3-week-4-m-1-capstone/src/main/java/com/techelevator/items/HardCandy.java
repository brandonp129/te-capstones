package com.techelevator.items;

public class HardCandy extends CandyStoreItem{
    public HardCandy(String candyType, String candyId, String candyName, String candyWrapper, double candyPrice) {
        super(candyType, candyId, candyName, candyWrapper, candyPrice);
    }

    public HardCandy(String candyId) {
        super(candyId);
    }
}
