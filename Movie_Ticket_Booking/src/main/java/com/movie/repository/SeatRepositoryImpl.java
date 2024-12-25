package com.movie.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.movie.config.DBState;
import com.movie.model.Seat;

public class SeatRepositoryImpl extends DBState implements ISeatRepository {

    private static final String ADD_SEAT = "INSERT INTO seats (showtime_id, seat_number, is_available) VALUES (?, ?, ?)";
    private static final String GET_SEATS_BY_SHOWTIME = "SELECT * FROM seats WHERE showtime_id = ?";
    private static final String UPDATE_SEAT_AVAILABILITY = "UPDATE seats SET is_available = ? WHERE seat_id = ?";
    private static final String DELETE_SEAT = "DELETE FROM seats WHERE seat_id = ?";

    @Override
    public int addSeat(Seat seat) {
        int result = 0;
        try {
            ps = con.prepareStatement(ADD_SEAT);
            ps.setInt(1, seat.getShowtimeId());
            ps.setString(2, seat.getSeatNumber());
            ps.setBoolean(3, seat.isAvailable());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding seat: " + e.getMessage());
        }
        return result;
    }

    @Override
    public List<Seat> getSeatsByShowtimeId(int showtimeId) {
        List<Seat> seats = new ArrayList<>();
        try {
            ps = con.prepareStatement(GET_SEATS_BY_SHOWTIME);
            ps.setInt(1, showtimeId);
            rs = ps.executeQuery();
            while (rs.next()) {
                seats.add(new Seat(
                    rs.getInt("seat_id"),
                    rs.getInt("showtime_id"),
                    rs.getString("seat_number"),
                    rs.getBoolean("is_available")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching seats by showtime ID: " + e.getMessage());
        }
        return seats;
    }

    @Override
    public int updateSeatAvailability(int seatId, boolean isAvailable) {
        int result = 0;
        try {
            ps = con.prepareStatement(UPDATE_SEAT_AVAILABILITY);
            ps.setBoolean(1, isAvailable);
            ps.setInt(2, seatId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating seat availability: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteSeat(int seatId) {
        int result = 0;
        try {
            ps = con.prepareStatement(DELETE_SEAT);
            ps.setInt(1, seatId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting seat: " + e.getMessage());
        }
        return result;
    }
}
