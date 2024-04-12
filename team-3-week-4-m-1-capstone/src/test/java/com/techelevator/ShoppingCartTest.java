package com.techelevator;

import com.techelevator.items.CandyStoreItem;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {

    private ShoppingCart target;
    @Before
    public void setUp() {
        target = new ShoppingCart();
    }

    // Check for checkInventoryId
    @Test
    public void return_proper_total_with_valid_balance() {
        //CandyStoreItem item = new CandyStoreItem("SR", "S4", "moon bursts","T", 0.15 );

        // we carried a Map<String, CandyStoreItem> into most methods. But,
        // we cannot figure out hot to create a candyStoreItem for tests since
        // we cannot instantiate a CandyStoreItem since it is an abstract
    }
    @Test
    public void return_zero_with_balance_equal_to_price_x_quantity() {
        // we carried a Map<String, CandyStoreItem> into most methods. But,
        // we cannot figure out hot to create a candyStoreItem for tests since
        // we cannot instantiate a CandyStoreItem since it is an abstract
    }
    @Test
    public void return_original_with_not_enough_money() {
        // we carried a Map<String, CandyStoreItem> into most methods. But,
        // we cannot figure out hot to create a candyStoreItem for tests since
        // we cannot instantiate a CandyStoreItem since it is an abstract
    }



    // Check for askAddToCurrentBalance
    @Test
    public void return_no_money_added_when_over_one_hundred_entered() {
        // we carried a Map<String, CandyStoreItem> into most methods. But,
        // we cannot figure out hot to create a candyStoreItem for tests since
        // we cannot instantiate a CandyStoreItem since it is an abstract
    }
    @Test
    public void return_new_balance_when_less_than_100_entered() {
        // we carried a Map<String, CandyStoreItem> into most methods. But,
        // we cannot figure out hot to create a candyStoreItem for tests since
        // we cannot instantiate a CandyStoreItem since it is an abstract
    }
    @Test
    public void return_original_balance_when_less_than_zero_entered() {
        // we carried a Map<String, CandyStoreItem> into most methods. But,
        // we cannot figure out hot to create a candyStoreItem for tests since
        // we cannot instantiate a CandyStoreItem since it is an abstract
    }
    @Test
    public void return_original_balance_when_decimal_entered() {
        // we carried a Map<String, CandyStoreItem> into most methods. But,
        // we cannot figure out hot to create a candyStoreItem for tests since
        // we cannot instantiate a CandyStoreItem since it is an abstract
    }
}
