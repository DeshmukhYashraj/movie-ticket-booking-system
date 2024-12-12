package com.movie.client;

import org.apache.log4j.Logger;

import com.movie.config.LoggerApp;
import com.movie.operator.AdminModel;

public class MovieTicketBookingApp {

	static Logger logger = LoggerApp.getLogger();

	public static void main(String[] args) {
		logger.debug("main method");
		AdminModel.OperationAdmin();
		System.out.println("CHange  zalay ka bg");
		System.out.println("jhal jhal jhal");
	}
}
