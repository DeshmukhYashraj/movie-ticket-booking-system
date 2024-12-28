package com.movie.operator;

import java.util.Scanner;

import com.movie.service.IUserService;
import com.movie.service.UserServiceImpl;

public class UserLoginOperations {

//    static Logger logger = Logger.getLogger(UserLoginOperations.class);
    static Scanner sc = new Scanner(System.in);

    private static IUserService userService = new UserServiceImpl();

    public static String userLogin() {
    	String userEmail=null;
        try {
            System.out.print("Enter Email: "); // Ask for email instead of username
            String email = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            if (userService.validateUserLogin(email, password)) {
//                logger.info("✅ User login successful: " + email);
            	// Get user ID by email (assuming this method exists in the service)
                int userId = userService.getUserIdByEmail(email);
                
                // Set the logged-in user's ID in the session
//                UserSession.setLoggedInUserId(userId);
                System.out.println("\n\t✅ Login successful! Welcome back.");
                return email;
            } else {
//                logger.warn("❌ User login failed: Invalid credentials for " + email);
//                System.out.println("❌ Invalid email or password. Please try again.");
                return null;
            }
        } catch (Exception e) {
//            logger.error("❌ Error during user login", e);
            System.out.println("❌ An unexpected error occurred. Please try again later.");
            return null;
        }
    }
    
    public static void userLogout() {
        // Log out the user and reset the session
        UserSession.logOut();
        System.out.println("You have successfully logged out.");
    }

}
