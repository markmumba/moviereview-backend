package com.markian.moviereview.Movie.impl;

import com.markian.moviereview.Exceptions.MovieNotFoundException;
import com.markian.moviereview.Movie.Dto.MovieDto;
import com.markian.moviereview.Movie.Dto.MovieResponseDto;
import com.markian.moviereview.Movie.Movie;
import com.markian.moviereview.Movie.MovieMapper;
import com.markian.moviereview.Movie.MovieRepository;
import com.markian.moviereview.Movie.MovieService;
import com.markian.moviereview.Exceptions.MovieCreationException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;


    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }


    @Override
    public List<MovieResponseDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movieMapper::fromMovieToDto)
                .toList();
    }

    @Override
    public MovieResponseDto getMovie(Integer id) throws MovieNotFoundException {
        return movieRepository.findById(id)
                .map(movieMapper::fromMovieToDto)
                .orElseThrow(() ->
                        new MovieNotFoundException("Movie with id" + id + "not found "));
    }

    @Override
    public MovieResponseDto createMovie(MovieDto movieDto) throws MovieCreationException {
        Movie movie = movieMapper.fromDtoToMovie(movieDto);
        try {
            return movieMapper.fromMovieToDto(movieRepository.save(movie));
        } catch (DataIntegrityViolationException e) {
            throw new MovieCreationException("Error creating movie" + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public void updateMovie(Integer id, MovieDto updatedMovieDto) throws MovieNotFoundException {

        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() ->
                        new MovieNotFoundException("Movie with id" + id + "not found "));

        if (updatedMovieDto.title() != null) {
            existingMovie.setTitle(updatedMovieDto.title());
        }
        if (updatedMovieDto.posterurl() != null) {
            existingMovie.setPosterurl(updatedMovieDto.posterurl());
        }
        if (updatedMovieDto.synopsis() != null) {
            existingMovie.setSynopsis(updatedMovieDto.synopsis());
        }
        if (updatedMovieDto.director() != null) {
            existingMovie.setDirector(updatedMovieDto.director());
        }
        if (updatedMovieDto.releasedate() != null) {
            existingMovie.setReleasedate(updatedMovieDto.releasedate());
        }
        movieRepository.save(existingMovie);

    }

    @Override
    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }
}
