package com.movie.controller;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.movie.client.MovieTicketBookingApp;

public class AdminModelOp {

	static Scanner scn = new Scanner(System.in);

	static Logger logger = Logger.getLogger(MovieTicketBookingApp.class);

    static {
        try {
            PropertyConfigurator.configure("D:\\workspaces\\ConsoleProject\\Movie_Ticket_Booking\\Movie_Ticket_Booking\\src\\main\\java\\com\\movie\\commons\\Log4j.properties");
            logger.info("Logger configuration successful.");
        } catch (Exception e) {
            System.err.println("Failed to configure logger: " + e.getMessage());
        }
    }
	
	
	public static void OperationAdmin() {
		do {
			System.out.println("<<  Please enter your choice:  >>\n");
			System.out.println("1 :: Add Movie Language       :: ");
			System.out.println("2 :: Add Movie Genre          :: ");
			System.out.println("3 :: Add Movie Details        :: ");
			System.out.println("4 :: Add Cinema Details       :: ");
			System.out.println("5 :: Add Showtime Cinema Wise :: ");
			System.out.println("6 :: Add Seat Details         :: ");
			System.out.println("7 :: Add Booking Details      :: ");
			System.out.println("8 :: Edit feedback details    :: ");
			System.out.println("9 :: Exit");
			int value = scn.nextInt();
			switch (value) {
			case 1:
				AdminOperationImpl.manageLanguage();
				break;
				
			case 2:
				AdminOperationImpl.manageGenres();
				break;
				
			case 3:
				AdminOperationImpl.manageMovies();;
				break;

			case 4:
				AdminOperationImpl.manageCinemas();;
				break;

			case 5:
				AdminOperationImpl.manageShowtimes();;
				break;
			case 6:
				AdminOperationImpl.manageSeats();
				break;
			case 7:
				
				break;
			case 8:
				AdminOperationImpl.manageFeedback();
				break;
			case 9:
				System.out.println("Exiting Genre Management... Goodbye!");
				logger.info("Exiting Genre Management");
				return;

			default:
				throw new IllegalArgumentException("Unexpected value: " + value);
			}
		} while (true);

	}

}
