package com.markian.moviereview.Movie.impl;

import com.markian.moviereview.Exceptions.MovieNotFoundException;
import com.markian.moviereview.Genre.Genre;
import com.markian.moviereview.Genre.GenreRepository;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final GenreRepository genreRepository;


    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.genreRepository = genreRepository;
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
        if (updatedMovieDto.genres() != null) {
            existingMovie.setGenres(updatedMovieDto.genres());
        }
        movieRepository.save(existingMovie);

    }
    @Transactional
    @Override
    public void updateMovieGenres(Integer movieId, List<Integer> genreIds) throws MovieNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + movieId + " not found"));
        List<Genre> genres = genreRepository.findAllById(genreIds);
        movie.setGenres(new ArrayList<>(new HashSet<>(genres)));
        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }
}
