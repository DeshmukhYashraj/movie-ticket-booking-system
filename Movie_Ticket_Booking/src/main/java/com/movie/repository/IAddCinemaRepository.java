package com.movie.repository;

import java.util.List;

import com.movie.model.Cinema;
import com.movie.model.Seat;

public interface IAddCinemaRepository {

	public int addCinema(Cinema cinema);
	
	public List<Cinema> getAllCinema(); 
	
	public int updateCinema(Cinema updateCinema);
	
	public int removeCinema(int cinemaId);
	
	public Cinema getCinemaById(int cinemaId);
	
	public List<Cinema> getCinemaByMovie(String movieName);
	
	public List<Seat> bookMySeat(List<Seat> bookSeat);
}
