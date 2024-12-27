package com.movie.repository;

import com.movie.model.Feedback;

import java.sql.SQLException;
import java.util.List;

public interface IFeedbackRepository {
	
    // Add a new feedback
	 public boolean addFeedback(Feedback feedback);

	    // Retrieve all feedbacks
	 public List<Feedback> getAllFeedback();

	    // Retrieve feedback by movie ID
	 public List<Feedback> getFeedbackByMovieId(int movieId);

	    // Delete feedback by ID
	 public boolean deleteFeedbackById(int feedbackId);
}
