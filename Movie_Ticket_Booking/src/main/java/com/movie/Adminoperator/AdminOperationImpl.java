package com.movie.Adminoperator;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.movie.config.LoggerApp;

public class AdminOperationImpl {
	static Scanner scn = new Scanner(System.in);
	static Logger logger = LoggerApp.getLogger();

	public static void AddMovieDetails() {
		do {
			System.out.println("<<Welcome Admin>>");
			System.out.println("1 :: Movie Language :: ");
			System.out.println("2 :: Movie Genre :: ");
			System.out.println("3 :: Movie Details :: ");
			System.out.println("4 :: Exit");
			int value = scn.nextInt();
			switch (value) {
			case 1: {
				AdminOperationImpl.manageLanguage();
				break;
			}
			case 2: {
				AdminOperationImpl.manageGenres();
				break;
			}
			case 3: {
				AdminOperationImpl.manageMovies();
				break;
			}
			case 4: {
				System.out.println("Exiting Movies Management... Goodbye!");
				return;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + value);
			}
		} while (true);

	}

	public static void manageLanguage() {
		do {
			System.out.println("<<Welcome Admin>>");
			System.out.println("1 :: Add Movie Language :: ");
			System.out.println("2 :: Show Available Movie Language :: ");
			System.out.println("2 :: Update Movie Language :: ");
			System.out.println("4 :: Delete Movie Language :: ");
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
			System.out.println("<< Welcome Admin >>");
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
			System.out.println("<<Welcome Admin>>");
			System.out.println("1 :: Add Movie :: ");
			System.out.println("2 :: Show Available Movies :: ");
			System.out.println("3 :: Update Movie :: ");
			System.out.println("4 :: Delete Movie :: ");
			System.out.println("5 :: Exit Movie Management :: ");
			int value = scn.nextInt();
			scn.nextLine(); // consume the newline character after nextInt

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
}
