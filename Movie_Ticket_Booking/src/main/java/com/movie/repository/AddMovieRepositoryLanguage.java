package com.movie.repository;

import com.movie.config.DBConfig;
import com.movie.config.DBState;
import com.movie.model.Language;

public class AddMovieRepositoryLanguage extends DBState implements IAddMovieRepository {

	@Override
	public int addMovieLanguage(Language lang) {
		con = DBConfig.getCon();
		if (con != null)
			System.out.println("connected");
		else
			System.out.println("not connected");

		return 0;
	}

}
