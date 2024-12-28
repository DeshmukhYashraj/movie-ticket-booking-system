package com.movie.repository;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;

import com.movie.config.DBState;
//import com.movie.config.LoggerApp;
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

	// Movie-related SQL queries
	private static final String ADD_MOVIE = "INSERT INTO MOVIES (TITLE, DURATION, LANGUAGE_ID, RELEASE_DATE, GENRE_ID) "
			+ "VALUES (?, ?, (SELECT language_id FROM languages WHERE language_name = ?), ?, "
			+ "(SELECT genre_id FROM genres WHERE genre_name = ?));";

	private static final String SHOW_MOVIES = "SELECT m.movie_id, m.title, m.duration, m.language_id, l.language_name, m.release_date, m.genre_id, g.genre_name "
			+ "FROM movies m JOIN languages l ON m.language_id = l.language_id "
			+ "JOIN genres g ON m.genre_id = g.genre_id";
	private static final String UPDATE_MOVIE = "UPDATE MOVIES SET TITLE = ?, DURATION = ?, LANGUAGE_ID = ?, RELEASE_DATE = ?, GENRE_ID = ? WHERE MOVIE_ID = ?";
	private static final String DELETE_MOVIE = "DELETE FROM MOVIES WHERE MOVIE_ID = ?";
	private static final String GET_MOVIE_BY_ID = "SELECT m.movie_id, m.title, m.duration, l.language_id, l.language_name, m.release_date, g.genre_id,"
			+ "g.genre_name FROM movies m JOIN languages l ON m.language_id = l.language_id JOIN genres g ON m.genre_id = g.genre_id WHERE m.movie_id = ?";
	private static final String GET_MOVIE_BY_NAME = "SELECT * FROM movies WHERE title = ?";
	
	private static final String GET_MOVIES_BY_LANGUAGE = "SELECT * FROM MOVIES WHERE LANGUAGE_ID=?";
	private static final String GET_MOVIES_BY_GENRE = "SELECT * FROM MOVIES  WHERE GENRE_ID=?";


//	Logger logger = LoggerApp.getLogger();

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
			System.out.println("Internal Problems..." + e.getMessage());
//			logger.fatal("Internal Problems..." + e.getMessage());
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
			System.out.println("Data Not Found..." + e.getMessage());
//			logger.fatal("Data Not Found... " + e.getMessage());
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
			System.out.println("Internal Problems..." + e.getMessage());
//			logger.fatal("Internal Problems..." + e.getMessage());
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
			System.out.println("Error while fetching language ID: " + e.getMessage());
//			logger.fatal("Error while fetching language ID: " + e.getMessage());
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
			System.out.println("Internal Problems..." + e.getMessage());
//			logger.fatal("Internal Problems..." + e.getMessage());
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
			System.out.println("Internal Problems..." + e.getMessage());
//			logger.fatal("Internal Problems..." + e.getMessage());
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
			System.out.println("Internal Problems..." + e.getMessage());

//			logger.fatal("Error fetching genres: " + e.getMessage());
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
				System.out.println("Internal Problems..." + e.getMessage());

//				logger.fatal("Error updating genre: " + e.getMessage());
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
			System.out.println("Internal Problems..." + e.getMessage());

//			logger.fatal("Error removing genre: " + e.getMessage());
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
			System.out.println("Internal Problems..." + e.getMessage());
//			logger.fatal("Error while fetching genre ID: " + e.getMessage());
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
			System.out.println("Internal Problems..." + e.getMessage());
//			logger.fatal("Error adding movie: " + e.getMessage());
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
			System.out.println("Internal Problems..." + e.getMessage());
//			logger.fatal("Error fetching movies: " + e.getMessage());
		}
		return movies; // Return empty list if an error occurs
	}

	@Override
	public int updateMovie(Movies updatedMovie) {
		try {
			String languageName = updatedMovie.getLanguage();
			int languageId = getLanguageIdByName(languageName);

			String genreName = updatedMovie.getGenreName();
			int genreId = getGenreIdByName(genreName);

			// Prepare the update SQL query
			String query = "UPDATE movies SET title = ?, duration = ?, language_id = ?, release_date = ?, genre_id = ? WHERE movie_id = ?";

			ps = con.prepareStatement(query);
			ps.setString(1, updatedMovie.getTitle());
			ps.setTime(2, updatedMovie.getDuration());
			ps.setInt(3, languageId);
			ps.setDate(4, updatedMovie.getReleaseDate());
			ps.setInt(5, genreId);
			ps.setInt(6, updatedMovie.getMovieId());

			// Execute the update query
			int result = ps.executeUpdate();
			return result > 0 ? result : -1;
		} catch (SQLException e) {
			System.out.println("Internal Problems..." + e.getMessage());

//			logger.fatal("Error updating movie: " + e.getMessage());
		}
		return 0;
	}

	private int getLanguageIdByName(String languageName) {
		try {
			String query = "SELECT language_id FROM languages WHERE language_name = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, languageName);
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("language_id");
			}
		} catch (SQLException e) {
			System.out.println("Internal Problems..." + e.getMessage());
//			logger.fatal("Error fetching language_id for language_name: " + e.getMessage());
		}
		return 0;
	}

	private int getGenreIdByName(String genreName) {
		try {
			String query = "SELECT genre_id FROM genres WHERE genre_name = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, genreName);
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("genre_id");
			}
		} catch (SQLException e) {
			System.out.println("Internal Problems..." + e.getMessage());
//			logger.fatal("Error fetching genre_id for genre_name: " + e.getMessage());
		}
		return 0;
	}

	@Override
	public int removeMovie(int movieId) {
		try {
			ps = con.prepareStatement(DELETE_MOVIE);
			ps.setInt(1, movieId);

			int result = ps.executeUpdate();
			return result > 0 ? result : 0;
		} catch (SQLException e) {
			System.out.println("Internal Problems..." + e.getMessage());
//			logger.fatal("Error removing movie: " + e.getMessage());
		}
		return 0;
	}

	@Override
	public Movies getMovieById(int movieId) {
		Movies movie = null;
		try {
			ps = con.prepareStatement(GET_MOVIE_BY_ID);
			ps.setInt(1, movieId);
			rs = ps.executeQuery();

			if (rs.next()) {
				Language language = new Language(rs.getInt("language_id"), rs.getString("language_name"));

				Genre genre = new Genre(rs.getInt("genre_id"), rs.getString("genre_name"));

				movie = new Movies(rs.getInt("movie_id"), rs.getString("title"), rs.getTime("duration"), language,
						rs.getDate("release_date"), genre);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return movie;
	}
	
	@Override
	public Movies getMovieByName(String movieName) {
       
        try {
        	ps = con.prepareStatement(GET_MOVIE_BY_NAME);
            ps.setString(1, movieName);
            rs = ps.executeQuery();
            if (rs.next()) {
                Movies movie = new Movies();
                movie.setMovieId(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                return movie;
            }
		} catch (Exception e) {
			System.out.println("Error is "+e.getMessage());
		}
        return null;
    }

	private static int getLanguageById(String lname) {
		try {
			ps = con.prepareStatement("select * from languages where language_name=?");
			ps.setString(1, lname);
			rs = ps.executeQuery();
			while (rs.next()) {
				int lid = rs.getInt(1);
				return lid;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());

		}
		return 0;

	}

	@Override
	public void movieFinderByLanguage(String name) {

		int llid = AddMovieRepositoryImpl.getLanguageById(name);
		System.out.println(llid);

		try {

			ps = con.prepareStatement(GET_MOVIES_BY_LANGUAGE);
			ps.setInt(1, llid);
			rs = ps.executeQuery();

			while (rs.next()) {

				java.sql.Time duration = rs.getTime("duration");
				Movies m = new Movies();
				m.setTitle(rs.getString("title"));
				m.setDuration(duration);
				// moviesList.add(m);

				// m.setLanguage(rs.getInt("language_id"));
				System.out.println(rs.getString("title") + "\t\t\t" + duration + "\t\t\t" + rs.getInt("language_id"));
				System.out.println("--------------------------------------------------------------------------------");
				System.out.printf("%-30s %-20s %-15s%n", "Title", "Duration (mins)");
				System.out.println("--------------------------------------------------------------------------------");

				while (rs.next()) {
				    String title = rs.getString("title");
				    
				    System.out.printf("%-30s %-20d %-15d%n", title, duration);
				}

				System.out.println("--------------------------------------------------------------------------------");

			}
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static int getGenreById(String gname) {
		try {
			ps = con.prepareStatement("select * from genres where genre_name=?");
			ps.setString(1, gname);
			rs = ps.executeQuery();
			while (rs.next()) {
				int gid = rs.getInt(1);
				return gid;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());

		}
		return 0;

	}

	@Override
	public void movieFinderByGenre(String gname) {

		int ggid = AddMovieRepositoryImpl.getGenreById(gname);
		System.out.println(ggid);

		try {
		    ps = con.prepareStatement(GET_MOVIES_BY_GENRE);
		    ps.setInt(1, ggid);
		    rs = ps.executeQuery();

		    // Print table header
		    System.out.println("-------------------------------------------------------------");
		    System.out.printf("%-30s %-20s %-15s%n", "Title", "Duration");
		    System.out.println("-------------------------------------------------------------");

		    while (rs.next()) {
		        java.sql.Time duration = rs.getTime("duration");
		        Movies m = new Movies();
		        m.setTitle(rs.getString("title"));
		        m.setDuration(duration);

		        // Print each movie's details
		        System.out.printf("%-30s %-20s %-15d%n", 
		                          rs.getString("title"), 
		                          duration  );
		    }

		    System.out.println("-------------------------------------------------------------");

		} catch (Exception e) {
		    e.printStackTrace();
		}


	}
}