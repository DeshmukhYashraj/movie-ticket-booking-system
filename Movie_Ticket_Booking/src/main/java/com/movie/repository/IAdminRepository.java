package com.movie.repository;

import com.movie.model.AdminModel;

public interface IAdminRepository {

	public boolean isAddNewAdmin(AdminModel admin);
	
	public boolean adminLogin(String username,String password);
}
