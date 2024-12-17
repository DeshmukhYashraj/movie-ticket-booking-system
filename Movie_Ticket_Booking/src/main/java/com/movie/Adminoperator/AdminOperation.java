package com.movie.Adminoperator;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.movie.config.LoggerApp;
import com.movie.model.Genre;
import com.movie.model.Language;
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
}
