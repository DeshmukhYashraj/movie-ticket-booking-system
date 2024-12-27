package com.movie.repository;

import com.movie.config.DBState;
import com.movie.model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryImpl extends DBState implements IBookingRepository {

	@Override
	public boolean createBooking(Booking booking) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Booking getBookingByIds(int userId, int showtimeId, int seatId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> getBookingsByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateBookingByIds(Booking booking, int userId, int showtimeId, int seatId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBooking(Booking booking) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBookingByIds(int userId, int showtimeId, int seatId) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
