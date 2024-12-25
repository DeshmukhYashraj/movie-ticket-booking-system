package com.movie.repository;

import java.util.List;
import com.movie.model.Seat;

public interface ISeatRepository {
	
	public int addSeat(Seat seat);

	public List<Seat> getSeatsByShowtimeId(int showtimeId);

	public int updateSeatAvailability(int seatId, boolean isAvailable);

	public int deleteSeat(int seatId);
}
