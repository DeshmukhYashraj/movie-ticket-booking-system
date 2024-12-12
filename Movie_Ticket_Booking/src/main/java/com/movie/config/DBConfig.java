package com.movie.config;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DBConfig {
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static DBConfig db = null;

	private DBConfig() {
		try {
			Properties prop = new Properties();
			File f = new File("src/main/java/com/movie/commons/application.properties");
			FileInputStream fis = new FileInputStream(f);
			prop.load(fis);
//			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
//			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println("Error is : " + e);
		}
	}

	public static DBConfig getInstance() {
		if (db == null) {
			new DBConfig();
		}
		return db;
	}

	public static Connection getCon() {
		return con;
	}

	public static PreparedStatement getStatement() {
		return ps;
	}

	public static ResultSet getResult() {
		return rs;
	}
}