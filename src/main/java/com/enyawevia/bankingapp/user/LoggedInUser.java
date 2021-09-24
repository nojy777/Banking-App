package com.enyawevia.bankingapp.user;

public class LoggedInUser {
    private static int id;
    private static String firstName;
    private static String lastName;
    private static String email;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        LoggedInUser.id = id;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        LoggedInUser.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        LoggedInUser.lastName = lastName;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        LoggedInUser.email = email;
    }
}
