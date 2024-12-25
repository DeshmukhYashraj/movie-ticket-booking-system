package com.movie.repository;

import java.sql.*;

import com.movie.config.DBState;
import com.movie.model.UserModel;

public class UserRepositoryImpl extends DBState implements IUserRepository {

    // Method to add a new user to the database
    @Override
    public boolean addUser(UserModel user) {
        String query = "INSERT INTO user (username, password, email, phone, registered_date) VALUES (?, ?, ?, ?, ?)";
        try {
        	ps = con.prepareStatement(query);
        	 ps.setString(1, user.getUsername());          // username
             ps.setString(2, user.getPassword());          // password
             ps.setString(3, user.getEmail());             // email
             ps.setString(4, user.getPhone());             // phone
             ps.setDate(5, user.getRegisteredDate());      // registered_date

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;  // Returns true if the insert was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to validate user login credentials
    @Override
    public boolean validateUserLogin(String email, String password) {
        String query = "SELECT * FROM user WHERE email = ? AND password = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // If a row is returned, credentials are correct
                return true;
            } else {
                return false; // Invalid credentials
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
