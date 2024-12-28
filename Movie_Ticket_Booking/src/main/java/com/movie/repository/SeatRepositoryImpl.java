package com.movie.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.movie.config.DBState;
import com.movie.model.Booking;
import com.movie.model.Seat;

public class SeatRepositoryImpl extends DBState implements ISeatRepository {

    private static final String ADD_SEAT = "INSERT INTO SEATS (SHOWTIME_ID, SEAT_NUMBER, IS_AVAILABLE) VALUES (?, ?, ?)";
    private static final String GET_SEATS_BY_SHOWTIME = "SELECT * FROM SEATS WHERE SHOWTIME_ID = ?";
    private static final String UPDATE_SEAT_AVAILABILITY = "UPDATE SEATS SET IS_AVAILABLE = ? WHERE SEAT_ID = ?";
    private static final String DELETE_SEAT = "DELETE FROM SEATS WHERE SEAT_ID = ? ";
    private static final String SELECT_SEAT_BY_CINEMA_MOVIE = "SELECT S.SEAT_ID, S.SEAT_NUMBER, S.IS_AVAILABLE, ST.SHOW_DATE, ST.START_TIME, ST.END_TIME,C.CINEMANAME" +
    		"FROM SEATS S JOIN SHOWTIMES ST ON S.SHOWTIME_ID = ST.SHOWTIME_ID JOIN" +
    	    "CINEMA C ON ST.CINEMA_ID = C.CINEMA_ID WHERE ST.CINEMA_ID = ? AND ST.SHOWTIME_ID = ?";

    @Override
    public int addSeat(Seat seat) {
        try {
            ps = con.prepareStatement(ADD_SEAT);
            ps.setInt(1, seat.getShowtimeId());
            ps.setString(2, seat.getSeatNumber());
            ps.setBoolean(3, seat.isAvailable());
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            System.out.println("Error adding seat: " + e.getMessage());
        }
        return 0;
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
    
    public int getMovieIdByName(String movieName) {
		String query = "SELECT MOVIE_ID FROM MOVIES WHERE TITLE = ?";
		int movieId = 0;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, movieName);
			rs = ps.executeQuery();
			if (rs.next()) {
	            movieId = rs.getInt("movie_id");  
	        }
	    } catch (Exception e) {
	        System.out.println("Error is in getMovieIdByName()"+e.getMessage());  // Print the exception for debugging
	    }

	    return movieId;
	}

    
    public int getCinemaIdBYName(String cinemaName) {
		String query = "select cinema_id from cinema where cinemaName = ?";
		int cinemaId = 0;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, cinemaName);
			rs = ps.executeQuery();
			if (rs.next()) {
	            cinemaId = rs.getInt("cinema_id");  
	        }
	    } catch (Exception e) {
//	        e.printStackTrace();  // Print the exception for debugging
	        System.out.println("Error is in getCinemaIdBYName()"+e.getMessage());  // Print the exception for debugging
	    }
	    return cinemaId;
	}
    
    public List<Integer> getShowtimeIdsByCinemaId(int cinemaId) {
        String query = "SELECT showtime_id FROM showtimes WHERE cinema_id = ?";
        List<Integer> showtimeIds = new ArrayList<>();
        try {
        	ps = con.prepareStatement(query);
            ps.setInt(1, cinemaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    showtimeIds.add(rs.getInt("showtime_id"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return showtimeIds;
    }
  
    public List<Seat> getSeatsByShowtime(int showtimeId) {
        String query = "SELECT SEAT_ID, SEAT_NUMBER, IS_AVAILABLE FROM SEATS WHERE SHOWTIME_ID = ?";
        List<Seat> seats = new ArrayList<>();

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, showtimeId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Seat seat = new Seat();
                seat.setSeatId(rs.getInt("SEAT_ID"));
                seat.setSeatNumber(rs.getString("SEAT_NUMBER"));
                seat.setAvailable(rs.getBoolean("IS_AVAILABLE"));
                seats.add(seat);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching seat data: " + e.getMessage());
        }

        return seats;
    }

  
    public List<Seat> showSeatsAvailable(int showtimeId) {
        // Get the seats for the given showtime ID
        List<Seat> seats = getSeatsByShowtime(showtimeId);

        // If no seats are available, print a message and return an empty list
        if (seats.isEmpty()) {
            System.out.println("No seats available for this showtime.");
            return seats;
        }

        // Display header for seat availability
        System.out.println("Seat Availability for Showtime ID: " + showtimeId);
        System.out.printf("%-10s %-15s %-10s\n", "Seat ID", "Seat Number", "Available");
        System.out.println("-----------------------------------------");

        // Print each seat's details
        for (Seat seat : seats) {
            System.out.printf("%-10d %-15s %-10s\n", 
                              seat.getSeatId(), 
                              seat.getSeatNumber(), 
                              seat.isAvailable() ? "Yes" : "No");
        }

        // Return the list of seats (this is useful for further processing or displaying)
        return seats;
    }

	@Override
	public boolean bookMySeat(Booking book) {
		
		List<Seat> bookSeat=book.getSeatList();
		try {
			ps=con.prepareStatement("INSERT INTO booking  VALUES ('0',?,?,1,CURRENT_DATE,100)");
			ps.setInt(1, book.getUserId());
			ps.setInt(2, book.getShowTimeId());
			int value=ps.executeUpdate();
			if(value>0) {
				int ticketcount=0;
				for(Seat seat:bookSeat) {
					ticketcount++;
					ps=con.prepareStatement("UPDATE SEATS SET IS_AVAILABLE = 0 WHERE SEAT_ID = ?");
					ps.setInt(1, seat.getSeatId());
					ps.executeUpdate();
					
				}
				int totalPrice=ticketcount*150;
				int booking_id=this.getMaxBookingId();
				ps=con.prepareStatement("UPDATE BOOKING SET TOTAL_COST=? WHERE BOOKING_ID=?");
				ps.setInt(1, totalPrice);
				ps.setInt(2, booking_id);
				value=ps.executeUpdate();
				
				
				System.out.println("Seats you Booked: "+ticketcount);
				System.out.println("\nTotal price: "+totalPrice);
				System.out.println("");
				
				if(value>0) {
					return true;
				}
			}
			
			
		}catch (Exception e) {
			System.out.println("Error is in bookMySeat "+e);
		}
		return false;
	}
	
	public int getMaxBookingId() {
		
		try {
			ps=con.prepareStatement("SELECT MAX(BOOKING_ID) FROM BOOKING");
			rs=ps.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		}catch(Exception ex) {
			System.out.println("Error is  getMaxBookingId :: "+ex);
		}
		return 0;
		
	}


}
