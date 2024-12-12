package com.movie.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Movies {

	private Integer movieId;
	private String movieName;
	private String movieGenre;
	private LocalDateTime duration;
	private Integer languageId;
	private LocalDateTime date;

}
