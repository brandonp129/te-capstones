package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.Users;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class AccountController {

    private UserDao userDao;
    private AccountDao accountDao;

    public AccountController(AccountDao accountDao, UserDao userDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @RequestMapping(path = "/accounts", method = RequestMethod.GET)
    public Account getLoggedInAccount(Principal principal) {
        String loggedInUsername = principal.getName();
        User loggedInUser = userDao.getUserByUsername(loggedInUsername);

        return accountDao.getAccountById(loggedInUser.getId());
    }

    @RequestMapping(path="/users", method=RequestMethod.GET)
    public List<Users> list(Principal principal) {
        String loggedInUsername = principal.getName();
        User loggedInUser = userDao.getUserByUsername(loggedInUsername);

        return userDao.getListUsers(loggedInUser.getId());
    }

    @RequestMapping(path="/listAccounts", method=RequestMethod.GET)
    public List<Account> listOfAccounts(Principal principal) {
        String loggedInUsername = principal.getName();
        User loggedInUser = userDao.getUserByUsername(loggedInUsername);

        return accountDao.getAllAccounts();
    }


}
