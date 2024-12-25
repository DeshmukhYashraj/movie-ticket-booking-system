package com.movie.service;

import com.movie.model.AdminModel;
import com.movie.repository.AdminRepositoryImpl;

public interface IAdminService {

	public boolean isAddNewAdmin(AdminModel admin);

	public boolean adminLogin(String username, String password);

}
