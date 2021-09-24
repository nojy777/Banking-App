package com.enyawevia.bankingapp.user;

import com.enyawevia.bankingapp.util.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {

    ConnectionService connect = new ConnectionService();

    public User registerUser(User user) {
        String sql = "insert into users (first_name, last_name, email, password) values(?,?,?,?)";
        try {
            Connection c = connect.establishConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());

            int resp = ps.executeUpdate();
            if(resp == 1) {
                System.out.println("Account Created Successfully");
            } else {
                System.out.println("Failed to create account. Please try again");
            }

        } catch (Exception ex) {

        }
        return null;
    }

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
