package com.movie.operator;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.movie.config.LoggerApp;

public class AdminModel {

	static Scanner scn = new Scanner(System.in);
	static Logger logger = LoggerApp.getLogger();

	public static void OperationAdmin() {
		do {
			System.out.println("<<Welcome Admin>>");
			System.out.println("1 :: Add Movie Language :: ");
			int value = scn.nextInt();
			switch (value) {
			case 1: {
				AdminOperation.addLanguage();
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + value);
			}
		} while (true);

	}

}
