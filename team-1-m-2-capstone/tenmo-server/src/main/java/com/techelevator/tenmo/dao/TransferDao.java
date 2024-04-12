package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

import java.util.List;

public interface TransferDao {

    Transfer addTransfer(Transfer transfer);

    public int addToBalance(Transfer transfer);

    public int subtractFromBalance(Transfer transfer);

    public List<Transfer> getAllTransfers(String username);
}
