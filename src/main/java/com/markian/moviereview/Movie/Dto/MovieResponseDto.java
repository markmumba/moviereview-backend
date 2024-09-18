package com.markian.moviereview.Movie.Dto;

import java.time.LocalDateTime;

public record MovieResponseDto(
        String title,
        LocalDateTime releasedate,
        String director,
        String synopsis,
        String posterurl
) {

}
