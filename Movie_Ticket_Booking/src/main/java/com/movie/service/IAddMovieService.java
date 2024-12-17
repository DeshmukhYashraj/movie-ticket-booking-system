package com.movie.service;

import java.util.List;

import com.movie.model.Language;

public interface IAddMovieService {

	public int addMovieLanguage(Language lang);

	public List<Language> getAllLanguage();

	public int removeMovieLanguage(Language lang);

	public int updateMovieLanguage(String oldName,String newName);

}
