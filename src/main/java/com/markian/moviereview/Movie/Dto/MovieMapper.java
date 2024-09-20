package com.markian.moviereview.Movie.Dto;

import com.markian.moviereview.Genre.Dto.GenreDto;
import com.markian.moviereview.Genre.Dto.GenreListResponseDto;
import com.markian.moviereview.Genre.Dto.GenreMapper;
import com.markian.moviereview.Genre.Dto.GenreResponseDto;
import com.markian.moviereview.Movie.Movie;
import com.markian.moviereview.Review.Dto.ReviewListResponseDto;
import com.markian.moviereview.Review.Dto.ReviewResponseDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class MovieMapper {

    private <T, R> List<R> mapList(List<T> sourceList, Function<T, R> mapper) {
        return Optional.ofNullable(sourceList)
                .orElse(Collections.emptyList())
                .stream()
                .map(mapper)
                .toList();
    }

    public Movie fromDtoToMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.title());
        movie.setReleasedate(movieDto.releasedate());
        movie.setDirector(movieDto.director());
        movie.setSynopsis(movieDto.synopsis());
        movie.setPosterurl(movieDto.posterurl());
        movie.setGenres(movieDto.genres());
        return movie;
    }

    public MovieResponseDto fromMovieToDto(Movie movie) {
        return new MovieResponseDto(
                movie.getId(),
                movie.getTitle(),
                movie.getReleasedate(),
                movie.getDirector(),
                movie.getSynopsis(),
                movie.getPosterurl(),
                mapList(movie.getGenres(), genre -> new GenreListResponseDto(genre.getId(), genre.getName())),
                mapList(movie.getReviews(), review -> new ReviewListResponseDto(review.getId(), review.getRating(), review.getReviewtext()))
        );
    }

    public MovieListResponseDto fromMovieToDtoList(Movie movie) {
        return new MovieListResponseDto(
                movie.getId(),
                movie.getTitle(),
                movie.getReleasedate(),
                movie.getDirector(),
                movie.getSynopsis(),
                movie.getPosterurl()
        );
    }
}
