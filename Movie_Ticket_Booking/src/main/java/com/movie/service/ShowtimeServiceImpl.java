package com.movie.service;

import java.sql.SQLException;
import java.util.List;

import com.movie.model.Showtime;
import com.movie.repository.IShowtimeRepository;
import com.movie.repository.ShowtimeRepositoryImpl;
import com.movie.service.IShowtimeService;

public class ShowtimeServiceImpl implements IShowtimeService {

    IShowtimeRepository showtimeRepo = new ShowtimeRepositoryImpl();

    // Constructor
    public ShowtimeServiceImpl() {
        this.showtimeRepo = new ShowtimeRepositoryImpl();
    }

    @Override
    public int addShowtime(Showtime showtime) throws SQLException {
        return showtimeRepo.addShowtime(showtime);
    }

    @Override
    public List<Showtime> getAllShowtimes() throws SQLException {
        return showtimeRepo.getAllShowtimes();
    }

    @Override
    public Showtime getShowtimeById(int showtimeId) throws SQLException {
        return showtimeRepo.getShowtimeById(showtimeId);
    }

    @Override
    public int updateShowtime(Showtime showtime) throws SQLException {
        return showtimeRepo.updateShowtime(showtime);
    }

    @Override
    public int deleteShowtime(int showtimeId) throws SQLException {
        return showtimeRepo.deleteShowtime(showtimeId);
    }

	@Override
	public List<Showtime> getAllShowtimesByCinema(String movieName, String cinemaName) {
		return showtimeRepo.getAllShowtimesByCinema(movieName, cinemaName);
	}
}
