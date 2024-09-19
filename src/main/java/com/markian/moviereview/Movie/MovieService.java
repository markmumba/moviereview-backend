package com.markian.moviereview.Movie;

import com.markian.moviereview.Exceptions.MovieNotFoundException;
import com.markian.moviereview.Movie.Dto.MovieDto;
import com.markian.moviereview.Movie.Dto.MovieListResponseDto;
import com.markian.moviereview.Movie.Dto.MovieResponseDto;
import com.markian.moviereview.Exceptions.MovieCreationException;

import java.util.List;

public interface MovieService {
    List<MovieListResponseDto> getAllMovies();

    Movie getMovieEntity(Integer id) throws MovieNotFoundException;

    MovieResponseDto getMovie(Integer id) throws MovieNotFoundException;

    MovieResponseDto createMovie(MovieDto movieDto) throws MovieCreationException;

    void updateMovie(Integer id, MovieDto movieDto) throws MovieNotFoundException;

    void deleteMovie(Integer id);
}
