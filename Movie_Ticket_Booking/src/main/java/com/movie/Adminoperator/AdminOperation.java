package com.movie.Adminoperator;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.movie.config.LoggerApp;
import com.movie.model.Genre;
import com.movie.model.Language;
import com.movie.model.Movies;
import com.movie.service.AddMovieServiceImpl;
import com.movie.service.IAddMovieService;

public class AdminOperation {
	static Scanner scn = new Scanner(System.in);
	static IAddMovieService movieService = new AddMovieServiceImpl();
	static Logger logger = LoggerApp.getLogger();

	// Method for adding a new language
	public static void addLanguage() {
		System.out.println("Enter Language Name :: ");
		String langName = scn.nextLine();
		Language lang = new Language(langName);
		int value = movieService.addMovieLanguage(lang);
		System.out.println(value > 0 ? "Language Added Successfully.." : "Language not added..");
	}

	// Method for displaying all languages
	public static void showLanguage() {
		System.out.println("Language Details Are :: ");
		List<Language> languages = movieService.getAllLanguage();
		if (languages != null) {
			languages.forEach(System.out::println);
		} else {
			logger.info("No Record Found...");
		}
	}

	// Method for updating an existing language
	public static void updateLanguage() {
		System.out.println("Enter Old Language Name :: ");
		String oldName = scn.nextLine();
		System.out.println("Enter New Language Name :: ");
		String newName = scn.nextLine();
		int value = movieService.updateMovieLanguage(oldName, newName);
		System.out.println(value > 0 ? "Language UPDATED Successfully.." : "Language not updated..");
	}

	// Method for deleting a language
	public static void deleteLanguage() {
		System.out.println("Enter Language Name :: ");
		String langName = scn.nextLine();
		int value = movieService.removeMovieLanguage(new Language(langName));
		System.out.println(value > 0 ? "Language Deleted Successfully.." : "Language not deleted..");
	}

	// Method for adding a new genre
	public static void addGenre() {
		System.out.println("Enter Genre Name :: ");
		String genreName = scn.nextLine();
		Genre genre = new Genre(genreName); // Assuming Genre constructor is similar to Language
		int value = movieService.addMovieGenre(genre);
		System.out.println(value > 0 ? "Genre Added Successfully.." : "Genre not added..");
	}

	// Method for displaying all genres
	public static void showGenres() {
		System.out.println("Genre Details Are :: ");
		List<Genre> genres = movieService.getAllGenres(); // Assuming method exists in service
		if (genres != null) {
			genres.forEach(System.out::println);
		} else {
			logger.info("No Genre Found...");
		}
	}

	// Method for updating an existing genre
	public static void updateGenre() {
		System.out.println("Enter Old Genre Name :: ");
		String oldName = scn.nextLine();
		System.out.println("Enter New Genre Name :: ");
		String newName = scn.nextLine();
		int value = movieService.updateMovieGenre(oldName, newName);
		System.out.println(value > 0 ? "Genre UPDATED Successfully.." : "Genre not updated..");
	}

	// Method for deleting a genre
	public static void deleteGenre() {
		System.out.println("Enter Genre Name :: ");
		String genreName = scn.nextLine();
		int value = movieService.removeMovieGenre(new Genre(genreName));
		System.out.println(value > 0 ? "Genre Deleted Successfully.." : "Genre not deleted..");
	}

	public static void addMovies() {
		// Taking movie title input
		System.out.println("Enter Movie Title: ");
		String title = scn.nextLine();

		System.out.println("Enter Movie Duration (hh:mm:ss): ");
		String duration = scn.nextLine();
		Time timeDuration = Time.valueOf(duration); // Convert String to Time

		System.out.println("Enter Language Name: ");
		String languageName = scn.nextLine();
		Language lang = new Language(languageName); // Create a Language object using the name

		System.out.println("Enter Release Date (YYYY-MM-DD): ");
		String releaseDate = scn.nextLine();
		Date dateRelease = Date.valueOf(releaseDate); // Convert String to Date

		System.out.println("Enter Genre Name: ");
		String genreName = scn.nextLine();
		Genre gen = new Genre(genreName); // Create a Genre object using the name

		Movies movie = new Movies(0, title, timeDuration, lang, dateRelease, gen);

		int value = movieService.addMovie(movie);

		System.out.println(value > 0 ? "Movie Added Successfully!" : "Movie could not be added.");
	}

	public static void showMovies() {
		System.out.println("Movie Details: ");
		List<Movies> movies = movieService.getAllMovies(); // Fetches all movies from the database
		if (movies != null && !movies.isEmpty()) {
			movies.forEach(System.out::println); // Print each movie object using toString()
		} else {
			logger.info("No Movies Found.");
		}
	}

	public static void updateMovie() {

		System.out.println("Enter Movie ID to Update: ");
		int movieId = scn.nextInt();
		scn.nextLine(); // Consume the newline character

		System.out.println("Enter New Movie Title: ");
		String newTitle = scn.nextLine();

		System.out.println("Enter New Movie Duration (hh:mm:ss): ");
		String newDuration = scn.nextLine();
		Time duration = Time.valueOf(newDuration); // Convert String to Time

		// Accept new Language name instead of language ID
		System.out.println("Enter New Language Name: ");
		String newLanguageName = scn.nextLine(); // Accept language name as input
		Language lang = new Language(newLanguageName); // Create a Language object using the name

		System.out.println("Enter New Release Date (YYYY-MM-DD): ");
		String newReleaseDate = scn.nextLine();
		Date releaseDate = Date.valueOf(newReleaseDate); // Convert String to Date

		// Accept new Genre name instead of genre ID
		System.out.println("Enter New Genre Name: ");
		String newGenreName = scn.nextLine(); // Accept genre name as input
		Genre genre = new Genre(newGenreName); // Create a Genre object using the name

		// Create the updated Movie object with the new data
		Movies updatedMovie = new Movies(movieId, newTitle, duration, lang, releaseDate, genre);

		// Assuming the update method in movieService will handle the DB update
		int value = movieService.updateMovie(updatedMovie);

		// Print success/failure message based on the result of the update operation
		System.out.println(value > 0 ? "Movie Updated Successfully!" : "Movie could not be updated.");
	}

	public static void deleteMovie() {
		System.out.println("Enter Movie ID to Delete: ");
		int movieId = scn.nextInt();

		// Assuming delete method in movieService
		int value = movieService.removeMovie(movieId); // This method should handle DB delete

		System.out.println(value > 0 ? "Movie Deleted Successfully!" : "Movie could not be deleted.");
	}

}
