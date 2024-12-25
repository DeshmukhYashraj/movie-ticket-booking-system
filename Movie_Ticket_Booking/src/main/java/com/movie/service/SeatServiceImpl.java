package com.movie.service;

import java.util.List;

import com.movie.model.Seat;
import com.movie.repository.ISeatRepository;
import com.movie.repository.SeatRepositoryImpl;

public class SeatServiceImpl implements IAddSeatService{

	ISeatRepository seatRepo = new SeatRepositoryImpl();

	@Override
	public int addSeat(Seat seat) {
		return seatRepo.addSeat(seat);
	}

	@Override
	public List<Seat> getSeatsByShowtimeId(int showtimeId) {
		return seatRepo.getSeatsByShowtimeId(showtimeId);
	}

	@Override
	public int updateSeatAvailability(int seatId, boolean isAvailable) {
		return seatRepo.updateSeatAvailability(seatId, isAvailable);
	}

	@Override
	public int deleteSeat(int seatId) {
		return seatRepo.deleteSeat(seatId);
	}

}
