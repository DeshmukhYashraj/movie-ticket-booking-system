package com.movie.client;

import org.apache.log4j.Logger;

import com.movie.config.DBState;
import com.movie.config.LoggerApp;
import com.movie.operator.AdminModel;

public class MovieTicketBookingApp extends DBState {

	static AdminModel admin = new AdminModel();
	static Logger logger = LoggerApp.getLogger();

	public static void main(String[] args) {
		admin.adminOperator();
	}
}
