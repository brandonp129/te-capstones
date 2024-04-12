package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    Account getAccountById(int accountId);

    public List<Account> getAllAccounts();
}
