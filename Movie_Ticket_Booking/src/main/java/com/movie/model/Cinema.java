package com.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cinema {
	private Integer cinemaId;
	private String cinameName;
	private String cinemaLocation;
	private String cinemaContact;
}
