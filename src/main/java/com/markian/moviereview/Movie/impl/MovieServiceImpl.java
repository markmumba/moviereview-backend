package com.markian.moviereview.Movie.impl;

import com.markian.moviereview.Exceptions.MovieNotFoundException;
import com.markian.moviereview.Movie.Dto.MovieDto;
import com.markian.moviereview.Movie.Dto.MovieListResponseDto;
import com.markian.moviereview.Movie.Dto.MovieResponseDto;
import com.markian.moviereview.Movie.Movie;
import com.markian.moviereview.Movie.Dto.MovieMapper;
import com.markian.moviereview.Movie.MovieRepository;
import com.markian.moviereview.Movie.MovieService;
import com.markian.moviereview.Exceptions.MovieCreationException;
import jakarta.transaction.Transactional;
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
    public List<MovieListResponseDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movieMapper::fromMovieToDtoList)
                .toList();
    }

    public Movie getMovieEntity(Integer id) throws MovieNotFoundException {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + id + " not found"));
    }

    @Override
    public MovieResponseDto getMovie(Integer id) throws MovieNotFoundException {
        return movieRepository.findById(id)
                .map(movieMapper::fromMovieToDto)
                .orElseThrow(() ->
                        new MovieNotFoundException("Movie with id " + id + " not found "));
    }

    @Override
    public MovieResponseDto createMovie(MovieDto movieDto) {

        if (movieRepository.existsByTitle(movieDto.title())) {
            throw new MovieCreationException("A movie with the title " + movieDto.title() + " already exists.");
        }

        Movie movie = movieMapper.fromDtoToMovie(movieDto);
        return movieMapper.fromMovieToDto(movieRepository.save(movie));
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
