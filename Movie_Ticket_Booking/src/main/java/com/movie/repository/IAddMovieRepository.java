package com.movie.repository;

import java.util.List;

import com.movie.model.Genre;
import com.movie.model.Language;

public interface IAddMovieRepository {
	public int addMovieLanguage(Language lang);

	public List<Language> getAllLanguage();

	public int removeMovieLanguage(Language lang);

	public int updateMovieLanguage(String oldName, String newName);

	public int updateMovieGenre(String oldName, String newName);

	public List<Genre> getAllGenres();

	public int addMovieGenre(Genre genre);

	public int removeMovieGenre(Genre genre);

}
