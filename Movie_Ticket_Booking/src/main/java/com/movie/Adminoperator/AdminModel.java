package com.movie.Adminoperator;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.movie.config.LoggerApp;

public class AdminModel {

	static Scanner scn = new Scanner(System.in);
	static Logger logger = LoggerApp.getLogger();

	public static void OperationAdmin() {
		do {
			System.out.println("<<Welcome Admin>>");
			System.out.println("1 :: Add Movie Details :: ");
			System.out.println("2 :: Add Cinema Details :: ");
			int value = scn.nextInt();
			switch (value) {
			case 1: {
				AdminOperationImpl.AddMovieDetails();
				break;
			}
			case 2: {
//				AdminOperationImpl.AddCinemaDetails();
			}

			default:
				throw new IllegalArgumentException("Unexpected value: " + value);
			}
		} while (true);

	}

}
