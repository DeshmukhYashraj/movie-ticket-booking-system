package com.movie.service;

import java.util.List;

import com.movie.model.Language;
import com.movie.repository.AddMovieRepositoryImpl;
import com.movie.repository.IAddMovieRepository;

public class AddMovieServiceImpl implements IAddMovieService {

	IAddMovieRepository movieRepo = new AddMovieRepositoryImpl();

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
	public int updateMovieLanguage(Language lang) {
		return 0;
	}

}
