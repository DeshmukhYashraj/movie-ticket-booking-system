package com.movie.controller;

import java.util.Scanner;


//import org.apache.log4j.Logger;
//
//import com.movie.config.LoggerApp;

public class AdminOperationImpl {
	static Scanner scn = new Scanner(System.in);
//	static Logger logger = LoggerApp.getLogger();

	public static void manageLanguage() {
		do {
			System.out.println("\n<< Please select Language operation: >>\n");
			System.out.println("1 :: Add Movie Language :: ");
			System.out.println("2 :: Show Available Movie Language :: ");
			System.out.println("2 :: Update Movie Language :: ");
			System.out.println("4 :: Delete Movie Language :: ");
			System.out.println("5 :: Exit ");
			int value = scn.nextInt();
			switch (value) {
			case 1: {
				AdminOperation.addLanguage();
				break;
			}
			case 2: {
				AdminOperation.showLanguage();
				break;
			}
			case 3: {
				AdminOperation.updateLanguage();
				break;
			}
			case 4: {
				AdminOperation.deleteLanguage();
				break;
			}
			case 5: {
				System.out.println("Exiting Language Management... Goodbye!");
				return;
			}
				
			default:
				throw new IllegalArgumentException("Unexpected value: " + value);
			}
		} while (true);
	}

	public static void manageGenres() {
		do {
			System.out.println("\n<< Please select Genre operation: >>\n");
			System.out.println("1 :: Add Movie Genre");
			System.out.println("2 :: Show Available Movie Genres");
			System.out.println("3 :: Update Movie Genre");
			System.out.println("4 :: Delete Movie Genre");
			System.out.println("5 :: Exit");

			int value = scn.nextInt();
			scn.nextLine(); // Consume the newline character

			switch (value) {
			case 1: {
				AdminOperation.addGenre();
				break;
			}
			case 2: {
				AdminOperation.showGenres();
				break;
			}
			case 3: {
				AdminOperation.updateGenre();
				break;
			}
			case 4: {
				AdminOperation.deleteGenre();
				break;
			}
			case 5: {
				System.out.println("Exiting Genre Management... Goodbye!");
				return; // Exit the loop
			}
			default:
				System.out.println("Invalid choice! Please select a valid option.");
			}
		} while (true);
	}

	public static void manageMovies() {
		do {
			System.out.println("\n<< Please select Movie operation: >>\n");
			System.out.println("1 :: Add Movie :: ");
			System.out.println("2 :: Show Available Movies :: ");
			System.out.println("3 :: Update Movie :: ");
			System.out.println("4 :: Delete Movie :: ");
			System.out.println("5 :: Exit Movie Management :: ");
			int value = scn.nextInt();
			scn.nextLine(); 

			switch (value) {
			case 1: {
				// Add Movie
				AdminOperation.addMovies();
				break;
			}
			case 2: {
				// Show Movies
				AdminOperation.showMovies();
				break;
			}
			case 3: {
				// Update Movie
				AdminOperation.updateMovie();
				break;
			}
			case 4: {
				// Delete Movie
				AdminOperation.deleteMovie();
				break;
			}
			case 5: {
				System.out.println("Exiting Movie Management... Goodbye!");
				return; // Exit
			}
			default:
				System.out.println("Invalid option. Please try again.");
			}
		} while (true);
	}
	
	public static void manageCinemas() {
	    do {
	        System.out.println("\n<< Please select Cinema operation: >>\n");
	        System.out.println("1 :: Add Cinema :: ");
	        System.out.println("2 :: Show Available Cinemas :: ");
	        System.out.println("3 :: Update Cinema :: ");
	        System.out.println("4 :: Delete Cinema :: ");
	        System.out.println("5 :: Exit Cinema Management :: ");
	        int value = scn.nextInt();
	        scn.nextLine(); // Clear buffer

	        switch (value) {
	            case 1: {
	                // Add Cinema
	                AdminOperation.addCinema();
	                break;
	            }
	            case 2: {
	                // Show Cinemas
	                AdminOperation.showCinemas();
	                break;
	            }
	            case 3: {
	                // Update Cinema
	                AdminOperation.updateCinema();
	                break;
	            }
	            case 4: {
	                // Delete Cinema
	                AdminOperation.deleteCinema();;
	                break;
	            }
	            case 5: {
	                System.out.println("Exiting Cinema Management... Goodbye!");
	                return; // Exit
	            }
	            default:
	                System.out.println("Invalid option. Please try again.");
	        }
	    } while (true);
	}

	public static void manageSeats() {
	    do {
	        System.out.println("\n<< Please select Seat operation: >>\n");
	        System.out.println("1 :: Add Seat");
	        System.out.println("2 :: Show Seats for Showtime");
	        System.out.println("3 :: Update Seat Availability");
	        System.out.println("4 :: Delete Seat");
	        System.out.println("5 :: Exit Seat Management");

	        int choice = scn.nextInt();
	        scn.nextLine(); // Clear buffer

	        switch (choice) {
	            case 1:
	            	AdminOperation.addSeat();
	                break;
	            case 2:
	            	AdminOperation.showSeats();
	                break;
	            case 3:
	            	AdminOperation.updateSeatAvailability();
	                break;
	            case 4:
	            	AdminOperation.deleteSeat();
	                break;
	            case 5:
	                System.out.println("Exiting Seat Management... Goodbye!");
	                return; // Exit
	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
	    } while (true);
	}

	public static void manageShowtimes() {
	    do {
	        System.out.println("\n<< Please select Showtime operation: >>\n");
	        System.out.println("1 :: Add Showtime");
	        System.out.println("2 :: Show All Showtimes");
	        System.out.println("3 :: Update Showtime");
	        System.out.println("4 :: Delete Showtime");
	        System.out.println("5 :: Exit Showtime Management");

	        int choice = scn.nextInt();
	        scn.nextLine(); // Clear buffer

	        switch (choice) {
	            case 1:
	                AdminOperation.addShowtime();
	                break;
	            case 2:
	                AdminOperation.showAllShowtimes();
	                break;
	            case 3:
	                AdminOperation.updateShowtime();
	                break;
	            case 4:
	                AdminOperation.deleteShowtime();
	                break;
	            case 5:
	                System.out.println("Exiting Showtime Management... Goodbye!");
	                return; // Exit
	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
	    } while (true);
	}

	public static void manageFeedback() {
		System.out.println("Welcome to feedback secsion");
		System.out.println("1 :: Add feedback");
		int value = scn.nextInt();

		switch (value) {
		case 1:
			
			break;

		default:
			break;
		}
	}
	
}
