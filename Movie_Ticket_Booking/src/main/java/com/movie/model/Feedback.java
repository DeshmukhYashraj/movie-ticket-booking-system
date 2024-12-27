package com.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
	private Integer feedbackId;
	private String feedbackText;
	private Integer rating;
	
	private UserModel user;
	private Movies movie;
	
}
