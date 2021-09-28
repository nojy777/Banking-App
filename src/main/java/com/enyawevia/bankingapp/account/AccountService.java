package com.enyawevia.bankingapp.account;

import com.enyawevia.bankingapp.user.LoggedInUser;
import com.enyawevia.bankingapp.util.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Account Service Class. Contains all the functions to be performed on an account
 * @author Enoje Awevia
 * @version 1.0
 */
public class AccountService {

    ConnectionService connect = new ConnectionService();
    Scanner scanner = new Scanner(System.in);

    /** Create a new account
     * @param accountType an integer representing the type of account to be created
     * @return An integer representing the result of the action.
     */
    public int createAccount(int accountType) {
        String sql = "INSERT INTO accounts (account_number, account_name, balance, user_id, account_type) VALUES(?, ?, ?, ?, ?)";
        try {
            Connection c = connect.establishConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, generateAccountNumber());
            ps.setString(2, LoggedInUser.getFirstName() + " " + LoggedInUser.getLastName());
            ps.setDouble(3, 0.00);
            ps.setInt(4, LoggedInUser.getId());
            ps.setInt(5, accountType);

            int res = ps.executeUpdate();
            return res;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    private String generateAccountNumber() {
        int length = 8;

        // set characters in random string
        String numericString = "1234567890";
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = (int) (numericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(numericString.charAt(index));
        }
        return sb.toString();
    }

    /** Deposits funds into an account
     * @param selectedAccount The selected account
     * @param amount A double value representing the amount to be deposited
     * @return An integer representing the result of the action.
     */
    public int depositFunds(Account selectedAccount, double amount) {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        try {
            Connection c = connect.establishConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            // get new balance
            double newBalance = selectedAccount.getBalance() + amount;

            ps.setDouble(1, newBalance);
            ps.setInt(2, selectedAccount.getAccountId());

            int res = ps.executeUpdate();
            return res;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    /** List the accounts belonging to a particular user
     * @param userId An integer value representing the id of the user
     * @return a list of Accounts
     */
    public List<Account> getUserAccounts(int userId) {
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        try {
            Connection c = connect.establishConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            List<Account> accountList = new ArrayList<>();

            if(!rs.isBeforeFirst()) {
                System.out.println("You must create at least one account to perform a transaction");
                return null;
            }

            // accounts found for user
            while(rs.next()) {
                Account acc = new Account();
                acc.setAccountId(rs.getInt("id"));
                acc.setAccountNumber(rs.getString("account_number"));
                acc.setAccountName(rs.getString("account_name"));
                acc.setBalance(rs.getDouble("balance"));
                accountList.add(acc);
            }
            return accountList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /** Retrieve the list of accounts and allows the user select the account to perform a transaction
     * @return the selected Account
     */
    public Account listAndSelectAccount() {
        try {
            List<Account> accList = getUserAccounts(LoggedInUser.getId());
            for (int x = 0; x < accList.size(); x++) {
                System.out.println((x + 1) + ". " + accList.get(x).getAccountNumber() + " => " + accList.get(x).getBalance());
            }

            int accountIndex = scanner.nextInt();

            if (accountIndex < 1 || accountIndex > accList.size()) {
                System.out.println("Please enter a correct option");
                // throw new Exception("xxx");
            }

            Account selectedAccount = accList.get(accountIndex - 1);
            return selectedAccount;
        } catch (Exception ex) {
            return null;
        }
    }

    /** Withdraw funds from an account
     * @param selectedAccount the selected account
     * @param amount the amount to withdraw
     * @return an integer indicating the status of the transaction
     */
    public int withdrawFunds(Account selectedAccount, double amount) {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        try {
            Connection c = connect.establishConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            // check if user has sufficient funds
            if(selectedAccount.getBalance() < amount) {
                System.out.println("Insufficient fund. ");
                return 0;
            }

            // get new balance
            double newBalance = selectedAccount.getBalance() - amount;

            ps.setDouble(1, newBalance);
            ps.setInt(2, selectedAccount.getAccountId());

            int res = ps.executeUpdate();
            return res;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    /** Check the balance in an account
     * @param selectedAccount the selected account
     * @return a double value indicating the balance on the account
     */
    public double checkBalance(Account selectedAccount) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try {
            Connection c = connect.establishConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setInt(1, selectedAccount.getAccountId());
            ResultSet rs = ps.executeQuery();

            double balance = 0;
            while(rs.next()) {
                balance = rs.getDouble("balance");
            }
            return balance;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }
}
