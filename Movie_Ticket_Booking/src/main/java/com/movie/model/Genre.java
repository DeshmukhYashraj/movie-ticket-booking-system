package com.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
	private int genreId;
	private String genreName;

	// Constructor
    public Genre(String genreName) {
        this.genreName = genreName;
    }

    // Getter
    public String getGenreName() {
        return genreName;
    }
}
