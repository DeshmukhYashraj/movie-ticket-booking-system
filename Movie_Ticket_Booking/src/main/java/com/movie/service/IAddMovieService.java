package com.movie.service;

import java.util.List;

import com.movie.model.Genre;
import com.movie.model.Language;
import com.movie.model.Movies;

public interface IAddMovieService {

	public int addMovieLanguage(Language lang);

	public List<Language> getAllLanguage();

	public int removeMovieLanguage(Language lang);

	public int updateMovieLanguage(String oldName, String newName);

	public int addMovieGenre(Genre genre);

	public List<Genre> getAllGenres();

	public int updateMovieGenre(String oldName, String newName);

	public int removeMovieGenre(Genre genre);

	public int addMovie(Movies movie);

	public List<Movies> getAllMovies();

	public int updateMovie(Movies updatedMovie);

	public int removeMovie(int movieId);
	
	public Movies getMovieById(int movieId);
	
	public void movieFinderByLanguage(String name);
	
	public Movies getMovieByName(String movieName);
	
	public void movieFinderByGenre(String gname);


}
