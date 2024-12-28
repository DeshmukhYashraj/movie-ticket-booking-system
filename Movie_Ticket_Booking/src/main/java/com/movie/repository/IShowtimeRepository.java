package com.movie.repository;

import java.sql.SQLException;
import java.util.List;
import com.movie.model.Showtime;

public interface IShowtimeRepository {

    // Add a new Showtime
    public int addShowtime(Showtime showtime) ;

    // Retrieve all Showtimes
    public List<Showtime> getAllShowtimes();

    // Retrieve a specific Showtime by ID
    public Showtime getShowtimeById(int showtimeId);

    // Update an existing Showtime
    public int updateShowtime(Showtime showtime);

    // Remove a Showtime by ID
    public int deleteShowtime(int showtimeId);
    
    
    public List<Showtime> getAllShowtimesByCinema(String movieName, String cinemaName);
    
    
}
