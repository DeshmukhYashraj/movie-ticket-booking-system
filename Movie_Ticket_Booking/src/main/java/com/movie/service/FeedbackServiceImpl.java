package com.movie.service;

import java.util.List;

import com.movie.model.Feedback;
import com.movie.repository.FeedbackRepositoryImpl;
import com.movie.repository.IFeedbackRepository;

public class FeedbackServiceImpl implements IFeedbackService{

	IFeedbackRepository feedbackRepo = new FeedbackRepositoryImpl();

	@Override
	public boolean addFeedback(Feedback feedback) {
		return feedbackRepo.addFeedback(feedback);
	}

	@Override
	public List<Feedback> getAllFeedback() {
		return feedbackRepo.getAllFeedback();
	}

	@Override
	public List<Feedback> getFeedbackByMovieId(int movieId) {
		return feedbackRepo.getFeedbackByMovieId(movieId);
	}

	@Override
	public boolean deleteFeedbackById(int feedbackId) {
		return feedbackRepo.deleteFeedbackById(feedbackId);
	}
	
	
}
