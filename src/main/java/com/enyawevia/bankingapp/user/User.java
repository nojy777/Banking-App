package com.enyawevia.bankingapp.user;

/** Represents a user.
 * @author
 * @version 1.0
 */
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User() {
    }

    /** User constructor, used to initialize a user
     */
    public User(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    /** Gets the user id
     * @return an integer representing the user id
     */
    public int getId() {
        return id;
    }

    /** Set the user id
     * @param id an integer representing the user id
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Gets the first name of the user
     * @return a String representing the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /** Set the first name of the user
     *  * @param firstName a string representing the first name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /** Gets the last name of the user
     * @return a String representing the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /** Set the last name of the user
     * @param lastName a String representing the last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /** Gets the email of the user
     * @return a String representing the email of the user
     */
    public String getEmail() {
        return email;
    }

    /** Set the email of the user
     * @param email a String representing the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** Gets the password of the user
     * @return a String representing the password of the user
     */
    public String getPassword() {
        return password;
    }

    /** Set the password of the user
     * @param password a String representing the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
