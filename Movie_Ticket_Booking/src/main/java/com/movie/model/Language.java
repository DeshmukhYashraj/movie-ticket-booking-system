package com.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Language {

	private Integer langId;
	@NonNull
	private String langName;
}
