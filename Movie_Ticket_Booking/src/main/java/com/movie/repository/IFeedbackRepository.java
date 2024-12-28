package com.movie.repository;

import com.movie.model.Feedback;
import com.movie.model.Movies;

import java.sql.SQLException;
import java.util.List;

public interface IFeedbackRepository {

	public boolean submitFeedback(int userId, String movieName, String feedbackText, int rating);
	
	public List<Feedback> getTopRatedMovies();
	
	public List<Movies> getUpcommingMovies();
	
	
	// Retrieve feedback by movie ID
	public List<Feedback> getFeedbackByMovieId(int movieId);

	// Delete feedback by ID
	public boolean deleteFeedbackById(int feedbackId);
}
