package com.enyawevia.bankingapp.account;

/** Represents an Account.
 * @author Enoje Awevia
 * @version 1.0
 */
public class Account {
    private int accountId;
    private String accountName;
    private String accountNumber;
    private double balance;

    /** Gets the account id
     * @return An integer representing the account's id
     */
    public int getAccountId() {
        return accountId;
    }

    /** Set the account id
     * @param accountId an integer representing the account id
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /** Gets the account name
     * @return a String representing the account name
     */
    public String getAccountName() {
        return accountName;
    }

    /** Sets the account name
     * @param accountName  a String representing the account name
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /** Gets the account name
     * @return a String representing the account name
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /** Sets the account number
     * @param accountNumber  a String representing the account number
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /** Gets the balance of the account
     * @return a double representing the account balance
     */
    public double getBalance() {
        return balance;
    }

    /** Sets the account balance
     * @param balance  a double representing the account balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
