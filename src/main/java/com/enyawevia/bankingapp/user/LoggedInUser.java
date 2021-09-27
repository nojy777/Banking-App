package com.enyawevia.bankingapp.user;

/** Represents the details of a logged-in user.
 * @author
 * @version 1.0
 */
public class LoggedInUser {

    private static int id;
    private static String firstName;
    private static String lastName;
    private static String email;

    /** Gets the account id
     * @return An integer representing the account's id
     */
    public static int getId() {
        return id;
    }

    /** Set the id of the logged-in user
     * @param id an integer representing the logged-in user id
     */
    public static void setId(int id) {
        LoggedInUser.id = id;
    }

    /** Gets the first name of the logged-in user
     * @return a string representing the first name of the logged-in user
     */
    public static String getFirstName() {
        return firstName;
    }

    /** Set the firstname of the logged-in user
     * @param firstName a String representing the first name of the logged-in user
     */
    public static void setFirstName(String firstName) {
        LoggedInUser.firstName = firstName;
    }

    /** Gets the last name of the logged-in user
     * @return a string representing the last name of the logged in user
     */
    public static String getLastName() {
        return lastName;
    }

    /** Set the last name of the logged-in user
     * @param lastName a String representing the last name of the logged-in user
     */
    public static void setLastName(String lastName) {
        LoggedInUser.lastName = lastName;
    }

    /** Gets the email of the logged-in user
     * @return a String representing the email of the logged-in user
     */
    public static String getEmail() {
        return email;
    }

    /** Set the email of the logged-in user
     * @param email a string representing the email of the logged-in user
     */
    public static void setEmail(String email) {
        LoggedInUser.email = email;
    }
}
