package com.movie.model;

import java.sql.Date;
import java.sql.Time;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Movies {

	private int movieId;
	private String title;
	private Time duration;
	private Language language; 
	private Date releaseDate;
	private Genre genre; 

	// Constructor with Language and Genre objects
	public Movies(int movieId, String title, Time duration, Language language, Date releaseDate, Genre genre) {
		this.movieId = movieId;
		this.title = title;
		this.duration = duration;
		this.language = language;
		this.releaseDate = releaseDate;
		this.genre = genre;
	}

	public String getLanguage() {
		return language.getLangName(); // assuming `languageName` holds the language name
	}

	public String getGenreName() {
		return genre.getGenreName(); // assuming `genreName` holds the genre name
	}

}
