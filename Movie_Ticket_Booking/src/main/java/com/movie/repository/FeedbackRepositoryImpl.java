package com.movie.repository;

import com.movie.config.DBState;
import com.movie.model.Cinema;
import com.movie.model.Feedback;
import com.movie.model.Movies;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackRepositoryImpl extends DBState implements IFeedbackRepository {

	private static final String ADD_FEEDBACK = "INSERT INTO FEEDBACK (USER_ID, MOVIE_ID, FEEDBACK_TEXT, RATING) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_FEEDBACK = "SELECT * FROM FEEDBACK";
	private static final String GET_FEEDBACK_BY_ID = "SELECT * FROM FEEDBACK WHERE MOVIE_ID = ?";
	private static final String DELETE_FEEDBACK = "DELETE FROM FEEDBACK WHERE FEEDBACK_ID = ?";
	private static final String GET_TOP_RATED_MOVIES = "SELECT M.TITLE, F.FEEDBACK_TEXT, F.RATING FROM FEEDBACK F JOIN MOVIES M ON F.MOVIE_ID = M.MOVIE_ID WHERE F.RATING > 3 ORDER BY F.RATING DESC";
	private static final String GET_UPCOMMING_MOVIES = "SELECT TITLE, RELEASE_DATE FROM MOVIES WHERE RELEASE_DATE > CURDATE() ORDER BY RELEASE_DATE ASC";
	
	@Override
    public boolean submitFeedback(int userId, String movieName, String feedbackText, int rating) {
        // Replace with actual database logic
        try {
            // Example Database Query
            String query = "INSERT INTO feedback (user_id, movie_id, feedback_text, rating) VALUES (?, " +
                    "(SELECT movie_id FROM movies WHERE title = ?), ?, ?)";
            // Assuming `connection` is a valid DB connection
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, movieName);
            ps.setString(3, feedbackText);
            ps.setInt(4, rating);

            return ps.executeUpdate() > 0; // Return true if feedback is successfully inserted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
			ps = con.prepareStatement(DELETE_FEEDBACK);

			ps.setInt(1, feedbackId);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("Error while deleting feedback: " + e.getMessage());
			return false;
		}
	}


	@Override
	public List<Feedback> getTopRatedMovies() {
		List<Feedback> feedbackList = new ArrayList<>();
		try {
			ps = con.prepareStatement(GET_TOP_RATED_MOVIES); 
			rs = ps.executeQuery(); 
			while (rs.next()) {
				Feedback feedback = new Feedback();
				feedback.setMovieName(rs.getString("title"));
				feedback.setFeedbackText(rs.getString("feedback_text"));
				feedback.setRating(rs.getInt("rating"));
				feedbackList.add(feedback);
//				System.out.println("\t"+rs.getString("title")+"\t"+rs.getString("feedback_text")+"\t"+rs.getInt("rating"));
			}
		} catch (SQLException e) {
			System.err.println("Error while fetching feedback"); // Use logging
		}
		return feedbackList;
	}


	@Override
	public List<Movies> getUpcommingMovies() {
		List<Movies> movies = new ArrayList<>();
		try {
			ps = con.prepareStatement(GET_UPCOMMING_MOVIES); 
			rs = ps.executeQuery(); 
			while (rs.next()) {
				Movies movie = new Movies();
				movie.setTitle(rs.getString("TITLE"));
				movie.setReleaseDate(rs.getDate("RELEASE_DATE"));
				movies.add(movie);
			}
		} catch (SQLException e) {
			System.err.println("Error while fetching feedback"); // Use logging
		}
		return movies;
	}

}
