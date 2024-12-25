package com.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Language {

	private Integer langId;
	private String langName;
	
	 // Constructor
    public Language(String langName) {
        this.langName = langName;
    }

    // Getter
    public String getLangName() {
        return langName;
    }
}
