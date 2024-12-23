package com.movie.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.movie.config.DBState;
import com.movie.config.LoggerApp;
import com.movie.model.Genre;
import com.movie.model.Language;
import com.movie.model.Movies;

public class AddMovieRepositoryImpl extends DBState implements IAddMovieRepository {

	// Language-related SQL queries
	private static final String ADD_LANGUAGE = "INSERT INTO LANGUAGES (LANGUAGE_NAME) VALUES(?);";
	private static final String SHOW_LANGUAGE = "SELECT * FROM LANGUAGES";
	private static final String DELETE_LANGUAGE = "DELETE FROM LANGUAGES WHERE LANGUAGE_NAME = ?; ";
	private static final String GET_LANGID_BY_NAME = "SELECT LANGUAGE_ID FROM LANGUAGES WHERE LANGUAGE_NAME = ?";
	private static final String UPDATE_LANGUAGE = "UPDATE LANGUAGES SET LANGUAGE_NAME = ? WHERE LANGUAGE_ID = ?;";

	// Genre-related SQL queries
	private static final String ADD_GENRE = "INSERT INTO GENRES (GENRE_NAME) VALUES (?);";
	private static final String SHOW_GENRES = "SELECT * FROM GENRES";
	private static final String DELETE_GENRE = "DELETE FROM GENRES WHERE GENRE_NAME = ?;";
	private static final String GET_GENREID_BY_NAME = "SELECT GENRE_ID FROM GENRES WHERE GENRE_NAME = ?";
	private static final String UPDATE_GENRE = "UPDATE GENRES SET GENRE_NAME = ? WHERE GENRE_ID = ?;";

	// Genre-related SQL queries
	private static final String ADD_MOVIE = "INSERT INTO MOVIES (TITLE, DURATION, LANGUAGE_ID, RELEASE_DATE, GENRE_ID) "
			+ "VALUES (?, ?, (SELECT language_id FROM languages WHERE language_name = ?), ?, "
			+ "(SELECT genre_id FROM genres WHERE genre_name = ?));";

	private static final String SHOW_MOVIES = "SELECT m.movie_id, m.title, m.duration, m.language_id, l.language_name, m.release_date, m.genre_id, g.genre_name "
			+ "FROM movies m " + "JOIN languages l ON m.language_id = l.language_id "
			+ "JOIN genres g ON m.genre_id = g.genre_id";
	private static final String UPDATE_MOVIE = "UPDATE MOVIES SET TITLE = ?, DURATION = ?, LANGUAGE_ID = ?, RELEASE_DATE = ?, GENRE_ID = ? WHERE MOVIE_ID = ?";
	private static final String DELETE_MOVIE = "DELETE FROM MOVIES WHERE MOVIE_ID = ?";

	Logger logger = LoggerApp.getLogger();

	// Language-related methods
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

	// Genre-related methods
	@Override
	public int addMovieGenre(Genre genre) {
		int value = 0;
		try {
			ps = con.prepareStatement(ADD_GENRE);
			ps.setString(1, genre.getGenreName());
			value = ps.executeUpdate();
			return value > 0 ? value : 0;
		} catch (SQLException e) {
			logger.fatal("Internal Problems..." + e.getMessage());
		}
		return value;
	}

	@Override
	public List<Genre> getAllGenres() {
		List<Genre> genres = new ArrayList<>();
		try {
			ps = con.prepareStatement(SHOW_GENRES);
			rs = ps.executeQuery();
			while (rs.next()) {
				Genre genre = new Genre(rs.getInt(1), rs.getString(2)); // Map data to object
				genres.add(genre);
			}
			return genres;
		} catch (SQLException e) {
			logger.fatal("Error fetching genres: " + e.getMessage());
		}
		return genres;
	}

	@Override
	public int updateMovieGenre(String oldName, String newName) {
		int genreId = getGenreByName(oldName);
		if (genreId != -1) {
			try {
				ps = con.prepareStatement(UPDATE_GENRE);
				ps.setString(1, newName);
				ps.setInt(2, genreId);
				int value = ps.executeUpdate();
				return value > 0 ? value : -1;
			} catch (SQLException e) {
				logger.fatal("Error updating genre: " + e.getMessage());
			}
		}
		return 0;
	}

	@Override
	public int removeMovieGenre(Genre genre) {
		int value = 0;
		try {
			ps = con.prepareStatement(DELETE_GENRE);
			ps.setString(1, genre.getGenreName());
			value = ps.executeUpdate();
			return value > 0 ? value : 0;
		} catch (SQLException e) {
			logger.fatal("Error removing genre: " + e.getMessage());
		}
		return value;
	}

	// Helper method to fetch genre ID by name
	public int getGenreByName(String name) {
		int value = -1;
		try {
			ps = con.prepareStatement(GET_GENREID_BY_NAME);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				value = rs.getInt(1); // Retrieve genre_id
			}
		} catch (SQLException e) {
			logger.fatal("Error while fetching genre ID: " + e.getMessage());
		}
		return value;
	}

	@Override
	public int addMovie(Movies movie) {
		try {

			ps = con.prepareStatement(ADD_MOVIE);

			ps.setString(1, movie.getTitle()); // Set the title
			ps.setTime(2, movie.getDuration()); // Set the duration
			ps.setString(3, movie.getLanguage()); // Set the language name
			ps.setDate(4, movie.getReleaseDate()); // Set the release date
			ps.setString(5, movie.getGenreName()); // Set the genre name

			int result = ps.executeUpdate();
			return result > 0 ? result : -1; // Return the number of affected rows, or -1 if no rows were affected
		} catch (SQLException e) {
			logger.fatal("Error adding movie: " + e.getMessage());
		}
		return 0; // Return 0 if an error occurs
	}

	@Override
	public List<Movies> getAllMovies() {
		List<Movies> movies = new ArrayList<>();
		try {

			ps = con.prepareStatement(SHOW_MOVIES);
			rs = ps.executeQuery();

			while (rs.next()) {
				Language language = new Language(rs.getInt("language_id"), rs.getString("language_name"));
				Genre genre = new Genre(rs.getInt("genre_id"), rs.getString("genre_name"));

				Movies movie = new Movies(rs.getInt("movie_id"), // movie_id
						rs.getString("title"), // title
						rs.getTime("duration"), // duration
						language, // Language object
						rs.getDate("release_date"), // release_date
						genre // Genre object
				);

				movies.add(movie);
			}
			return movies;
		} catch (SQLException e) {
			logger.fatal("Error fetching movies: " + e.getMessage());
		}
		return movies; // Return empty list if an error occurs
	}

	/*@Override
	public int updateMovie(Movies updatedMovie) {
		try {
			ps = con.prepareStatement(UPDATE_MOVIE);
	
			ps.setString(1, updatedMovie.getTitle()); // Set the title
			ps.setTime(2, updatedMovie.getDuration()); // Set the duration
			ps.setString(3, updatedMovie.getLanguage()); // Set the language name
			ps.setDate(4, updatedMovie.getReleaseDate()); // Set the release date
			ps.setString(5, updatedMovie.getGenreName()); // Set the genre name
			ps.setInt(6, updatedMovie.getMovieId()); // Set the movie ID to update
	
			int result = ps.executeUpdate();
			return result > 0 ? result : -1;
		} catch (SQLException e) {
			logger.fatal("Error updating movie: " + e.getMessage());
		}
		return 0; // Return 0 if an error occurs
	}*/

	@Override
	public int updateMovie(Movies updatedMovie) {
		try {
			// Retrieve language_id based on the language name
			String languageName = updatedMovie.getLanguage(); // Get language name from movie object
			int languageId = getLanguageIdByName(languageName); // Fetch language_id from the database

			// Retrieve genre_id based on the genre name
			String genreName = updatedMovie.getGenreName(); // Get genre name from movie object
			int genreId = getGenreIdByName(genreName); // Fetch genre_id from the database

			// Prepare the update SQL query
			String query = "UPDATE movies SET title = ?, duration = ?, language_id = ?, release_date = ?, genre_id = ? WHERE movie_id = ?";

			ps = con.prepareStatement(query);
			ps.setString(1, updatedMovie.getTitle()); // Set title
			ps.setTime(2, updatedMovie.getDuration()); // Set duration
			ps.setInt(3, languageId); // Set language_id (from retrieved languageId)
			ps.setDate(4, updatedMovie.getReleaseDate()); // Set release date
			ps.setInt(5, genreId); // Set genre_id (from retrieved genreId)
			ps.setInt(6, updatedMovie.getMovieId()); // Set movie_id for updating the specific movie

			// Execute the update query
			int result = ps.executeUpdate();
			return result > 0 ? result : -1; // Return number of rows affected (or -1 if no update occurred)
		} catch (SQLException e) {
			logger.fatal("Error updating movie: " + e.getMessage());
		}
		return 0; // Return 0 if an error occurs
	}

	// Helper method to get language_id based on language_name
	private int getLanguageIdByName(String languageName) {
		try {
			String query = "SELECT language_id FROM languages WHERE language_name = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, languageName); // Set the language name in the query
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("language_id"); // Return the corresponding language_id
			}
		} catch (SQLException e) {
			logger.fatal("Error fetching language_id for language_name: " + e.getMessage());
		}
		return 0; // Return 0 if no matching language is found
	}

	// Helper method to get genre_id based on genre_name
	private int getGenreIdByName(String genreName) {
		try {
			String query = "SELECT genre_id FROM genres WHERE genre_name = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, genreName); // Set the genre name in the query
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("genre_id"); // Return the corresponding genre_id
			}
		} catch (SQLException e) {
			logger.fatal("Error fetching genre_id for genre_name: " + e.getMessage());
		}
		return 0; // Return 0 if no matching genre is found
	}

	@Override
	public int removeMovie(int movieId) {
		try {
			ps = con.prepareStatement(DELETE_MOVIE);
			ps.setInt(1, movieId);

			int result = ps.executeUpdate();
			return result > 0 ? result : 0; // Return the number of affected rows
		} catch (SQLException e) {
			logger.fatal("Error removing movie: " + e.getMessage());
		}
		return 0;
	}

}
