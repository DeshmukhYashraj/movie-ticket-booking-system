package com.movie.operator;

import java.util.Scanner;

import com.movie.model.Language;

public class AdminOperation {
	static Scanner scn = new Scanner(System.in);

	public static void addLanguage() {
		System.out.println("Enter Language Name :: ");
		String langName = scn.nextLine();
		Language lang = new Language(langName);
		
	}

}
