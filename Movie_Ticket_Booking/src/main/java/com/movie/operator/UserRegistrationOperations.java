package com.movie.operator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import com.movie.model.UserModel;
import com.movie.service.IUserService;
import com.movie.service.UserServiceImpl;

public class UserRegistrationOperations {

//    static Logger logger = Logger.getLogger(UserRegistrationOperations.class);
    static Scanner sc = new Scanner(System.in);

    static IUserService userService = new UserServiceImpl();

    public static boolean registerUser() {
        boolean isSuccess = false; 
        try {
            System.out.println("Enter Username: ");
            String username = sc.nextLine();

            System.out.println("Enter Email: ");
            String email = sc.nextLine();

            System.out.println("Enter Password: ");
            String password = sc.nextLine();

            System.out.println("Enter Phone Number: ");
            String phone = sc.nextLine();

            // Automatically set the registered_date to current date
            Date registeredDate = Date.valueOf(LocalDate.now());

            // Create UserModel object
            UserModel user = new UserModel();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);
            user.setRegisteredDate(registeredDate);

            // Call service to add user
            if (userService.addNewUser(user)) {
//                logger.info("✅ User registered successfully: " + username);
                System.out.println("✅ User registered successfully!");
                isSuccess = true;  // Mark as success
            } else {
//                logger.warn("❌ User registration failed: " + username);
                System.out.println("❌ User registration failed.");
            }
        } catch (Exception e) {
//            logger.error("❌ Error during user registration", e);
            System.out.println("❌ An unexpected error occurred. Please try again later.");
        }
        return isSuccess;  // Return final status
    }
}
