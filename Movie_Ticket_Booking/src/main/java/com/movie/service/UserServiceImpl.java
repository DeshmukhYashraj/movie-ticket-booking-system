package com.movie.service;

import com.movie.model.UserModel;
import com.movie.repository.IUserRepository;
import com.movie.repository.UserRepositoryImpl;

public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository = new UserRepositoryImpl();

    // Method to register a new user
    @Override
    public boolean addNewUser(UserModel user) {
        return userRepository.addUser(user);
    }

    // Method to validate user login
    @Override
    public boolean validateUserLogin(String email, String password) {
        return userRepository.validateUserLogin(email, password);
    }

	@Override
	public UserModel getUserByUsername(String username) {
		return userRepository.getUserByUsername(username);
	}

	@Override
	public int getUserIdByEmail(String email) {
		return userRepository.getUserIdByEmail(email);
	}
}
