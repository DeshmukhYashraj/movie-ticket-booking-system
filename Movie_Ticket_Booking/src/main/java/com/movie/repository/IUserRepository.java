package com.movie.repository;

import com.movie.model.UserModel;

public interface IUserRepository {
	
	

	boolean addUser(UserModel user);

	boolean validateUserLogin(String email, String password);

}
