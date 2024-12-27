package com.movie.service;

import java.util.List;

import com.movie.model.Feedback;

public interface IFeedbackService {

			// Add a new feedback
		 public boolean addFeedback(Feedback feedback);

		    // Retrieve all feedbacks
		 public List<Feedback> getAllFeedback();

		    // Retrieve feedback by movie ID
		 public List<Feedback> getFeedbackByMovieId(int movieId);

		    // Delete feedback by ID
		 public boolean deleteFeedbackById(int feedbackId);
}
