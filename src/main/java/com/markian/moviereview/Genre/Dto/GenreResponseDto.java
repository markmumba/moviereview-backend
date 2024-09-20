package com.markian.moviereview.Genre.Dto;

import com.markian.moviereview.Movie.Dto.MovieListResponseDto;
import com.markian.moviereview.Movie.Dto.MovieResponseDto;
import com.markian.moviereview.Movie.Movie;

import java.util.List;

public record GenreResponseDto(
        Integer id,
        String name,
        List<MovieListResponseDto> movies
) {
}
