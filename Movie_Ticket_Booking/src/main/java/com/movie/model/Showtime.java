package com.movie.model;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Showtime {
    private int showtimeId;          // Unique identifier for the showtime
    private int movieId;             // Foreign key referencing a movie
    private int cinemaId;            // Foreign key referencing a cinema
    private String showDate;           // The date of the show
    private String startTime;          // The start time of the show (matches start_time in DB)
    private String endTime;            // The end time of the show (matches end_time in DB)
    
    private Movies movie;            // Movie details associated with this showtime
    private Cinema cinema;           // Cinema details associated with this showtime
}
