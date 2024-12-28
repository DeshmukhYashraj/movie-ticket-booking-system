package com.movie.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.util.Scanner;

import com.movie.service.FeedbackServiceImpl;
import com.movie.service.IFeedbackService;

public class UserModelOp {

    static Scanner scn = new Scanner(System.in);

    static Logger logger = Logger.getLogger(UserModelOp.class);

    static {
        try {
            PropertyConfigurator.configure("D:\\workspaces\\ConsoleProject\\Movie_Ticket_Booking\\Movie_Ticket_Booking\\src\\main\\java\\com\\movie\\commons\\Log4j.properties");
            logger.info("Logger configuration successful.");
        } catch (Exception e) {
            System.err.println("Failed to configure logger: " + e.getMessage());
        }
    }
    // ANSI escape codes for colors and styles
    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String GREEN = "\033[32m";
    private static final String CYAN = "\033[36m";
    private static final String YELLOW = "\033[33m";
    private static final String BLUE = "\033[34m";
    private static final String RED = "\033[31m";
    private static final String UNDERLINE = "\033[4m";

    public static void operationUser(String email) {
        // Design with borders and formatting
        System.out.println(GREEN + BOLD + "══════════════════════════════════" + RESET);
        System.out.println(YELLOW + BOLD + "       TOP RATED MOVIES         " + RESET);
        System.out.println(GREEN + "══════════════════════════════════" + RESET);
        UserOperation.getAllTopRatedMovies();
        System.out.println(GREEN + "==================================" + RESET);

        System.out.println("\n" + GREEN + "══════════════════════════════════" + RESET);
        System.out.println(YELLOW + BOLD + "    UPCOMING MOVIES:              " + RESET);
        System.out.println(GREEN + "══════════════════════════════════" + RESET);
        UserOperation.getUpcommingMovies();
        System.out.println(GREEN + "==================================" + RESET);

        // Menu for user choices
        do {
            System.out.println(CYAN + "\n* Select an option *" + RESET);
            System.out.println(CYAN + "1 :: Search by Language" + RESET);
            System.out.println(CYAN + "2 :: Search by Genre" + RESET);
            System.out.println(CYAN + "3 :: Exit" + RESET);
            System.out.print(YELLOW + "Enter your choice: " + RESET);
            

            int value = scn.nextInt();
            switch (value) {

                case 1:
                    System.out.println(BLUE + "Searching by Language..." + RESET);
                    UserOperation.finderMovieBylanguage(email);
                    break;
                case 2:
                    System.out.println(BLUE + "Searching by Genre..." + RESET);
                    UserOperation.finderMovieByGenre(email);
                    break;
                case 3:
                    System.out.println(RED + "Exiting. Thank you!" + RESET);
                    return;
                default:
                    System.out.println(RED + "Invalid choice, please try again!" + RESET);
                    logger.fatal("Invalid user choice.");
                    break;
            }

        } while (true);
    }
}
