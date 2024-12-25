package com.movie.repository;

import java.sql.SQLException;
import java.util.List;
import com.movie.model.Showtime;

public interface IShowtimeRepository {

    // Add a new Showtime
    int addShowtime(Showtime showtime) ;

    // Retrieve all Showtimes
    List<Showtime> getAllShowtimes();

    // Retrieve a specific Showtime by ID
    Showtime getShowtimeById(int showtimeId);

    // Update an existing Showtime
    int updateShowtime(Showtime showtime);

    // Remove a Showtime by ID
    int deleteShowtime(int showtimeId);
}
