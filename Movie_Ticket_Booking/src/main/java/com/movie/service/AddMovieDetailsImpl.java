package com.movie.service;

import com.movie.model.Language;
import com.movie.repository.AddMovieRepositoryLanguage;
import com.movie.repository.IAddMovieRepository;

public class AddMovieDetailsImpl implements IAddMovieService {

	IAddMovieRepository movieRepo = new AddMovieRepositoryLanguage();

	@Override
	public int addMovieLanguage(Language lang) {

		return movieRepo.addMovieLanguage(lang);
	}

}
