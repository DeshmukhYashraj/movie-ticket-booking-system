package com.movie.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.movie.config.DBState;
import com.movie.config.LoggerApp;
import com.movie.model.Language;

public class AddMovieRepositoryImpl extends DBState implements IAddMovieRepository {

	private static final String ADD_LANGUAGE = "INSERT INTO LANGUAGES (LANGUAGE_NAME) VALUES(?);";
	private static final String SHOW_LANGUAGE = "SELECT * FROM LANGUAGES";
	private static final String DELETE_LANGUAGE = "DELETE FROM LANGUAGES WHERE LANGUAGE_NAME = ?; ";
	private static final String GET_LANGID_BY_NAME = "SELECT LANGUAGE_ID FROM LANGUAGES WHERE LANGUAGE_NAME = ?";
	private static final String UPDATE_LANGUAGE = "UPDATE LANGUAGES SET LANGUAGE_NAME = ? WHERE LANGUAGE_ID = ?;";
	Logger logger = LoggerApp.getLogger();

	@Override
	public int addMovieLanguage(Language lang) {
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

	@Override
	public List<Language> getAllLanguage() {
		List<Language> list = new ArrayList<>();
		try {
			ps = con.prepareStatement(SHOW_LANGUAGE);
			rs = ps.executeQuery();

			while (rs.next()) {
				Language lang = new Language(rs.getInt(1), rs.getString(2)); // Map data to object
				list.add(lang);
			}
			return list;
		} catch (SQLException e) {
			logger.fatal("Data Not Found... " + e.getMessage());
		}
		return list;
	}

	@Override
	public int removeMovieLanguage(Language lang) {
		int value = 0;
		try {
			ps = con.prepareStatement(DELETE_LANGUAGE);
			ps.setString(1, lang.getLangName());
			value = ps.executeUpdate();
			return value > 0 ? value : 0;
		} catch (SQLException e) {
			logger.fatal("Internal Problems..." + e.getMessage());
		}
		return value;
	}

	public int getLanguageByName(String name) {
		int value = -1;
		try {
			ps = con.prepareStatement(GET_LANGID_BY_NAME);

			ps.setString(1, name);
			rs = ps.executeQuery();

			if (rs.next()) {
				value = rs.getInt(1); // Retrieve language_id
			}
		} catch (SQLException e) {
			logger.fatal("Error while fetching language ID: " + e.getMessage());
		}
		return value;
	}

	@Override
	public int updateMovieLanguage(String oldName, String newName) {
		try {
			int value = getLanguageByName(oldName);
			ps = con.prepareStatement(UPDATE_LANGUAGE);
			ps.setString(1, newName);
			ps.setInt(2, value);
			value = ps.executeUpdate();
			return value > 0 ? value : -1;
		} catch (SQLException e) {
			logger.fatal("Internal Problems..." + e.getMessage());
		}
		return 0;
	}

}
