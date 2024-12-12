package com.movie.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBState {
	protected static DBConfig config = DBConfig.getInstance();
	protected static Connection con = config.getCon();
	protected static PreparedStatement ps = config.getStatement();
	protected static ResultSet rs = config.getResult();
}