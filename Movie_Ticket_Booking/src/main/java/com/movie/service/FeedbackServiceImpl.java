package com.movie.service;

import java.util.List;

import com.movie.model.Feedback;
import com.movie.model.Movies;
import com.movie.repository.FeedbackRepositoryImpl;
import com.movie.repository.IFeedbackRepository;

public class FeedbackServiceImpl implements IFeedbackService{

	IFeedbackRepository feedbackRepo = new FeedbackRepositoryImpl();

	@Override
	public boolean submitFeedback(int userId, String movieName, String feedbackText, int rating) {
		return feedbackRepo.submitFeedback(userId, movieName, feedbackText, rating);
	}

	@Override
	public List<Feedback> getFeedbackByMovieId(int movieId) {
		return feedbackRepo.getFeedbackByMovieId(movieId);
	}

	@Override
	public boolean deleteFeedbackById(int feedbackId) {
		return feedbackRepo.deleteFeedbackById(feedbackId);
	}

	@Override
	public List<Feedback> getTopRatedMovies() {
		return feedbackRepo.getTopRatedMovies();
	}

	@Override
	public List<Movies> getUpcommingMovies() {
		return feedbackRepo.getUpcommingMovies();
	}

	
	
	
}
