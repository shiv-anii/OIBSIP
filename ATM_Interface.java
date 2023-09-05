package oasis_infobyte;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ATM_Interface {

    private String userID;
    private String userPIN;
    private ATM_Account account;
    private List<ATM_Account> accounts;

    public ATM_Interface(String userID, String userPIN, ATM_Account account, List<ATM_Account> accounts) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.account = account;
        this.accounts = accounts;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the ATM Interface");
            System.out.println("1. View Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Select an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    performWithdraw();
                    break;
                case 3:
                    performDeposit();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (ATM_Transaction transaction : account.getTransactionHistory()) {
            System.out.println("Type: " + transaction.getType() +
                    ", Amount: " + transaction.getAmount() +
                    ", Timestamp: " + transaction.getTimestamp());
        }
    }

    private void performWithdraw() {
        double amount = ATM_UserInput.getDoubleInput("Enter withdrawal amount: ");
        if (account.getBalance() >= amount) {
            account.withdraw(amount);
            addTransactionToHistory("Withdrawal", -amount);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private void performDeposit() {
        double amount = ATM_UserInput.getDoubleInput("Enter deposit amount: ");
        account.deposit(amount);
        addTransactionToHistory("Deposit", amount);
    }

    private void performTransfer() {
        String destinationAccount = ATM_UserInput.getStringInput("Enter destination account: ");
        ATM_Account recipientAccount = getRecipientAccount(destinationAccount);

        if (recipientAccount != null) {
            double amount = ATM_UserInput.getDoubleInput("Enter transfer amount: ");
            if (account.getBalance() >= amount) {
                account.transfer(amount, recipientAccount);
                addTransactionToHistory("Transfer to " + recipientAccount.getAccountID(), -amount);
                addTransactionToHistory("Transfer from " + account.getAccountID(), amount);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Recipient account not found.");
        }
    }

    private ATM_Account getRecipientAccount(String destinationAccount) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter recipient's User ID: ");
        String recipientUserID = scanner.nextLine();

        for (ATM_Account account : accounts) {
            if (account.getAccountID().equals(recipientUserID)) {
                return account;
            }
        }

        System.out.println("Recipient account not found.");
        return null;
    }

    private void addTransactionToHistory(String type, double amount) {
        ATM_Transaction transaction = new ATM_Transaction(type, amount);
        account.getTransactionHistory().add(transaction);
    }
}
