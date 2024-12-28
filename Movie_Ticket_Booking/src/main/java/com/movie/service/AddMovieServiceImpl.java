package com.movie.service;

import java.util.List;

import com.movie.model.Genre;
import com.movie.model.Language;
import com.movie.model.Movies;
import com.movie.repository.AddMovieRepositoryImpl;
import com.movie.repository.IAddMovieRepository;

public class AddMovieServiceImpl implements IAddMovieService {

	IAddMovieRepository movieRepo = new AddMovieRepositoryImpl();

	// Language-related methods
	@Override
	public int addMovieLanguage(Language lang) {
		return movieRepo.addMovieLanguage(lang);
	}

	@Override
	public List<Language> getAllLanguage() {
		return movieRepo.getAllLanguage();
	}

	@Override
	public int removeMovieLanguage(Language lang) {
		return movieRepo.removeMovieLanguage(lang);
	}

	@Override
	public int updateMovieLanguage(String oldName, String newName) {
		return movieRepo.updateMovieLanguage(oldName, newName);
	}

	// Genre-related methods

	@Override
	public int addMovieGenre(Genre genre) {
		// Call repository method to add genre
		return movieRepo.addMovieGenre(genre);
	}

	@Override
	public List<Genre> getAllGenres() {
		// Call repository method to retrieve all genres
		return movieRepo.getAllGenres();
	}

	@Override
	public int updateMovieGenre(String oldName, String newName) {
		// Call repository method to update genre name
		return movieRepo.updateMovieGenre(oldName, newName);
	}

	@Override
	public int removeMovieGenre(Genre genre) {
		// Call repository method to remove genre
		return movieRepo.removeMovieGenre(genre);
	}

	@Override
	public int addMovie(Movies movie) {
		return movieRepo.addMovie(movie);
	}

	@Override
	public List<Movies> getAllMovies() {
		return movieRepo.getAllMovies();
	}

	@Override
	public int updateMovie(Movies updatedMovie) {
		return movieRepo.updateMovie(updatedMovie);
	}

	@Override
	public int removeMovie(int movieId) {
		return movieRepo.removeMovie(movieId);
	}

	@Override
	public Movies getMovieById(int movieId) {
		return movieRepo.getMovieById(movieId);
	}

	@Override
	public void movieFinderByLanguage(String name) {
		
		movieRepo.movieFinderByLanguage(name);
	}

	@Override
	public void movieFinderByGenre(String gname) {
		movieRepo.movieFinderByGenre(gname);
	}

	@Override
	public Movies getMovieByName(String movieName) {
		return movieRepo.getMovieByName(movieName);
	}

}
