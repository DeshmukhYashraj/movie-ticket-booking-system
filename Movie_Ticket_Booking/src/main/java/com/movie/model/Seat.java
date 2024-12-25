package com.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
	private int seatId;
    private int showtimeId;
    private String seatNumber;
    private boolean isAvailable;
}
