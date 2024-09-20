package com.markian.moviereview.Movie.Dto;

import com.markian.moviereview.Genre.Genre;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record MovieDto(

        @NotNull(message = "Movie must have a title")
        String title,

        @NotNull(message = "Movie should have the release date ")
        LocalDateTime releasedate,

        @NotNull(message = "The movie should have the director")
        String director,

        String synopsis,

        String posterurl,

        List<Genre> genres

) {
}
