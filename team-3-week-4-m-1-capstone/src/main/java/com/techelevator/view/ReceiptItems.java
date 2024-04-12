package com.techelevator.view;

public class ReceiptItems {

    private String quantity;
    private String candyName;
    private String companyName;
    private String individualPrice;
    private String individualCandySum;
    private String transactionTotal;
    private String changeOwed;

    public ReceiptItems(String quantity, String candyName, String companyName,
                        String individualPrice,String individualCandySum,
                        String transactionTotal,String changeOwed){

        this.quantity =quantity;
        this.candyName = candyName;
        this.companyName = companyName;
        this.individualPrice = individualPrice;
        this.individualCandySum = individualCandySum;


        this.transactionTotal = transactionTotal;
        this.changeOwed = changeOwed;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCandyName() {
        return candyName;
    }

    public void setCandyName(String candyName) {
        this.candyName = candyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndividualPrice() {
        return individualPrice;
    }

    public void setIndividualPrice(String individualPrice) {
        this.individualPrice = individualPrice;
    }

    public String getIndividualCandySum() {
        return individualCandySum;
    }

    public void setIndividualCandySum(String individualCandySum) {
        this.individualCandySum = individualCandySum;
    }

    public String getTransactionTotal() {
        return transactionTotal;
    }

    public void setTransactionTotal(String transactionTotal) {
        this.transactionTotal = transactionTotal;
    }

    public String getChangeOwed() {
        return changeOwed;
    }

    public void setChangeOwed(String changeOwed) {
        this.changeOwed = changeOwed;
    }
}
