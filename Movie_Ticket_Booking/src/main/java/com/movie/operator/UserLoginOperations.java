package com.movie.operator;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.movie.service.IUserService;
import com.movie.service.UserServiceImpl;

public class UserLoginOperations {

//    static Logger logger = Logger.getLogger(UserLoginOperations.class);
    static Scanner sc = new Scanner(System.in);

    private static IUserService userService = new UserServiceImpl();

    public static boolean userLogin() {
        try {
            System.out.print("Enter Email: "); // Ask for email instead of username
            String email = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            if (userService.validateUserLogin(email, password)) {
//                logger.info("✅ User login successful: " + email);
                System.out.println("✅ Login successful! Welcome back.");
                return true;
            } else {
//                logger.warn("❌ User login failed: Invalid credentials for " + email);
                System.out.println("❌ Invalid email or password. Please try again.");
                return false;
            }
        } catch (Exception e) {
//            logger.error("❌ Error during user login", e);
            System.out.println("❌ An unexpected error occurred. Please try again later.");
            return false;
        }
    }

}
