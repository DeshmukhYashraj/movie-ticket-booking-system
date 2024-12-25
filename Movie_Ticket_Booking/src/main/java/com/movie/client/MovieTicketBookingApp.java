package com.movie.client;

import java.util.Scanner;

//import org.apache.log4j.Logger;

import com.movie.Adminoperator.AdminModelOp;
//import com.movie.config.LoggerApp;
import com.movie.operator.AdminPannelOperations;
import com.movie.operator.UserLoginOperations;
import com.movie.operator.UserRegistrationOperations;

public class MovieTicketBookingApp {

//	static Logger logger = LoggerApp.getLogger();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

//		logger.debug("Main method initiated");

		System.out.println("🎬 ----WELCOME TO MOVIE TICKET BOOKING---- 🎬");
		System.out.println("\n____________________________________________\n");
		// Main menu loop
		while (true) {
			System.out.println("1: Admin Panel Management");
			System.out.println("2: User Management");
			System.out.println("3: Exit");
			System.out.println("\n<= Enter your choice: =>\n");

			int choice = sc.nextInt();
			sc.nextLine(); 

			switch (choice) {
			case 1: // Admin Login
				AdminModelOp.OperationAdmin();
//				if (AdminPannelOperations.adminLogin()) {
//					System.out.println("\n🎉 Welcome to Admin Panel! 🎉\n");
//					AdminModelOp.OperationAdmin();
//				} else {
//					System.out.println("❌ Admin login failed. Invalid credentials.");
//				}
				break;

			case 2: // User Management (Registration or Login)
				System.out.println("\nSelect an option:");
				System.out.println("1: New User Registration");
				System.out.println("2: User Login");

				int userChoice = sc.nextInt();
				sc.nextLine(); 

				switch (userChoice) {
				case 1: // New User Registration
					if (UserRegistrationOperations.registerUser()) {
						System.out.println("🎉 User registered successfully! 🎉");
					} else {
						System.out.println("❌ User registration failed.");
					}
					break;

				case 2: // User Login
					if (UserLoginOperations.userLogin()) {
						System.out.println("\n🎉 Welcome to the Movie Ticket Booking System! 🎉\n");
					} else {
						System.out.println("❌ User login failed. Invalid credentials.");

					}
					break;

				default:
					System.out.println("❌ Invalid choice. Please try again.");
					break;
				}
				break;

			case 3: // Exit
				System.out.println("\nThank you for using the application. Goodbye!");
//				logger.info("Application exited.");
				System.exit(0);
				break;

			default:
				System.out.println("\n❌ Invalid Choice! Try Again.");
//				logger.warn("Invalid menu choice entered.");
				break;
			}
		}
	}
}
