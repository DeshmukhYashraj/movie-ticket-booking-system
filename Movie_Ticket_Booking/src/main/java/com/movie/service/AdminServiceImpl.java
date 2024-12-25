package com.movie.service;

import com.movie.model.AdminModel;
import com.movie.repository.AdminRepositoryImpl;
import com.movie.repository.IAdminRepository;

public class AdminServiceImpl implements IAdminService{

	IAdminRepository adminRepo = new AdminRepositoryImpl();

	@Override
	public boolean isAddNewAdmin(AdminModel admin) {
		return adminRepo.isAddNewAdmin(admin);
	}

	@Override
	public boolean adminLogin(String username, String password) {
		return adminRepo.adminLogin(username, password);
	}
	
	
}
