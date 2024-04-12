package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transfer addTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfer(transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (2, 2, (SELECT account_id FROM account WHERE user_id = ?), (SELECT account_id FROM account WHERE user_id = ?), ?) RETURNING transfer_id;";
        int newTransferId = jdbcTemplate.queryForObject(sql,int.class, transfer.getFromUserId(), transfer.getToUserId(), transfer.getAmount());
        transfer.setTransferId(newTransferId);

        addToBalance(transfer);
        subtractFromBalance(transfer);

        return transfer;
    }

    public int addToBalance(Transfer transfer){
        String sql = "UPDATE account SET balance = balance + ? WHERE user_id = ? ;";

        int numberOfRowsChanged = jdbcTemplate.update(sql, transfer.getAmount(), transfer.getToUserId());

        return numberOfRowsChanged;
    }

    public int subtractFromBalance(Transfer transfer){
        String sql = "UPDATE account SET balance = balance - ? WHERE user_id = ? ;";

        int numberOfRowsChanged = jdbcTemplate.update(sql, transfer.getAmount(), transfer.getFromUserId());

        return numberOfRowsChanged;
    }

    @Override
    public List<Transfer> getAllTransfers(String username) {
        List<Transfer> list = new ArrayList<>();

        String sql = "SELECT transfer_id, transfer_type_desc, transfer_status_desc, account_from, account_to, amount " +
                "FROM transfer " +
                "JOIN transfer_type ON transfer.transfer_type_id = transfer_type.transfer_type_id " +
                "JOIN transfer_status ON transfer.transfer_status_id = transfer_status.transfer_status_id " +
                "WHERE account_from = (select account_id from account where user_id = (select user_id from tenmo_user where username = ? )) " +
                "OR account_to = (select account_id from account where user_id = (select user_id from tenmo_user where username = ? ))";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username, username);
        while (results.next() ) {
            Transfer transfer = mapRowToTransfer(results);
            list.add(transfer);
        }
        return list;
    }



    private Transfer mapRowToTransfer(SqlRowSet row) {
        Transfer transfer = new Transfer();

//        transfer.setFromUserId(row.getInt("account_from"));
//        transfer.setToUserId(row.getInt("account_to"));

        transfer.setTransferId(row.getInt("transfer_id"));
        transfer.setTransferType(row.getString("transfer_type_desc"));
        transfer.setTransferStatus(row.getString("transfer_status_desc"));
        transfer.setAccountFrom(row.getInt("account_from"));
        transfer.setAccountTo(row.getInt("account_to"));
        transfer.setAmount(row.getDouble("amount"));

        return transfer;
    }

}
