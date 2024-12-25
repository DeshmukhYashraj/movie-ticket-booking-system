package com.movie.service;

import java.util.List;

import com.movie.model.Cinema;

public interface IAddCinemaService {

	public int addCinema(Cinema cinema);

	public List<Cinema> getAllCinema();

	public int updateCinema(Cinema updateCinema);

	public int removeCinema(int cinemaId);
	
	public Cinema getCinemaById(int cinemaId);

}
