package com.movie.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.movie.model.Booking;
import com.movie.model.Cinema;
import com.movie.model.Feedback;
import com.movie.model.Movies;
import com.movie.model.Seat;
import com.movie.model.Showtime;
import com.movie.service.AddCinemaServiceImpl;
import com.movie.service.AddMovieServiceImpl;
import com.movie.service.FeedbackServiceImpl;
import com.movie.service.IAddCinemaService;
import com.movie.service.IAddMovieService;
import com.movie.service.IAddSeatService;
import com.movie.service.IFeedbackService;
import com.movie.service.IShowtimeService;
import com.movie.service.IUserService;
import com.movie.service.SeatServiceImpl;
import com.movie.service.ShowtimeServiceImpl;
import com.movie.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class UserOperation {

	static Scanner scn = new Scanner(System.in);
	static String movieName;
	static String custEmail;
	static int showTimeId;

	static Logger logger = Logger.getLogger(UserOperation.class);

    static {
        try {
            PropertyConfigurator.configure("D:\\workspaces\\ConsoleProject\\Movie_Ticket_Booking\\Movie_Ticket_Booking\\src\\main\\java\\com\\movie\\commons\\Log4j.properties");
            logger.info("Logger configuration successful.");
        } catch (Exception e) {
            System.err.println("Failed to configure logger: " + e.getMessage());
        }
    }
	// Services
	static IAddMovieService movieService = new AddMovieServiceImpl();
	static IAddCinemaService cinemaService = new AddCinemaServiceImpl();
	static IShowtimeService showtimeService = new ShowtimeServiceImpl();
	static IAddSeatService seatService = new SeatServiceImpl();
	static IUserService userService = new UserServiceImpl();
	static IFeedbackService feedbackService = new FeedbackServiceImpl();

	// ANSI escape codes for colors and styles
	private static final String RESET = "\033[0m";
	private static final String BOLD = "\033[1m";
	private static final String GREEN = "\033[32m";
	private static final String CYAN = "\033[36m";
	private static final String YELLOW = "\033[33m";
	private static final String BLUE = "\033[34m";
	private static final String RED = "\033[31m";
	private static final String UNDERLINE = "\033[4m";

	// Method to find movies by language
	public static void finderMovieBylanguage(String email) {
		custEmail = email;
		AdminOperation.showLanguage();
		System.out.println(CYAN + "Select Language: " + RESET);
		String language = scn.nextLine();
		movieService.movieFinderByLanguage(language);
		findCinemaByMovie();
	}

	// Method to find movies by genre
	public static void finderMovieByGenre(String email) {
		custEmail = email;
		AdminOperation.showGenres();
		System.out.println(CYAN + "Select Genre: " + RESET);
		String genreName = scn.nextLine();
		movieService.movieFinderByGenre(genreName);
		findCinemaByMovie();
	}

	// Method to find cinemas showing the selected movie
	public static void findCinemaByMovie() {
		System.out.println(GREEN + "Enter Movie Name: " + RESET);
		movieName = scn.nextLine();
		List<Cinema> listCinema = cinemaService.getCinemaByMovie(movieName);
		System.out.println("\n\n");
		System.out.printf("%-25s %-30s%n", BOLD + "Cinema Name" + RESET, "Cinema Contact");

		listCinema.forEach(cinema -> 
		    System.out.printf("%-25s %-30s%n", cinema.getCinameName(), cinema.getCinemaContact())
		);

		getAllShowtimesByCinema();
	}

	// Method to get all showtimes by cinema
	public static void getAllShowtimesByCinema() {
		System.out.println(GREEN + "Select Cinema Name: " + RESET);
		String cinemaName = scn.nextLine();

		List<Showtime> showList = showtimeService.getAllShowtimesByCinema(movieName, cinemaName);
		System.out.println("\n");
		System.out.printf("%-15s %-15s %-15s %-15s\n", BOLD + "Showtime ID" + RESET, "Show Date", "Start Time", "End Time");

		showList.forEach(showtime -> 
		    System.out.printf("%-15s %-15s %-15s %-15s\n", 
		                      showtime.getShowtimeId(), 
		                      showtime.getShowDate(), 
		                      showtime.getStartTime(), 
		                      showtime.getEndTime())
		);

		System.out.println(YELLOW + "Select Showtime ID: " + RESET);
		showTimeId = scn.nextInt();
		scn.nextLine(); // Consume newline
		List<Seat> seats = seatService.showSeatsAvailable(showTimeId);
		System.out.println("\n");

		System.out.printf("%-10s %-15s %-10s\n", BOLD + "Seat ID" + RESET, "Seat Number", "Available");

		seats.forEach(seat -> 
		    System.out.printf("%-10s %-15s %-10s\n", 
		                      seat.getSeatId(), 
		                      seat.getSeatNumber(), 
		                      (seat.isAvailable() ? "Available" : "Booked"))
		);

		bookSeatBySeatName();
	}

	// Method to book seats by seat name
	public static void bookSeatBySeatName() {
		int custId = userService.getUserIdByEmail(custEmail);

		Booking book = new Booking();
		book.setShowTimeId(showTimeId);
		book.setUserId(custId);

		List<Seat> bookSeat = new ArrayList<Seat>();

		do {
			Seat seat = new Seat();
			System.out.println(GREEN + "Enter Seat Number: " + RESET);
			String seatName = scn.nextLine();
			seat.setSeatNumber(seatName);
			bookSeat.add(seat);

			System.out.println(YELLOW + "Do you want to add more seats? (yes/no): " + RESET);
			if (scn.nextLine().equalsIgnoreCase("no")) {
				break;
			}
		} while (true);
		book.setSeatList(bookSeat);
		boolean b = seatService.bookMySeat(book);
		if (b) {
			System.out.println("\n🎉 Your ticket is successfully booked! 🎉");
			promptFeedback(custId);
			logger.info("New Ticket booked.");
		} else {
			System.out.println("\n❌ Ticket booking failed. Please try again.");
			logger.fatal("Failed to book ticket");
		}
	}

	// Method to prompt for feedback
	public static void promptFeedback(int userId) {
		System.out.println("\nWould you like to provide feedback for the movie? (yes/no)");
		String choice = scn.nextLine();
		if (choice.equalsIgnoreCase("yes")) {
			System.out.println(GREEN + "Enter your feedback for the movie:" + RESET);
			String feedbackText = scn.nextLine();
			System.out.println(GREEN + "Enter your rating (1-5):" + RESET);
			int rating = scn.nextInt();
			scn.nextLine(); // Consume newline

			boolean feedbackSubmitted = feedbackService.submitFeedback(userId, movieName, feedbackText, rating);
			if (feedbackSubmitted) {
				System.out.println("\n🎉 Thank you for your valuable feedback!");
				logger.info("User given feedback.");
			} else {
				System.out.println("\n❌ Feedback submission failed. Please try again later.");
			}
		} else {
			System.out.println("\nThank you for using our service!");
			
		}
	}

	public static void getAllTopRatedMovies() {
		List<Feedback> feedbacks = feedbackService.getTopRatedMovies();

		// Table header
		System.out.printf(BOLD + "%-20s %-30s %-30s" + RESET + "\n", "Movie Name", "Feedback", "Rating");
		System.out.println("--------------------------------------------------------------");
		// Table data
		feedbacks.forEach(fb -> System.out.printf("%-20s %-30s %-30s\n", fb.getMovieName(), fb.getFeedbackText(),
				fb.getRating()));

	}

	public static void getUpcommingMovies() {
		List<Movies> movies = feedbackService.getUpcommingMovies();

		// Table header
		System.out.printf(BOLD + "%-20s %-35s" + RESET + "\n", "Movie Title", "Release Date");
		System.out.println("-------------------------------");

		// Table data
		movies.forEach(movie -> System.out.printf("%-20s %-35s\n", movie.getTitle(), movie.getReleaseDate()));

	}
}
