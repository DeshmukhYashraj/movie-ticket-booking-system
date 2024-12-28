package com.movie.service;

import java.sql.SQLException;
import java.util.List;

import com.movie.model.Showtime;

public interface IShowtimeService {
	
	// Add a new Showtime
	int addShowtime(Showtime showtime) throws SQLException;

	// Retrieve all Showtimes
	List<Showtime> getAllShowtimes() throws SQLException;

	// Retrieve a specific Showtime by ID
	Showtime getShowtimeById(int showtimeId) throws SQLException;

	// Update an existing Showtime
	int updateShowtime(Showtime showtime) throws SQLException;

	// Remove a Showtime by ID
	int deleteShowtime(int showtimeId) throws SQLException;
	
	public List<Showtime> getAllShowtimesByCinema(String movieName, String cinemaName);
}
