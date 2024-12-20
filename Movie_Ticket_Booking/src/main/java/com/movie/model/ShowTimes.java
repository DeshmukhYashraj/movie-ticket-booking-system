package com.movie.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowTimes {
	private Integer showTimeId;
	private LocalDate showDate;
	private LocalTime startTime;
	private LocalTime endTime;
}
