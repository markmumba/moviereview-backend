package com.markian.moviereview.Movie.Dto;

import com.markian.moviereview.Movie.Movie;
import org.springframework.stereotype.Service;

@Service
public class MovieMapper {

    public Movie fromDtoToMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.title());
        movie.setReleasedate(movieDto.releasedate());
        movie.setDirector(movieDto.director());
        movie.setSynopsis(movieDto.synopsis());
        movie.setPosterurl(movieDto.posterurl());

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
                movie.getGenres(),
                movie.getReviews()
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
