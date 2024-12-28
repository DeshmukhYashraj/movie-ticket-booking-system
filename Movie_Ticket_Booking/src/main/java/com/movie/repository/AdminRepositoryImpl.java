package com.movie.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.movie.config.DBState;
import com.movie.model.AdminModel;

public class AdminRepositoryImpl extends DBState implements IAdminRepository {

//    static Logger logger = Logger.getLogger(AdminRepositoryImpl.class);

    @Override
    public boolean isAddNewAdmin(AdminModel admin) {
        String query = "INSERT INTO admin (username, password) VALUES (?, ?)";
        try  {
        	ps = con.prepareStatement(query);
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
            	System.out.println("✅ Admin successfully added: " + admin.getUsername());
//                logger.info("✅ Admin successfully added: " + admin.getUsername());
                return true;
            }
        } catch (SQLException e) {
        	System.out.println("❌ Error while adding a new admin.");
//            logger.error("❌ Error while adding a new admin.", e);
        }
        return false;
    }

    @Override
    public boolean adminLogin(String username, String password) {
        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try  {
        	ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // If a record exists, login is successful
//            	System.out.println("✅ Admin login successful for username: " + username);
//                logger.info("✅ Admin login successful for username: " + username);
                return true;
            } else {
            	System.out.println("⚠️ Admin login failed for username: " + username);
//                logger.warn("⚠️ Admin login failed for username: " + username);
            }
        } catch (SQLException e) {
        	System.out.println("❌ Error while checking admin login credentials.");
//            logger.error("❌ Error while checking admin login credentials.", e);
        }
        return false;
    }
}
