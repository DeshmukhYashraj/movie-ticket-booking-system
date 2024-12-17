package com.movie.Adminclient;

import org.apache.log4j.Logger;

import com.movie.Adminoperator.AdminModel;
import com.movie.config.LoggerApp;

public class MovieTicketBookingApp {

	static Logger logger = LoggerApp.getLogger();

	public static void main(String[] args) {
		logger.debug("main method");
		AdminModel.OperationAdmin();

	}
}
