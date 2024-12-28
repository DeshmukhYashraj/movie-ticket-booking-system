package com.movie.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.movie.client.MovieTicketBookingApp;
import com.movie.model.Cinema;
import com.movie.model.Feedback;
//import com.movie.config.LoggerApp;
import com.movie.model.Genre;
import com.movie.model.Language;
import com.movie.model.Movies;
import com.movie.model.Seat;
import com.movie.model.Showtime;
import com.movie.model.UserModel;
import com.movie.operator.UserLoginOperations;
import com.movie.operator.UserSession;
import com.movie.repository.AddCinemaRepositoryImpl;
import com.movie.repository.IAddCinemaRepository;
import com.movie.repository.IFeedbackRepository;
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

public class AdminOperation {
	
	static Logger logger = Logger.getLogger(AdminOperation.class);

	static Scanner scn = new Scanner(System.in);
	static IUserService userService = new UserServiceImpl();
	static IAddMovieService movieService = new AddMovieServiceImpl();
	static IAddCinemaService cinemaService = new AddCinemaServiceImpl();
	static IAddSeatService seatService = new SeatServiceImpl();
	static IShowtimeService showtimeService = new ShowtimeServiceImpl();
    static IFeedbackService feedbackService = new FeedbackServiceImpl();

    
	// Method for adding a new language
	public static void addLanguage() {
		showLanguage();
		System.out.println("Enter Language Name :: ");
		String langName = scn.nextLine();
		Language lang = new Language(langName);
		int value = movieService.addMovieLanguage(lang);
		System.out.println(value > 0 ? "\nLanguage Added Successfully.." : "\nLanguage not added..");
		logger.info("Language saved message.");
	}

	// Method for displaying all languages
	public static void showLanguage() {
	    System.out.println("============================================");
	    System.out.println("       ::   Language Details Are ::           ");
	    System.out.println("============================================");
	    
	    // Print table header with border for better design
	    System.out.printf("%-10s %-20s\n", "Lang ID", "Language Name");
	    System.out.println("--------------------------------------------");
	    
	    // Fetch language data
	    List<Language> languages = movieService.getAllLanguage();
	    
	    if (languages != null) {
	        // Print each language data in a formatted manner
	        languages.forEach((lang) -> 
	            System.out.printf("%-10d %-20s\n", lang.getLangId(), lang.getLangName())
	        );
	        
	    } else {
	        // If no data is found
	        System.out.println("No Record Found.");
	    }
	    
	    System.out.println("============================================");
	}


	// Method for updating an existing language
	public static void updateLanguage() {
		showLanguage();
		System.out.println("Enter Old Language Name :: ");
		String oldName = scn.nextLine();
		System.out.println("Enter New Language Name :: ");
		String newName = scn.nextLine();
		int value = movieService.updateMovieLanguage(oldName, newName);
		System.out.println(value > 0 ? "Language UPDATED Successfully.." : "Language not updated..");
		logger.info("Language name updated started.");

	}

	// Method for deleting a language
	public static void deleteLanguage() {
		showLanguage();
		System.out.println("Enter Language Name :: ");
		String langName = scn.nextLine();
		int value = movieService.removeMovieLanguage(new Language(langName));
		System.out.println(value > 0 ? "Language Deleted Successfully.." : "Language not deleted..");
	}

	// Method for adding a new genre
	public static void addGenre() {
		showGenres();
		System.out.println("Enter Genre Name :: ");
		String genreName = scn.nextLine();
		Genre genre = new Genre();
		genre.setGenreName(genreName);// Assuming Genre constructor is similar to Language
		int value = movieService.addMovieGenre(genre);
		System.out.println(value > 0 ? "Genre Added Successfully.." : "Genre not added..");
	}

	// Method for displaying all genres
	public static void showGenres() {
		System.out.println("=========================");
		System.out.println("Genre Details Are :: ");
		List<Genre> genres = movieService.getAllGenres(); // Assuming method exists in service
		if (genres != null) {
//			genres.forEach((genre)->System.out.println(genre.getGenreId()+"\t"+genre.getGenreName()));
			genres.forEach((genre) -> System.out.printf("%-10d %-20s\n", 
                    genre.getGenreId(), 
                    genre.getGenreName()));

		} else {
			System.out.println("No Genre Found");
//			logger.info("No Genre Found...");
		}
		System.out.println("=========================");
	}

	// Method for updating an existing genre
	public static void updateGenre() {
		showGenres();
		System.out.println("Enter Old Genre Name :: ");
		String oldName = scn.nextLine();
		System.out.println("Enter New Genre Name :: ");
		String newName = scn.nextLine();
		int value = movieService.updateMovieGenre(oldName, newName);
		System.out.println(value > 0 ? "Genre UPDATED Successfully.." : "Genre not updated..");
	}

	// Method for deleting a genre
	public static void deleteGenre() {
		showGenres();
		System.out.println("Enter Genre Name :: ");
		String genreName = scn.nextLine();
		Genre genre = new Genre();
		genre.setGenreName(genreName);
		int value = movieService.removeMovieGenre(genre);
		System.out.println(value > 0 ? "Genre Deleted Successfully.." : "Genre not deleted..");
	}

	// Method for add movie
	public static void addMovies() {
		showMovies();
		System.out.println("Enter Movie Title: ");
		String title = scn.nextLine();

		System.out.println("Enter Movie Duration (hh:mm:ss): ");
		String duration = scn.nextLine();
		Time timeDuration = Time.valueOf(duration);

		System.out.println("Enter Language Name: ");
		String languageName = scn.nextLine();
		Language lang = new Language(languageName);

		System.out.println("Enter Release Date (YYYY-MM-DD): ");
		String releaseDate = scn.nextLine();
		Date dateRelease = Date.valueOf(releaseDate); // Convert String to Date

		System.out.println("Enter Genre Name: ");
		String genreName = scn.nextLine();
		Genre gen = new Genre();
		gen.setGenreName(genreName);

		Movies movie = new Movies(0, title, timeDuration, lang, dateRelease, gen);

		int value = movieService.addMovie(movie);

		System.out.println(value > 0 ? "Movie Added Successfully!" : "Movie could not be added.");
	}

	// Method for show all movies
	public static void showMovies() {
		System.out.println("=========================");
		System.out.println("Movie Details: ");
		List<Movies> movies = movieService.getAllMovies(); // Fetches all movies from the database
		if (movies != null && !movies.isEmpty()) {
//			movies.forEach((mov)->System.out.println(mov.getMovieId()+"\t"+mov.getTitle()+"\t\t"+mov.getLanguage()+"\t\t"+mov.getDuration()));
			movies.forEach((mov) -> System.out.printf("%-10d %-30s %-15s %-10s\n", 
                    mov.getMovieId(), 
                    mov.getTitle(), 
                    mov.getLanguage(), 
                    mov.getDuration()));
		} else {
			System.out.println("No Movies Found");
//			logger.info("No Movies Found.");
		}
		System.out.println("=========================");
	}
	// Method for update movie details
	public static void updateMovie() {
		showMovies();

		System.out.println("Enter Movie ID to Update: ");
		int movieId = scn.nextInt();
		scn.nextLine();

		System.out.println("Enter New Movie Title: ");
		String newTitle = scn.nextLine();

		System.out.println("Enter New Movie Duration (hh:mm:ss): ");
		String newDuration = scn.nextLine();
		Time duration = Time.valueOf(newDuration);

		System.out.println("Enter New Language Name: ");
		String newLanguageName = scn.nextLine();
		Language lang = new Language(newLanguageName);

		System.out.println("Enter New Release Date (YYYY-MM-DD): ");
		String newReleaseDate = scn.nextLine();
		Date releaseDate = Date.valueOf(newReleaseDate);

		System.out.println("Enter New Genre Name: ");
		String newGenreName = scn.nextLine();
		Genre genre = new Genre();
		genre.setGenreName(newGenreName);

		Movies updatedMovie = new Movies(movieId, newTitle, duration, lang, releaseDate, genre);

		int value = movieService.updateMovie(updatedMovie);

		System.out.println(value > 0 ? "Movie Updated Successfully!" : "Movie could not be updated.");
	}

	// Method for delete movie
	public static void deleteMovie() {
		showMovies();
		System.out.println("Enter Movie ID to Delete: ");
		int movieId = scn.nextInt();

		// Assuming delete method in movieService
		int value = movieService.removeMovie(movieId);

		System.out.println(value > 0 ? "Movie Deleted Successfully!" : "Movie could not be deleted.");
	}

	// Method for add cinema
	public static void addCinema() {
		showCinemas();
		System.out.println("Enter cinema name: ");
		String cinemaName = scn.nextLine();

		System.out.println("Enter cinema location: ");
		String cinemaLoc = scn.nextLine();

		System.out.println("Enter cinema contact number: ");
		String cinemaCont = scn.nextLine();

		Cinema cinema = new Cinema(0, cinemaName, cinemaLoc, cinemaCont);

		int value = cinemaService.addCinema(cinema);

		System.out.println(value > 0 ? "Cinema Added Successfully!" : "Cinema could not be added.");
	}

	// Method for show all cinemas
	public static void showCinemas() {
		System.out.println("=========================");
		System.out.println("Cinema Details Are :: ");

		List<Cinema> cinemas = cinemaService.getAllCinema();
		if (cinemas != null && !cinemas.isEmpty()) {
//			cinemas.forEach((cinema)->System.out.println(cinema.getCinemaId()+"\t"+cinema.getCinameName()+"\t"+cinema.getCinemaContact()+"\t"+cinema.getCinemaLocation()));
			cinemas.forEach(cinema -> 
		    System.out.printf("%-10d %-20s %-15s %-30s\n", 
		                      cinema.getCinemaId(),
		                      cinema.getCinameName(),
		                      cinema.getCinemaContact(),
		                      cinema.getCinemaLocation()));
		} else {
			System.out.println("No Cinemas Found.");
		}
		System.out.println("=========================");
	}

	// Method for upadte cinema details
	public static void updateCinema() {
		showCinemas();

		System.out.println("Enter Cinema ID to Update: ");
		int cinemaId = scn.nextInt();
		scn.nextLine(); // Clear buffer

		System.out.println("Enter Updated Cinema Name: ");
		String name = scn.nextLine();

		System.out.println("Enter Updated Cinema Location: ");
		String location = scn.nextLine();

		System.out.println("Enter Updated Cinema Contact: ");
		String contact = scn.nextLine();

		Cinema cinema = new Cinema(cinemaId, name, location, contact);

		int value = cinemaService.updateCinema(cinema);

		System.out.println(value > 0 ? "Cinema Updated Successfully!" : "Cinema could not be updated.");
	}

	// Method for delete cinema
	public static void deleteCinema() {
		showCinemas();
		System.out.println("Enter Cinema ID to Remove: ");
		int cinemaId = scn.nextInt();
		scn.nextLine();
		int value = cinemaService.removeCinema(cinemaId);
		System.out.println(value > 0 ? "Cinema Removed Successfully!" : "Cinema could not be removed.");
	}

	// Method to add a new showtime
	public static void addShowtime() {
		try {
			showCinemas();
			System.out.println("Enter Cinema ID: ");
			int cinemaId = scn.nextInt();
			scn.nextLine();

			// Fetch Cinema details
			Cinema cinema = cinemaService.getCinemaById(cinemaId);
			if (cinema == null) {
				System.out.println("Invalid Cinema ID.");
				return;
			}

			showMovies();
			System.out.println("Enter Movie ID: ");
			int movieId = scn.nextInt();
			scn.nextLine();

			// Fetch Movie details
			Movies movie = movieService.getMovieById(movieId);
			if (movie == null) {
				System.out.println("Invalid Movie ID.");
				return;
			}

			System.out.println("Enter Show Date (YYYY-MM-DD): ");
			String dateInput = scn.nextLine();
 
			System.out.println("Enter Show Start Time (hh:mm:ss): ");
			String startTimeInput = scn.nextLine();
			
			System.out.println("Enter Show End Time (hh:mm:ss): ");
			String endTimeInput = scn.nextLine();			

			Showtime showtime = new Showtime(0, movieId, cinemaId, dateInput, startTimeInput, endTimeInput, movie, cinema);
			int result = showtimeService.addShowtime(showtime);
			System.out.println(result > 0 ? "Showtime added successfully!" : "Failed to add showtime.");
		} catch (Exception e) {
			System.out.println("Error while adding showtime: " + e.getMessage());
		}
	}

	// Method to show all showtimes
	public static void showAllShowtimes() {
		try {
			System.out.println("=========================");
			List<Showtime> showtimes = showtimeService.getAllShowtimes();
			if (showtimes.isEmpty()) {
				System.out.println("No showtimes found.");
			} else {
//				showtimes.forEach((show) -> show.getCinemaId()+""+show.getMovieId()+""+show.getShowDate()+""+show.getStartTime()+""+show.getEndTime());
				showtimes.forEach((show) -> 
			    System.out.printf("%-10d %-10d %-15s %-10s %-10s\n", 
			                      show.getCinemaId(),
			                      show.getMovieId(),
			                      show.getShowDate(),
			                      show.getStartTime(),
			                      show.getEndTime()));

				System.out.println("=========================");

			}
		} catch (Exception e) {
			System.out.println("Error while fetching showtimes: " + e.getMessage());
		}
	}

	// Mthod to get showtime by Id
	public static void getShowtimeById() {
		try {
			System.out.println("Enter Showtime ID: ");
			int showtimeId = scn.nextInt();
			scn.nextLine();

			Showtime showtime = showtimeService.getShowtimeById(showtimeId);
			if (showtime != null) {
				System.out.println(showtime);
			} else {
				System.out.println("No showtime found with the given ID.");
			}
		} catch (Exception e) {
			System.out.println("Error while fetching showtime: " + e.getMessage());
		}
	}

	// Method to update showtime
	public static void updateShowtime() {
		try {
			showAllShowtimes();
			System.out.println("Enter Showtime ID to update: ");
			int showtimeId = scn.nextInt();
			scn.nextLine();

			Showtime existingShowtime = showtimeService.getShowtimeById(showtimeId);
			if (existingShowtime == null) {
				System.out.println("Showtime with given ID not found.");
				return;
			}

			System.out.println("Enter New Cinema ID (Current: " + existingShowtime.getCinemaId() + "): ");
			int cinemaId = scn.nextInt();
			scn.nextLine();

			System.out.println("Enter New Movie ID (Current: " + existingShowtime.getMovieId() + "): ");
			int movieId = scn.nextInt();
			scn.nextLine();

			System.out.println("Enter New Show Date (YYYY-MM-DD) (Current: " + existingShowtime.getShowDate() + "): ");
			String showDate = scn.nextLine();

			System.out.println(
					"Enter New Show Start Time (hh:mm:ss) (Current: " + existingShowtime.getStartTime() + "): ");
			String showStartTime = scn.nextLine();

			System.out.println("Enter New Show End Time (hh:mm:ss) (Current: " + existingShowtime.getEndTime() + "): ");
			String showEndTime = scn.nextLine();

			Movies movie = new Movies();
			Cinema cinema = new Cinema();
			Showtime updatedShowtime = new Showtime(showtimeId, movieId, cinemaId, showDate, showStartTime, showEndTime,movie,cinema);
			updatedShowtime.setShowtimeId(showtimeId);
			updatedShowtime.setMovieId(movieId);
			updatedShowtime.setCinemaId(cinemaId);
			updatedShowtime.setShowDate(showDate);
			updatedShowtime.setStartTime(showStartTime);
			updatedShowtime.setEndTime(showEndTime);
			int result = showtimeService.updateShowtime(updatedShowtime);
			System.out.println(result > 0 ? "Showtime updated successfully!" : "Failed to update showtime.");
		} catch (Exception e) {
			System.out.println("Error while updating showtime: " + e.getMessage());
		}
	}

	// Method to deleye showtime
	public static void deleteShowtime() {
		try {
			System.out.println("Enter Showtime ID to delete: ");
			int showtimeId = scn.nextInt();
			scn.nextLine();

			int result = showtimeService.deleteShowtime(showtimeId);
			System.out.println(result > 0 ? "Showtime deleted successfully!" : "Failed to delete showtime.");
		} catch (Exception e) {
			System.out.println("Error while deleting showtime: " + e.getMessage());
		}
	}

	// Method to add a seat for a specific showtime
	public static void addSeat() {
		showAllShowtimes();
		System.out.println("Enter Showtime ID to Add Seat: ");
		int showtimeId = scn.nextInt();
		scn.nextLine(); // Clear buffer

		System.out.println("Enter Seat Number: ");
		String seatNumber = scn.nextLine();

		System.out.println("Is the Seat Available? (true/false): ");
		boolean isAvailable = scn.nextBoolean();
		scn.nextLine(); // Clear buffer

		Seat seat = new Seat(0, showtimeId, seatNumber, isAvailable);

		int value = seatService.addSeat(seat);

		System.out.println(value > 0 ? "Seat Added Successfully!" : "Seat could not be added.");
	}

	// Method to show all seats for a specific showtime
	public static void showSeats() {
		showAllShowtimes();
		System.out.println("Enter Showtime ID to View Seats: ");
		int showtimeId = scn.nextInt();
		scn.nextLine(); // Clear buffer
		System.out.println("=========================");

		List<Seat> seats = seatService.getSeatsByShowtimeId(showtimeId);

		System.out.println("Seats for Showtime ID: " + showtimeId);
		if (seats != null && !seats.isEmpty()) {
//			seats.forEach((seat) -> System.out.println(seat.getSeatId()+""+""+seat.getSeatNumber())+""+seat.getShowtimeId()+""+seat.isAvailable());
			seats.forEach((seat) -> 
		    System.out.printf("%-10d %-10s %-15d %-10b\n", 
		                      seat.getSeatId(), 
		                      seat.getSeatNumber(),
		                      seat.getShowtimeId(),
		                      seat.isAvailable()));

		} else {
			System.out.println("No Seats Found for this Showtime.");
		}
		System.out.println("=========================");
	}

	// Method to update seat availability
	public static void updateSeatAvailability() {
		showSeats();

		System.out.println("Enter Seat ID to Update Availability: ");
		int seatId = scn.nextInt();
		scn.nextLine(); // Clear buffer

		System.out.println("Is the Seat Available? (true/false): ");
		boolean isAvailable = scn.nextBoolean();
		scn.nextLine(); // Clear buffer

		int value = seatService.updateSeatAvailability(seatId, isAvailable);

		System.out.println(
				value > 0 ? "Seat Availability Updated Successfully!" : "Seat Availability could not be updated.");
	}

	// Method to delete a seat
	public static void deleteSeat() {
		showSeats();

		System.out.println("Enter Seat ID to Remove: ");
		int seatId = scn.nextInt();
		scn.nextLine(); // Clear buffer

		int value = seatService.deleteSeat(seatId);

		System.out.println(value > 0 ? "Seat Removed Successfully!" : "Seat could not be removed.");
	}

	    
	

}
