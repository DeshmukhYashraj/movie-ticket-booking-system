package com.movie.repository;

import com.movie.config.DBState;
import com.movie.model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryImpl extends DBState implements IBookingRepository {

	// private static final ADD_NEW_BOOKING = "";

	@Override
	public boolean createBooking(Booking booking, int userId, int showtimeId, int seatId) {
		String query = "INSERT INTO booking (user_id, showtime_id, seat_id, booking_date, total_cost) VALUES (?, ?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setInt(2, showtimeId);
			ps.setInt(3, seatId);
			ps.setDate(4, Date.valueOf(booking.getBookingDate()));
			ps.setDouble(5, booking.getTotalCost());
			int affectedRows = ps.executeUpdate();
			if (affectedRows > 0) {
				try (ResultSet keys = ps.getGeneratedKeys()) {
					if (keys.next()) {
						booking.setBookingId(keys.getInt(1));
					}
				}
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Booking getBookingByIds(int userId, int showtimeId, int seatId) {
		String query = "SELECT booking_id, booking_date, total_cost FROM booking WHERE user_id = ? AND showtime_id = ? AND seat_id = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setInt(2, showtimeId);
			ps.setInt(3, seatId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapResultSetToBooking(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateBookingByIds(Booking booking, int userId, int showtimeId, int seatId) {
		String query = "UPDATE booking SET booking_date = ?, total_cost = ? WHERE user_id = ? AND showtime_id = ? AND seat_id = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setDate(1, Date.valueOf(booking.getBookingDate()));
			ps.setDouble(2, booking.getTotalCost());
			ps.setInt(3, userId);
			ps.setInt(4, showtimeId);
			ps.setInt(5, seatId);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteBookingByIds(int userId, int showtimeId, int seatId) {
		String query = "DELETE FROM booking WHERE user_id = ? AND showtime_id = ? AND seat_id = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setInt(2, showtimeId);
			ps.setInt(3, seatId);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Booking> getBookingsByUserId(int userId) {
		String query = "SELECT booking_id, booking_date, total_cost FROM booking WHERE user_id = ?";
		List<Booking> bookings = new ArrayList<>();
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				bookings.add(mapResultSetToBooking(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookings;
	}

	private Booking mapResultSetToBooking(ResultSet rs) throws SQLException {
		Booking booking = new Booking();
		booking.setBookingId(rs.getInt("booking_id"));
		booking.setBookingDate(rs.getDate("booking_date").toLocalDate());
		booking.setTotalCost(rs.getDouble("total_cost"));
		return booking;
	}

	@Override
	public boolean updateBooking(Booking booking) {
		// TODO Auto-generated method stub
		return false;
	}

}
