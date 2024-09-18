package com.markian.moviereview.Movie;

import com.markian.moviereview.Movie.Dto.MovieDto;
import com.markian.moviereview.Movie.Dto.MovieResponseDto;
import org.springframework.stereotype.Service;

@Service
public class MovieMapper {

    public Movie fromDtoToMovie (MovieDto movieDto) {
        if (movieDto == null ) {
            throw new NullPointerException("this should not be null");
        }
        Movie movie = new Movie();
        movie.setTitle(movieDto.title());
        movie.setReleasedate(movieDto.releasedate());
        movie.setDirector(movieDto.director());
        movie.setSynopsis(movieDto.synopsis());
        movie.setPosterurl(movieDto.posterurl());

        return movie;
    }

    public MovieResponseDto fromMovieToDto (Movie movie) {
        return new MovieResponseDto(
                movie.getTitle(),
                movie.getReleasedate(),
                movie.getDirector(),
                movie.getSynopsis(),
                movie.getPosterurl()
        );
    }
}
