package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public void showCurrentBalance(Account account) {
        System.out.println("Your current account balance is: " + account.getBalance());
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

    public void showAllAccounts(List<User> allUsers){
        System.out.println("-".repeat(60));
        System.out.println("Users");
        System.out.printf("%-10s %-7s%n", "ID", "Name");
        System.out.println("-".repeat(60));

            for (User users: allUsers) {
                System.out.printf("%-10s %-7s%n" , users.getId(), users.getUsername() );
            }
        System.out.println("-".repeat(9));
        System.out.println();
    }

    public int askUserIdToSentTo(String prompt) {

        int sendTo;
        System.out.print(prompt);

        return sendTo = Integer.parseInt(scanner.nextLine());
    }

    public int askAmountToSend(String prompt) {

        int amountToSend;
        System.out.print(prompt);

        return amountToSend = Integer.parseInt(scanner.nextLine());
    }

    public void showAllTransfers(List<Transfer> allTransfers, List<Account> allAccounts, AuthenticatedUser currentUser){
        System.out.println("-".repeat(60));
        System.out.println("Transfers");
        System.out.printf("%-10s %-15s %-7s%n", "ID", "From/To" , "Amount");
        System.out.println("-".repeat(60));

        for (Transfer transfers: allTransfers) {
            for (Account accounts: allAccounts){
                if(transfers.getAccountFrom() == accounts.getAccountId() && accounts.getUserId() != currentUser.getUser().getId()){
                    System.out.printf("%-10s %-4s %-8s $%4.2f %n", transfers.getTransferId(), "From: ", accounts.getUsername(), transfers.getAmount());
                }
                if(transfers.getAccountTo() == accounts.getAccountId() && accounts.getUserId() != currentUser.getUser().getId()){
                    System.out.printf("%-10s %-4s %-10s $%4.2f %n", transfers.getTransferId(), "To: ", accounts.getUsername(), transfers.getAmount());
                }
            }
        }

        System.out.println("-".repeat(9));
        System.out.println();
    }

    public void showTransferDetails(Transfer transfer, List<Account> allAccounts) {
        System.out.println("-".repeat(60));
        System.out.println("Transfer Details");
        System.out.println("-".repeat(60));

        System.out.println("  Id: " + transfer.getTransferId());
        for (Account accounts : allAccounts) {
            if (transfer.getAccountFrom() == accounts.getAccountId()) {
                System.out.println("  From: " + accounts.getUsername());
            }
            if (transfer.getAccountTo() == accounts.getAccountId())
                System.out.println("  To: " + accounts.getUsername());
        }
        System.out.println("  Type: " + transfer.getTransferType());
        System.out.println("  Status: " + transfer.getTransferStatus());
        System.out.printf("  Amount: " + "$%4.2f %n", transfer.getAmount());

    }





    public void errorMessage() {
        System.out.println();
        System.out.println("Has to be a positive number");
    }

    public void invalidAmountErrorMessage() {
        System.out.println();
        System.out.println("Insufficient funds");
    }

    public void invalidUser() {
        System.out.println();
        System.out.println("Please enter a valid user");
    }



}
