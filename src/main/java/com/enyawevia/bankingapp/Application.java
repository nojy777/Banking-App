package com.enyawevia.bankingapp;

import com.enyawevia.bankingapp.user.User;
import com.enyawevia.bankingapp.user.UserService;

import java.util.Scanner;

public class Application {

    UserService userSvc = new UserService();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Application app = new Application();
        app.mainMenu();
    }

    public void mainMenu() {
        System.out.println("Welcome to Banking App");

        System.out.println("1. Create an account");
        System.out.println("2. Login");
        System.out.println("3. Exit");


        int menuResp = scanner.nextInt();

        switch(menuResp) {
            case 1:
                System.out.println("Register new account");

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

                userSvc.registerUser(user);
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
    }
}
