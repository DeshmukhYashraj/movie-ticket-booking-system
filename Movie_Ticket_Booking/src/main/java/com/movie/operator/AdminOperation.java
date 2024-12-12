package com.movie.operator;

import java.util.Scanner;

import com.movie.model.Language;
import com.movie.service.AddMovieDetailsImpl;
import com.movie.service.IAddMovieService;

public class AdminOperation {
	static Scanner scn = new Scanner(System.in);
	static IAddMovieService movieService = new AddMovieDetailsImpl();

	public static void addLanguage() {
		System.out.println("Enter Language Name :: ");
		String langName = scn.nextLine();
		Language lang = new Language(langName);
		int value = movieService.addMovieLanguage(lang);
		System.out.println(value > 0 ? "Language Added Successfully.." : "Laguage not added..");
	}

}
