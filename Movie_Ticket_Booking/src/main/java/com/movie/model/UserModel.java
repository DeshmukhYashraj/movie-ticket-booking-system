package com.movie.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

	private Integer userId;
	private String username;
	private String email;
	private String password;
	private String phone;
	private Date registeredDate;
	
}
