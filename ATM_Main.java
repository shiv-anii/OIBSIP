package oasis_infobyte;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM_Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<ATM_Account> accounts = loadAccountsFromFile("C:\\Users\\HP\\Desktop\\accounts.txt");

        System.out.println("Welcome to the ATM");
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter User PIN: ");
        String userPIN = scanner.nextLine();

        ATM_Account userAccount = findUserAccount(accounts, userID, userPIN);

        if (userAccount != null) {
            ATM_Interface atm = new ATM_Interface(userID, userPIN, userAccount, accounts);
            atm.showMenu();
        } else {
            System.out.println("Invalid User ID or PIN. Exiting program ...");
        }
        scanner.close();
    }

    private static List<ATM_Account> loadAccountsFromFile(String fileName) {
        List<ATM_Account> accounts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 4) {
                    String accountID = parts[0];
                    String name = parts[1];
                    String pin = parts[2];
                    double balance = Double.parseDouble(parts[3]);

                    ATM_Account account = new ATM_Account(accountID, balance, pin);
                    accounts.add(account);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    private static ATM_Account findUserAccount(List<ATM_Account> accounts, String userID, String userPIN) {
        for (ATM_Account account : accounts) {
            if (account.getAccountID().equals(userID) && account.getPin().equals(userPIN)) {
                return account;
            }
        }
        System.out.println("User not found");
        return null;
    }
}

