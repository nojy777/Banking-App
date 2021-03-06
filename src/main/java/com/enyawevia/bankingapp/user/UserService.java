package com.enyawevia.bankingapp.user;

import com.enyawevia.bankingapp.util.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/** User service class. Contains the login and register functionalities.
 * @author Enoje Awevia
 * @version 1.0
 */
public class UserService {

    ConnectionService connect = new ConnectionService();

    /** Register a user
     * @param user represents a user to be registered
     * @return user represents the registered user
     */
    public User registerUser(User user) {
        String sql = "insert into users (first_name, last_name, email, password) values(?,?,?,?)";
        try {
            Connection c = connect.establishConnection();

            // check duplicate emails
            String dupEmailSql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement dupEmailPs = c.prepareStatement(dupEmailSql);
            dupEmailPs.setString(1, user.getEmail());
            ResultSet dupEmailRs = dupEmailPs.executeQuery();
            if(dupEmailRs.next()){
                System.out.println("Email is already taken by another user. Please use a different email address");
                return null;
            }

            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());

            int resp = ps.executeUpdate();
            if(resp == 1) {
                System.out.println("Registration  Successful");
            } else {
                System.out.println("Registration failed. Please try again");
            }

        } catch (Exception ex) {

        }
        return null;
    }

    /** Login a user
     * @param email represents the email of the user
     * @param password represents the password of the user
     * @return boolean representing the status of the action
     */
    public boolean loginUser(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try {
            Connection c = connect.establishConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                // set logged In User
                LoggedInUser.setId(rs.getInt("id"));
                LoggedInUser.setFirstName(rs.getString("first_name"));
                LoggedInUser.setLastName(rs.getString("last_name"));
                LoggedInUser.setEmail(rs.getString("email"));
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
        return false;
    }
}
