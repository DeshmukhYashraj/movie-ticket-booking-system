package com.movie.repository;

import java.util.List;

import com.movie.model.Cinema;

public interface IAddCinemaRepository {

	public int addCinema(Cinema cinema);
	
	public List<Cinema> getAllCinema(); 
	
	public int updateCinema(Cinema updateCinema);
	
	public int removeCinema(int cinemaId);
	
	public Cinema getCinemaById(int cinemaId);
}
