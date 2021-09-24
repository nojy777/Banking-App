package com.enyawevia.bankingapp.account;

import com.enyawevia.bankingapp.user.LoggedInUser;

public class AccountService {

    public void  createAccount(int accountType) {
        System.out.println("Logged in user first name is : " + LoggedInUser.getFirstName());
        System.out.println("Logged in user first name is : " + LoggedInUser.getLastName());

    }
}
