package com.movie.Adminoperator;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.movie.config.LoggerApp;
import com.movie.model.Language;
import com.movie.service.AddMovieServiceImpl;
import com.movie.service.IAddMovieService;

public class AdminOperation {
	static Scanner scn = new Scanner(System.in);
	static IAddMovieService movieService = new AddMovieServiceImpl();
	static Logger logger = LoggerApp.getLogger();

	public static void addLanguage() {
		System.out.println("Enter Language Name :: ");
		String langName = scn.nextLine();
		Language lang = new Language(langName);
		int value = movieService.addMovieLanguage(lang);
		System.out.println(value > 0 ? "Language Added Successfully.." : "Laguage not added..");
	}

	public static void showLanguage() {
		System.out.println("Language Details Are :: ");
		List<Language> languages = movieService.getAllLanguage();
		if (languages != null) {
			languages.forEach(System.out::println);
		} else {
			logger.info("No Record Found...");
		}
	}

	public static void updateLanguage() {
		System.out.println("Enter Old Language Name :: ");
		String oldName = scn.nextLine();
		System.out.println("Enter New Language Name :: ");
		String newName = scn.nextLine();
		int value = movieService.updateMovieLanguage(oldName, newName);
		System.out.println(value > 0 ? "Language UPDATED Successfully.." : "Laguage not updated..");
	}

	public static void deleteLanguage() {
		System.out.println("Enter Language Name :: ");
		String langName = scn.nextLine();
		int value = movieService.removeMovieLanguage(new Language(langName));
		System.out.println(value > 0 ? "Language Deleted Successfully.." : "Laguage not deleted..");
	}

}
