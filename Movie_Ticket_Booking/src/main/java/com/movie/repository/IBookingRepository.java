package com.movie.repository;

import com.movie.model.Booking;
import java.util.List;

public interface IBookingRepository {
	
	public boolean createBooking(Booking booking);

	public Booking getBookingByIds(int userId, int showtimeId, int seatId); // Read by Booking ID

	public List<Booking> getBookingsByUserId(int userId); // Read by User ID

	public boolean updateBookingByIds(Booking booking, int userId, int showtimeId, int seatId);

	boolean updateBooking(Booking booking); // Update

	public boolean deleteBookingByIds(int userId, int showtimeId, int seatId); // Delete
}
