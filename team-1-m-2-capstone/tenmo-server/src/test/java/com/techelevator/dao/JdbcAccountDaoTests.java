package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcAccountDaoTests extends BaseDaoTests{
    private static final Account ACCOUNT_1 = new Account(2001 , 1001, 1000.00);
    private static final Account ACCOUNT_2 = new Account(2002 , 1002, 1000.00);
    private static final Account ACCOUNT_3 = new Account(2003 , 1003, 1000.00);

    private JdbcAccountDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcAccountDao(jdbcTemplate);
    }

    @Test
    public void getUserByUsername_given_valid_user_returns_user() {
        Account account = sut.getAccountById(ACCOUNT_1.getAccountId());

        Assert.assertEquals(ACCOUNT_1, account);
    }


    @Test
    public void getAccountById_given_invalid_user_id_returns_null() {
        Account account = sut.getAccountById(-1);

        Assert.assertNull(account);
    }
}
