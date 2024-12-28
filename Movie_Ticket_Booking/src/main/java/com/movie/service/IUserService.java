package com.movie.service;

import com.movie.model.UserModel;

public interface IUserService  {

	public boolean addNewUser(UserModel user);

	public boolean validateUserLogin(String email, String password);
	
	public UserModel getUserByUsername(String username);

	 public int getUserIdByEmail(String email);
	
}
