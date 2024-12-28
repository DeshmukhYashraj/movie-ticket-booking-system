package com.movie.operator;

import java.util.Scanner;

import com.movie.model.AdminModel;
import com.movie.service.AdminServiceImpl;
import com.movie.service.IAdminService;

public class AdminPannelOperations {
//    static Logger logger = Logger.getLogger(AdminPannelOperations.class);
    static Scanner sc = new Scanner(System.in);

    static IAdminService adminService = new AdminServiceImpl();

    public static void addAdmin() {
        try {
            System.out.print("Enter Admin Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Admin Password: ");
            String password = sc.nextLine();

            // Create AdminModel object
            AdminModel admin = new AdminModel();
            admin.setUsername(username);
            admin.setPassword(password);

            // Add Admin through service
            if (adminService.isAddNewAdmin(admin)) {
            	System.out.println("✅ Admin added successfully: " + username);
//                logger.info("✅ Admin added successfully: " + username);
                System.out.println("✅ Admin added successfully!");
                
            } else {
//                logger.warn("❌ Failed to add admin: " + username);
                System.out.println("❌ Failed to add admin. Please try again.");
            }
        } catch (Exception e) {
//            logger.error("❌ Error while adding admin", e);
            System.out.println("❌ An unexpected error occurred. Please try again later.");
        }
    }

    public static boolean adminLogin() {
        try {
            System.out.print("Enter Admin Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Admin Password: ");
            String password = sc.nextLine();

            // Check admin credentials through service
            if (adminService.adminLogin(username, password)) {
//                logger.info("✅ Admin login successful: " + username);
                System.out.println("\n\t✅ Login successful. Welcome "+username+"!");
                return true;
            } else {
//                logger.warn("❌ Admin login failed: Invalid credentials for " + username);
                System.out.println("❌ Invalid username or password. Please try again.");
                return false;
            }
        } catch (Exception e) {
//            logger.error("❌ Error during admin login", e);
            System.out.println("❌ An unexpected error occurred. Please try again later.");
            return false;
        }
    }
}
