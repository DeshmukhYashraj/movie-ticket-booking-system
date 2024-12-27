package com.movie.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.movie.config.DBState;
import com.movie.model.Cinema;

public class AddCinemaRepositoryImpl extends DBState implements IAddCinemaRepository {

	private static final String ADD_CINEMA = "INSERT INTO cinema (cinameName, cinema_location, contact) VALUES (?, ?, ?)";
	private static final String SHOW_CINEMAS = "SELECT * FROM CINEMA";
	private static final String UPDATE_CINEMA = "UPDATE cinema SET cinameName = ?, cinema_location = ?, contact =? WHERE cinema_id = ?";
	private static final String DELETE_CINEMA = "DELETE FROM cinema WHERE cinema_id = ?";
    private static final String GET_CINEMA_BY_ID = "SELECT * FROM cinema WHERE cinema_id = ?";

	 @Override
	    public int addCinema(Cinema cinema) {
	        int value = 0;
	        try {
	            ps = con.prepareStatement(ADD_CINEMA);
	            ps.setString(1, cinema.getCinameName());
	            ps.setString(2, cinema.getCinemaLocation());
	            ps.setString(3, cinema.getCinemaContact());
	            value = ps.executeUpdate();
	            return value > 0 ? value : 0;
	        } catch (SQLException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	        return value;
	    }

	    @Override
	    public List<Cinema> getAllCinema() {
	        List<Cinema> cinemas = new ArrayList<>();
	        try {
	            ps = con.prepareStatement(SHOW_CINEMAS);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Cinema cinema = new Cinema();
	                cinema.setCinemaId(rs.getInt("cinema_id"));
	                cinema.setCinameName(rs.getString("cinameName"));
	                cinema.setCinemaLocation(rs.getString("cinema_location"));
	                cinema.setCinemaContact(rs.getString("contact"));
	                cinemas.add(cinema);
	            }
	        } catch (SQLException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	        return cinemas;
	    }

	    @Override
	    public int updateCinema(Cinema updateCinema) {
	        int value = 0;
	        try {
	            ps = con.prepareStatement(UPDATE_CINEMA);
	            ps.setString(1, updateCinema.getCinameName());
	            ps.setString(2, updateCinema.getCinemaLocation());
	            ps.setString(3, updateCinema.getCinemaContact());
	            ps.setInt(4, updateCinema.getCinemaId());
	            value = ps.executeUpdate();
	            return value > 0 ? value : 0;
	        } catch (SQLException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	        return value;
	    }

	    @Override
	    public int removeCinema(int cinemaId) {
	        int value = 0;
	        try {
	            ps = con.prepareStatement(DELETE_CINEMA);
	            ps.setInt(1, cinemaId);
	            value = ps.executeUpdate();
	            return value > 0 ? value : 0;
	        } catch (SQLException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	        return value;
	    }

	    @Override
	    public Cinema getCinemaById(int cinemaId) {
	        Cinema cinema = null;
	        try {
	            ps = con.prepareStatement(GET_CINEMA_BY_ID);
	            ps.setInt(1, cinemaId);
	            rs = ps.executeQuery();
	            if (rs.next()) {
	                int id = rs.getInt("cinema_id");
	                String name = rs.getString("cinameName");
	                String location = rs.getString("cinema_location");
	                String contact = rs.getString("contact");
	                cinema = new Cinema(id, name, location, contact);
	            }
	        } catch (SQLException e) {
	            System.out.println("Error fetching cinema by ID: " + e.getMessage());
	        } 
	        return cinema;
	    }
	
}