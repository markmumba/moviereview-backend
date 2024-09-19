package com.markian.moviereview.Movie.Dto;

import java.time.LocalDateTime;

public record MovieListResponseDto(
        Integer id,
        String title,
        LocalDateTime releasedate,
        String director,
        String synopsis,
        String posterurl
) {
}
