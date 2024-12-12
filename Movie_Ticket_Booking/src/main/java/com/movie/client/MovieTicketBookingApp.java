package com.movie.client;

import org.apache.log4j.Logger;

import com.movie.config.DBConfig;
import com.movie.config.DBState;
import com.movie.config.LoggerApp;

public class MovieTicketBookingApp extends DBState {

	Logger logger = LoggerApp.getLogger();

	public static void main(String[] args) {
		try {
			config = DBConfig.getInstance();
			con = DBConfig.getCon();
			if (con != null)
				System.out.println("connected");
			else
				System.out.println("not connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
