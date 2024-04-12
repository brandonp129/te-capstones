package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TransferService;

import java.util.List;

public class App {

    public static final int MENU_EXIT = 0;
    public static final int LOGIN_MENU_REGISTER = 1;
    public static final int LOGIN_MENU_LOGIN = 2;
    public static final int MAIN_MENU_VIEW_BALANCE = 1;
    public static final int MAIN_MENU_VIEW_TRANSFER_HISTORY = 2;
    public static final int MAIN_MENU_VIEW_PENDING_REQUESTS = 3;
    public static final int MAIN_MENU_SEND_TE_BUCKS = 4;
    public static final int MAIN_MENU_REQUEST_TE_BUCKS = 5;

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);

    private AuthenticatedUser currentUser;

    private Account account;
    private TransferService transferService = new TransferService(API_BASE_URL);

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            transferService.setCurrentUser(currentUser);
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != MENU_EXIT && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == LOGIN_MENU_REGISTER) {
                handleRegister();
            } else if (menuSelection == LOGIN_MENU_LOGIN) {
                handleLogin();
            } else if (menuSelection != MENU_EXIT) {
                consoleService.printMessage("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        consoleService.printMessage("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            consoleService.printMessage("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != MENU_EXIT) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == MAIN_MENU_VIEW_BALANCE) {
                viewCurrentBalance();
            } else if (menuSelection == MAIN_MENU_VIEW_TRANSFER_HISTORY) {
                viewTransferHistory();
            } else if (menuSelection == MAIN_MENU_VIEW_PENDING_REQUESTS) {
                viewPendingRequests();
            } else if (menuSelection == MAIN_MENU_SEND_TE_BUCKS) {
                sendBucks();
            } else if (menuSelection == MAIN_MENU_REQUEST_TE_BUCKS) {
                requestBucks();
            } else if (menuSelection == MENU_EXIT) {
                continue;
            } else {
                consoleService.printMessage("Invalid Selection");
            }
            consoleService.pause();
            mainMenu();
        }
    }

	private void viewCurrentBalance() {

       Account returnedAccount = transferService.getAccount();
        consoleService.showCurrentBalance(returnedAccount);

	}

	private void viewTransferHistory() {

        Transfer transfer = new Transfer();
        List<Transfer> allTransfers = transferService.getAllTransfers();
        List<Account> allAccounts = transferService.getAllAccounts();

        int sendTo = 0;
        consoleService.showAllTransfers(allTransfers, allAccounts, currentUser);

        sendTo = consoleService.askUserIdToSentTo("Please enter transfer ID to view details (0 to cancel): ");
        if (sendTo == 0){
            mainMenu();}
        for(Transfer transfers : allTransfers){
            if(sendTo == transfers.getTransferId()){
                consoleService.showTransferDetails(transfers, allAccounts);
                consoleService.pause();
                mainMenu();
            }
        }

	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {
        Transfer transfer = new Transfer();
		List<User> users = transferService.getAllUsers();
        int sendTo = 0;
        double amountToSend = 0;
		consoleService.showAllAccounts(users);

        //Select User to sent to
        /*
            need to check if valid user and also clean up!!!!!
         */
        sendTo = consoleService.askUserIdToSentTo("Enter ID of user you are sending to (0 to cancel):");
        if (sendTo == 0) {
            mainMenu();
        }
        amountToSend = consoleService.askAmountToSend("Enter amount:");
        if (amountToSend <= 0) {
            consoleService.errorMessage();
            sendBucks();
        } else if (transferService.getAccount().getBalance() < amountToSend) {
            consoleService.invalidAmountErrorMessage();
            sendBucks();
        }
        transfer.setToUserId(sendTo);
        transfer.setFromUserId(currentUser.getUser().getId());
        transfer.setAmount(amountToSend);
        transferService.changeBalance(transfer);
    }



	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}

}
