package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getAccountById(int userId) {
        Account account = null;
        String sql = "SELECT account_id, account.user_id, balance, username FROM account JOIN tenmo_user ON tenmo_user.user_id = account.user_id WHERE account.user_id = ?";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, userId);
        if (rows.next()) {
            account = mapRowToAccount(rows);
        }
        return account;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> list = new ArrayList<>();

        String sql = "SELECT account_id, tenmo_user.user_id, username, balance " +
                "FROM account " +
                "JOIN tenmo_user ON tenmo_user.user_id = account.user_id";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next() ) {
            Account account = mapRowToAccount(results);
            list.add(account);
        }
        return list;
    }


    private Account mapRowToAccount(SqlRowSet row) {
        Account account = new Account();

        account.setAccountId(row.getInt("account_id"));
        account.setUserId(row.getInt("user_id"));
        account.setBalance(row.getDouble("balance"));
        account.setUsername(row.getString("username"));

        return account;
    }

}
