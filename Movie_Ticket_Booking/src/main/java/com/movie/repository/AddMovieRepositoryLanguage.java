package com.movie.repository;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.movie.config.DBConfig;
import com.movie.config.DBState;
import com.movie.config.LoggerApp;
import com.movie.model.Language;

public class AddMovieRepositoryLanguage extends DBState implements IAddMovieRepository {

	private static final String ADD_LANGUAGE = "INSERT INTO LANGUAGES (LANGUAGE_NAME) VALUES(?);";
	Logger logger = LoggerApp.getLogger();

	@Override
	public int addMovieLanguage(Language lang) {
		con = DBConfig.getCon();
		int value = 0;
		try {
			ps = con.prepareStatement(ADD_LANGUAGE);
			ps.setString(1, lang.getLangName());
			value = ps.executeUpdate();
			return value > 0 ? value : 0;
		} catch (SQLException e) {
			logger.fatal("Internal Problems..." + e.getMessage());
		}
		return value;
	}

}
