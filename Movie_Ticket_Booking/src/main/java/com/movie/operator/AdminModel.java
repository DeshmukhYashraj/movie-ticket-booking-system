package com.movie.operator;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.movie.config.LoggerApp;
import com.movie.model.Language;

public class AdminModel {

	static Logger logger = LoggerApp.getLogger();

	public void adminOperator() {
		Scanner scn = new Scanner(System.in);
		do {
			System.out.println("<<Welcome Admin>>");
			System.out.println("1 :: Add Movie Language :: ");
			int value = scn.nextInt();
			switch (value) {
			case 1: {
				System.out.println("Enter Language Name :: ");
				String langName = scn.nextLine();
				Language lang = new Language(langName);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + value);
			}
		} while (true);

	}

}
