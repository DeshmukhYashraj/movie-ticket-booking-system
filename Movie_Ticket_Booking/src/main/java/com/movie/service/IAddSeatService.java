package com.movie.service;

import java.util.List;

import com.movie.model.Booking;
import com.movie.model.Seat;
import com.movie.repository.ISeatRepository;
import com.movie.repository.SeatRepositoryImpl;

public interface IAddSeatService {
	
	public int addSeat(Seat seat);

	public List<Seat> getSeatsByShowtimeId(int showtimeId);

	public int updateSeatAvailability(int seatId, boolean isAvailable);

	public int deleteSeat(int seatId);
	
	public List<Seat> showSeatsAvailable(int showtimeId);
	
	public boolean bookMySeat(Booking book);
	
}
