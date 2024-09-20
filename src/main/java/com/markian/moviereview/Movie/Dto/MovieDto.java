package com.markian.moviereview.Movie.Dto;

import com.markian.moviereview.Genre.Dto.GenreListResponseDto;
import com.markian.moviereview.Genre.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record MovieDto(
        @NotNull(message = "Movie must have a title")
        String title,

        @NotNull(message = "Movie should have the release date")
        LocalDateTime releasedate,

        @NotNull(message = "The movie should have the director")
        String director,

        String synopsis,

        String posterurl,

        List<GenreListResponseDto> genres
) {
}
