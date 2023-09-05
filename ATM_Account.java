package oasis_infobyte;

import java.util.ArrayList;
import java.util.List;

public class ATM_Account {

    private String accountID;
    private double balance;
    private String pin;
    private List<ATM_Transaction> transactionHistory;

    public ATM_Account(String accountID, double initialBalance, String pin) {
        this.accountID = accountID;
        this.balance = initialBalance;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        }
    }

    public void transfer(double amount, ATM_Account recipient) {
        if (amount <= balance) {
            balance -= amount;
            recipient.balance += amount;
        }
    }

    public List<ATM_Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getPin() {
        return pin;
    }
}
