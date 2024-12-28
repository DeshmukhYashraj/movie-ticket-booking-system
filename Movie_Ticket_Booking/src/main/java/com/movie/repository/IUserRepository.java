package com.movie.repository;

import com.movie.model.UserModel;

public interface IUserRepository {
	
	boolean addUser(UserModel user);

	boolean validateUserLogin(String email, String password);
	
	public UserModel getUserByUsername(String username);
	
	public int getUserIdByEmail(String email);
	
	

}
