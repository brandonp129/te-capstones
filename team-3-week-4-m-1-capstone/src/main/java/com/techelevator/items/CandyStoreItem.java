package com.techelevator.items;

/*
    This represents a single catering item in the system

    This is an abstract class that should be used as a superclass for the items.
 */
public abstract class CandyStoreItem {

    private final String candyId;
    private String candyType;
    private String candyName;
    private double candyPrice;
    private String candyWrapper;
    private double quantity = 100;


    public CandyStoreItem(String candyId) {
        this.candyId = candyId;
    }
    public CandyStoreItem(String candyType, String candyId, String candyName,
                          String candyWrapper,  double candyPrice) {

        this.candyType = candyType;
        this.candyId = candyId;
        this.candyName = candyName;
        this.candyWrapper = candyWrapper;
        this.candyPrice = candyPrice;

    }


    public String getCandyId() {
        return candyId;
    }

    public String getCandyType() {
        return candyType;
    }

    public void setCandyType(String candyType) {
        this.candyType = candyType;
    }

    public String getCandyName() {
        return candyName;
    }

    public void setCandyName(String candyName) {
        this.candyName = candyName;
    }

    public double getCandyPrice() {
        return candyPrice;
    }

    public void setCandyPrice(double candyPrice) {
        this.candyPrice = candyPrice;
    }

    public String getCandyWrapper() {
        return candyWrapper;
    }

    public void setCandyWrapper(String candyWrapper) {
        this.candyWrapper = candyWrapper;
       if(this.candyWrapper.equalsIgnoreCase("T")) {
           this.candyWrapper = "Y";
       } else {
           this.candyWrapper = "N";
       }

    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;

    }
}


