package com.enyawevia.bankingapp;

import com.enyawevia.bankingapp.account.Account;
import com.enyawevia.bankingapp.account.AccountService;
import com.enyawevia.bankingapp.user.User;
import com.enyawevia.bankingapp.user.UserService;

import java.util.Scanner;

public class Application {

    UserService userSvc = new UserService();
    AccountService accSvc = new AccountService();

    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Application app = new Application();
        app.mainMenu();
    }

    public void mainMenu() {
        System.out.println("Welcome to Banking App");

        System.out.println("1. Sign up for an account");
        System.out.println("2. Login");
        System.out.println("3. Exit");


        int menuResp = scanner.nextInt();

        switch(menuResp) {
            case 1:
                System.out.println("Sign up new account");

                System.out.print("Enter your first name : ");
                String firstName = scanner.next();

                System.out.print("Enter your last name : ");
                String lastName = scanner.next();

                System.out.print("Enter Your email address (will be used for login) : ");
                String email = scanner.next();

                System.out.print("Enter Your password: ");
                String password = scanner.next();

                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);

                User resp = userSvc.registerUser(user);
                if(resp == null) {
                    break;
                }
                break;
            case 2:
                System.out.println("Login to your account");

                System.out.print("Enter you email address : ");
                String loginEmail = scanner.next();

                System.out.print("Enter your password : ");
                String loginPassword = scanner.next();

                boolean loginResp = userSvc.loginUser(loginEmail, loginPassword);
                if(loginResp == true) {
                    System.out.println("Login Successful");
                    userMenu();
                } else {
                    System.out.println("Login Failed");
                }

                break;
            case 3:
                System.out.println("Good Bye");
                System.exit(0);
            default:
                System.out.println("Invalid Input");
                break;
        }
        mainMenu();
    }

    public void userMenu() {
        System.out.println("1. Create an account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdrawal");
        System.out.println("4. Check balance");
        System.out.println("5. Logout");

        int userMenuResp = scanner.nextInt();

        switch (userMenuResp) {
            case 1:
                System.out.println("1. Savings");
                System.out.println("2. Checking");
                System.out.print("select an account type : ");
                int accountTypeResp = scanner.nextInt();

                int res = accSvc.createAccount(accountTypeResp);
                if(res == 1) {
                    System.out.println("Account Created Successfully");
                } else {
                    System.out.println("Failed to create account");
                }
                break;
            case 2:
                System.out.println("Deposit funds");
                // get all users accounts
                Account selectedDepositAccount = accSvc.listAndSelectAccount();

                if(selectedDepositAccount == null) {
                    break;
                }

                System.out.print("Enter amount to deposit: ");
                double depositAmount = scanner.nextDouble();

                if(depositAmount <= 0) {
                    System.out.println("Invalid amount. Amount must be greater than 0");
                    break;
                }

                if(accSvc.depositFunds(selectedDepositAccount, depositAmount) == 1) {
                    System.out.println("Funds Deposited Successfully");
                } else {
                    System.out.println("Failed to deposit funds");
                }
                break;
            case 3:
                // get all users accounts
                Account selectedWithdrawalAccount = accSvc.listAndSelectAccount();

                if(selectedWithdrawalAccount == null) {
                    break;
                }

                System.out.println("Enter amount to withdraw : ");
                double withdrawalAmount = scanner.nextDouble();

                if(withdrawalAmount <= 0) {
                    System.out.println("Invalid amount. Amount must be greater than 0");
                    break;
                }

                if(accSvc.withdrawFunds(selectedWithdrawalAccount, withdrawalAmount) == 1) {
                    System.out.println("Funds Withdrawn Successfully");
                } else {
                    System.out.println("Failed to withdraw funds");
                }
                break;
            case 4:
                // get all users accounts
                Account selectedCheckBalanceAccount = accSvc.listAndSelectAccount();

                if(selectedCheckBalanceAccount == null) {
                    break;
                }

                double balance = accSvc.checkBalance(selectedCheckBalanceAccount);
                System.out.println("Account balance is : " + balance);
                break;
            case 5:
                System.out.println("Logout");
                mainMenu();
                break;
            default:
                System.out.println("Invalid Input. Please enter a correct input");
                break;
        }
        userMenu();
    }
}
