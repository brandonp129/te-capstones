package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.Users;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class TransferController {

    private UserDao userDao;
    private TransferDao transferDao;

    private AccountDao accountDao;

    public TransferController(UserDao userDao, TransferDao transferDao) {
        this.userDao = userDao;
        this.transferDao = transferDao;
    }

    @RequestMapping(path="/transfers", method= RequestMethod.POST)
    public Transfer add(@RequestBody Transfer transfer, Principal principal) {
        String loggedInUsername = principal.getName();
        User loggedInUser = userDao.getUserByUsername(loggedInUsername);

        return transferDao.addTransfer(transfer);
    }

    @RequestMapping(path="/transfers", method=RequestMethod.GET)
    public List<Transfer> list(Principal principal) {
        String loggedInUsername = principal.getName();
        User loggedInUser = userDao.getUserByUsername(loggedInUsername);

        return transferDao.getAllTransfers(loggedInUser.getUsername());
    }


}
