package com.movie.repository;

import java.util.List;

import com.movie.model.Language;

public interface IAddMovieRepository {
	public int addMovieLanguage(Language lang);

	public List<Language> getAllLanguage();

	public int removeMovieLanguage(Language lang);

	public int updateMovieLanguage(Language lang);

}
