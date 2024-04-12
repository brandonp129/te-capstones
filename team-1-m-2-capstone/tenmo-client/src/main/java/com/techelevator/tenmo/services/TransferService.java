package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransferService {

    private String baseApiUrl;
    private AuthenticatedUser currentUser;
    private RestTemplate restTemplate = new RestTemplate();


    public TransferService(String baseApiUrl) {
        this.baseApiUrl = baseApiUrl;
    }

    public void setCurrentUser(AuthenticatedUser currentUser) {
        this.currentUser = currentUser;
    }

    public Account getAccount(){
        String url = baseApiUrl + "accounts";
        Account returnedAccount = null;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        Account newAccount = new Account();
        HttpEntity<Account> entity = new HttpEntity<Account>(newAccount, headers);

        ResponseEntity<Account> response = restTemplate.exchange(url, HttpMethod.GET, entity, Account.class);

        returnedAccount = response.getBody();

        return returnedAccount;
    }

    public List<User> getAllUsers(){

        List<User> allUsers = new ArrayList<User>();
        String url = baseApiUrl + "users";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> entity = new HttpEntity<Void>(headers);

        ResponseEntity<User[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, User[].class);

        return Arrays.asList(response.getBody());

    }

    public Transfer changeBalance(Transfer transfer) {
        String url = baseApiUrl + "transfers";
        Transfer newTransfer = new Transfer();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Transfer> entity = new HttpEntity<Transfer>(transfer, headers);

        ResponseEntity<Transfer> response = restTemplate.exchange(url, HttpMethod.POST, entity, Transfer.class);

        newTransfer = response.getBody();

        return newTransfer;
    }

    public List<Transfer> getAllTransfers(){

        List<Transfer> allTransfer = new ArrayList<Transfer>();
        String url = baseApiUrl + "transfers";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> entity = new HttpEntity<Void>(headers);

        ResponseEntity<Transfer[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Transfer[].class);

        return Arrays.asList(response.getBody());

    }

    public List<Account> getAllAccounts() {

        List<Account> allAccounts = new ArrayList<Account>();
        String url = baseApiUrl + "listAccounts";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> entity = new HttpEntity<Void>(headers);

        ResponseEntity<Account[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Account[].class);

        return Arrays.asList(response.getBody());
    }


}
