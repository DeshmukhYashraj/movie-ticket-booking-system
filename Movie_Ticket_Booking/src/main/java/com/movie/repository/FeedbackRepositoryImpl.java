package com.movie.repository;

import com.movie.config.DBState;
import com.movie.model.Cinema;
import com.movie.model.Feedback;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackRepositoryImpl extends DBState implements IFeedbackRepository {

	private static final String ADD_FEEDBACK = "INSERT INTO FEEDBACK (USER_ID, MOVIE_ID, FEEDBACK_TEXT, RATING) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_FEEDBACK = "SELECT * FROM FEEDBACK";
	private static final String GET_FEEDBACK_BY_ID = "SELECT * FROM FEEDBACK WHERE MOVIE_ID = ?";
	private static final String DELETE_FEEDBACK = "DELETE FROM FEEDBACK WHERE FEEDBACK_ID = ?";
	
	
	@Override
	public boolean addFeedback(Feedback feedback) {
		try {
			ps = con.prepareStatement(ADD_FEEDBACK);
			ps.setInt(1, feedback.getUser().getUserId());
			ps.setInt(2, feedback.getMovie().getMovieId());
			ps.setString(3, feedback.getFeedbackText());
			ps.setInt(4, feedback.getRating());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("Error while adding feedback: " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Feedback> getAllFeedback() {
		List<Feedback> feedbackList = new ArrayList<>();

		try {
			ps = con.prepareStatement(GET_ALL_FEEDBACK);
			rs = ps.executeQuery();

			while (rs.next()) {
				Feedback feedback = new Feedback();
				feedback.setFeedbackId(rs.getInt("feedback_id"));
				feedback.setFeedbackText(rs.getString("feedback_text"));
				feedback.setRating(rs.getInt("rating"));

				feedbackList.add(feedback);
			}
		} catch (SQLException e) {
			System.err.println("Error while fetching all feedbacks: " + e.getMessage());
		}
		return feedbackList;
	}

	@Override
	public List<Feedback> getFeedbackByMovieId(int movieId) {
	    List<Feedback> feedbackList = new ArrayList<>();
	    try {
	        ps = con.prepareStatement(GET_FEEDBACK_BY_ID); // Use the inherited `PreparedStatement`
	        ps.setInt(1, movieId);
	        rs = ps.executeQuery(); // Use the inherited `ResultSet`
	        while (rs.next()) {
	            Feedback feedback = new Feedback();
	            feedback.setFeedbackId(rs.getInt("feedback_id"));
	            feedback.setFeedbackText(rs.getString("feedback_text"));
	            feedback.setRating(rs.getInt("rating"));
	            feedbackList.add(feedback);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error while fetching feedback by movie Id"); // Use logging
	    }
	    return feedbackList;
	}

	
	
	@Override
	public boolean deleteFeedbackById(int feedbackId) {
		try {
			ps= con.prepareStatement(DELETE_FEEDBACK);

			ps.setInt(1, feedbackId);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("Error while deleting feedback: " + e.getMessage());
			return false;
		}
	}
}
