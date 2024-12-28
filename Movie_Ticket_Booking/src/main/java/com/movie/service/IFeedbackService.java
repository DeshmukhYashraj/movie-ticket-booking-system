package com.movie.service;

import java.util.List;

import com.movie.model.Feedback;
import com.movie.model.Movies;

public interface IFeedbackService {

	// Add a new feedback
	public boolean submitFeedback(int userId, String movieName, String feedbackText, int rating);

	public List<Feedback> getTopRatedMovies();
	
	public List<Movies> getUpcommingMovies();
	// Retrieve feedback by movie ID
	public List<Feedback> getFeedbackByMovieId(int movieId);

	// Delete feedback by ID
	public boolean deleteFeedbackById(int feedbackId);
}
