package com.movie.client;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.movie.controller.AdminModelOp;
import com.movie.controller.UserModelOp;
import com.movie.operator.AdminPannelOperations;
import com.movie.operator.UserLoginOperations;
import com.movie.operator.UserRegistrationOperations;

public class MovieTicketBookingApp {
	
	static Logger logger = Logger.getLogger(MovieTicketBookingApp.class);

    static {
        try {
            PropertyConfigurator.configure("D:\\workspaces\\ConsoleProject\\Movie_Ticket_Booking\\Movie_Ticket_Booking\\src\\main\\java\\com\\movie\\commons\\Log4j.properties");
            logger.info("Logger configuration successful.");
        } catch (Exception e) {
            System.err.println("Failed to configure logger: " + e.getMessage());
        }
    }

	
	// ANSI color codes 
	private static final String RESET = "\u001B[0m";
	private static final String GREEN = "\u001B[32m";
	private static final String RED = "\u001B[31m";
	private static final String CYAN = "\u001B[36m";
	private static final String YELLOW = "\u001B[33m";
	private static final String BLUE = "\u001B[34m";

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println(CYAN + "**********************************************" + RESET);
		System.out.println(CYAN + "*     🎬 WELCOME TO MOVIE TICKET BOOKING 🎬    *" + RESET);
		System.out.println(CYAN + "**********************************************" + RESET);
		System.out.println();

		// Main menu loop
		while (true) {
			System.out.println(YELLOW + "===================== MAIN MENU =====================" + RESET);
			System.out.println(GREEN + "1: Admin Panel Management");
			System.out.println("2: User Management");
			System.out.println("3: Exit" + RESET);
			System.out.println(YELLOW + "====================================================" + RESET);
			System.out.print(CYAN + "\nEnter your choice: => " + RESET);

			int choice;
			try {
				choice = sc.nextInt();
				sc.nextLine(); 
			} catch (Exception e) {
				System.out.println(RED + "\n❌ Invalid input! Please enter a valid number.\n" + RESET);
				logger.fatal("Entered invalid input in main menu");
				sc.nextLine(); 
				continue;
			}

			switch (choice) {
			case 1:
				handleAdminOperations(sc);
				break;

			case 2:
				handleUserOperations(sc);
				break;

			case 3:
				System.out.print(YELLOW + "\nAre you sure you want to exit? (yes/no): " + RESET);
				String exitChoice = sc.nextLine().trim().toLowerCase();
				if (exitChoice.equals("yes") || exitChoice.equals("y")) {
					System.out.println(GREEN + "\nThank you for using the application. Goodbye! 🎉\n" + RESET);
					sc.close();
					System.exit(0);
				} else {
					System.out.println(CYAN + "\nReturning to the main menu...\n" + RESET);
				}
				break;

			default:
				System.out.println(RED + "\n❌ Invalid Choice! Please try again.\n" + RESET);
				logger.fatal("Invalid choice in User menu");

				break;
			}
		}
	}

	// Admin operations
	private static void handleAdminOperations(Scanner sc) {
		System.out.println(YELLOW + "\n================ ADMIN LOGIN ==================" + RESET);
		if (AdminPannelOperations.adminLogin()) {
			System.out.println(GREEN + "\n🎉 Welcome to Admin Panel! 🎉\n" + RESET);
			AdminModelOp.OperationAdmin();
		} else {
			System.out.println(RED + "\n❌ Admin login failed. Invalid credentials.\n" + RESET);
		}
	}

	// User operations
	private static void handleUserOperations(Scanner sc) {
		System.out.println(YELLOW + "\n============= USER MANAGEMENT ==============" + RESET);
		System.out.println(CYAN + "1: New User Registration");
		System.out.println("2: User Login" + RESET);
		System.out.println(YELLOW + "============================================" + RESET);
		System.out.print(CYAN + "\nPlease select an option: " + RESET);

		int userChoice;
		try {
			userChoice = sc.nextInt();
			sc.nextLine(); 
		} catch (Exception e) {
			System.out.println(RED + "\n❌ Invalid input! Please enter a valid number.\n" + RESET);
			sc.nextLine(); 
			return;
		}
		
		switch (userChoice) {
		case 1:
			if (UserRegistrationOperations.registerUser()) {
				System.out.println(GREEN + "\n🎉 User registered successfully! 🎉\n" + RESET);
			} else {
				System.out.println(RED + "\n❌ User registration failed.\n" + RESET);
			}
			break;

		case 2:
			try {
				String email = UserLoginOperations.userLogin();

				if (email == null || email.equals("null")) {
					System.out.println(RED + "\n❌ User login failed. Invalid credentials. Please try again.\n" + RESET);
				} else {
					System.out.println(GREEN + "\n🎉 Welcome to the Movie Ticket Booking System! 🎉\n" + RESET);
					UserModelOp.operationUser(email);
				}
			} catch (Exception e) {
				System.out.println(
						RED + "\n❌ An unexpected error occurred during user login. Please try again.\n" + RESET);
			}
			break;

		default:
			System.out.println(RED + "\n❌ Invalid choice. Please try again.\n" + RESET);
			break;
		}
	}
}
