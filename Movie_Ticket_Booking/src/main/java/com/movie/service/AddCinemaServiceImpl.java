package com.movie.service;

import java.util.List;

import com.movie.model.Cinema;
import com.movie.repository.AddCinemaRepositoryImpl;
import com.movie.repository.IAddCinemaRepository;

public class AddCinemaServiceImpl implements IAddCinemaService{

	IAddCinemaRepository cinemaRepo = new AddCinemaRepositoryImpl(); 
	
	@Override
	public int addCinema(Cinema cinema) {
		return cinemaRepo.addCinema(cinema);
	}

	@Override
	public List<Cinema> getAllCinema() {
		return cinemaRepo.getAllCinema();
	}

	@Override
	public int updateCinema(Cinema updateCinema) {
		return cinemaRepo.updateCinema(updateCinema);
	}

	@Override
	public int removeCinema(int cinemaId) {
		return cinemaRepo.removeCinema(cinemaId);
	}

	@Override
	public Cinema getCinemaById(int cinemaId) {
		return cinemaRepo.getCinemaById(cinemaId);
	}

}
