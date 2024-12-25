package com.movie.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.movie.config.DBState;
import com.movie.model.Cinema;
import com.movie.model.Genre;
import com.movie.model.Language;
import com.movie.model.Movies;
import com.movie.model.Showtime;

public class ShowtimeRepositoryImpl extends DBState implements IShowtimeRepository {

	private static final String INSERT_SHOWTIME = "INSERT INTO showtimes (movie_id, cinema_id, show_date, start_time, end_time) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_SHOWTIMES = "SELECT s.showtime_id, s.movie_id, s.cinema_id, s.show_date, s.start_time, s.end_time, " +
			"m.title AS movie_title, m.duration AS movie_duration, l.language_name AS movie_language, " +
			"m.release_date AS movie_releaseDate, g.genre_name AS genre_name, " +
			"c.cinameName AS cinema_name, c.cinema_location AS cinema_location " +
			"FROM showtimes s " +
			"JOIN movies m ON s.movie_id = m.movie_id " +
			"JOIN languages l ON m.language_id = l.language_id " +
			"JOIN genres g ON m.genre_id = g.genre_id " +
			"JOIN cinema c ON s.cinema_id = c.cinema_id";

	private static final String UPDATE_SHOWTIME = "UPDATE showtimes SET movie_id = ?, cinema_id = ?, show_date = ?, start_time = ?, end_time = ? WHERE showtime_id = ?";
	private static final String DELETE_SHOWTIME = "DELETE FROM showtimes WHERE showtime_id = ?";
	private static final String GET_SHOWTIME_BY_ID = "SELECT s.showtime_id, s.movie_id, s.cinema_id, s.show_date, s.start_time, s.end_time, "
			+ "m.title AS movie_title, m.duration AS movie_duration,l.language_name AS movie_language, "
			+ "c.cinameName AS cinema_name, c.cinema_Location AS cinema_location FROM showtimes s "
			+ "JOIN movies m ON s.movie_id = m.movie_id JOIN cinema c ON s.cinema_id = c.cinema_id"
			+ "JOIN languages l ON m.language_id = l.language_id";
//	private static final String GET_SHOWTIME_BY_ID ="SELECT * FROM showtimes WHERE showtime_Id = ?";

	@Override
	public int addShowtime(Showtime showtime) {
		try {
			ps = con.prepareStatement(INSERT_SHOWTIME);
			ps.setInt(1, showtime.getMovieId());
			ps.setInt(2, showtime.getCinemaId());
			ps.setDate(3, showtime.getShowDate());
			ps.setTime(4, showtime.getStartTime());
			ps.setTime(5, showtime.getEndTime());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Showtime> getAllShowtimes() {
		List<Showtime> showtimes = new ArrayList<>();
		 try {
		        ps = con.prepareStatement(GET_ALL_SHOWTIMES);
		        rs = ps.executeQuery();
		        
		        while (rs.next()) {
		            // Create Language object
		            Language language = new Language(rs.getString("movie_language"));

		            // Create Genre object
		            Genre genre = new Genre(rs.getString("genre_name"));
		            
		            // Create Movie object
		            Movies movie = new Movies(
		                rs.getInt("movie_id"), 
		                rs.getString("movie_title"),
		                rs.getTime("movie_duration"),
		                language,
		                rs.getDate("movie_releaseDate"),
		                genre
		            );
		            
		            // Create Cinema object
		            Cinema cinema = new Cinema(
		                rs.getInt("cinema_id"),
		                rs.getString("cinema_name"),
		                rs.getString("cinema_location"),
		                null  // Assuming contact info is not needed here
		            );
		            
		            // Create Showtime object
		            Showtime showtime = new Showtime(
		                rs.getInt("showtime_id"),
		                rs.getInt("movie_id"),
		                rs.getInt("cinema_id"),
		                rs.getDate("show_date"),
		                rs.getTime("start_time"),
		                rs.getTime("end_time"),
		                movie,   // Set the movie object here
		                cinema   // Set the cinema object here
		            );
		            showtimes.add(showtime);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    return showtimes;	}

	@Override
	public int updateShowtime(Showtime showtime) {
		try {
			ps = con.prepareStatement(UPDATE_SHOWTIME);
			ps.setInt(1, showtime.getMovieId());
			ps.setInt(2, showtime.getCinemaId());
			ps.setDate(3, showtime.getShowDate());
			ps.setTime(4, showtime.getStartTime());
			ps.setTime(5, showtime.getEndTime());
			ps.setInt(6, showtime.getShowtimeId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteShowtime(int showtimeId) {
		try {
			ps = con.prepareStatement(DELETE_SHOWTIME);
			ps.setInt(1, showtimeId);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Showtime getShowtimeById(int showtimeId) {

		try {
			ps = con.prepareStatement(GET_SHOWTIME_BY_ID);
			ps.setInt(1, showtimeId);

			rs = ps.executeQuery();
			if (rs.next()) {
				// Create a `Movies` object with partial data
				Movies movie = new Movies(rs.getInt("movie_id"), rs.getString("title"), null, // Assuming duration
																									// is not fetched in
																									// this query
						null, // Assuming language is not fetched in this query
						null, // Assuming releaseDate is not fetched in this query
						null // Assuming genre is not fetched in this query
				);

				// Create a `Cinema` object with partial data
				Cinema cinema = new Cinema(rs.getInt("cinema_id"), rs.getString("cinameName"),
						rs.getString("cinema_location"), null // Assuming contact is not fetched in this query
				);

				// Create and return the `Showtime` object
				return new Showtime(rs.getInt("showtime_id"), rs.getInt("movie_id"), rs.getInt("cinema_id"),
						rs.getDate("show_date"), rs.getTime("start_time"), rs.getTime("end_time"), movie, cinema);
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
		return null;
	}
}
