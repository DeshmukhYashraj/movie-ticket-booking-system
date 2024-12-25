package com.movie.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	private Integer bookingId;
	private LocalDate bookingDate;
	private Double totalCost;
	
	private UserModel userModel;
	private Showtime showtime;
	private Seat seat;
	
}
