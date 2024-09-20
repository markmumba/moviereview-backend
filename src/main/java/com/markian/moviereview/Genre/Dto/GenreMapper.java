package com.markian.moviereview.Genre.Dto;

import com.markian.moviereview.Genre.Genre;
import com.markian.moviereview.Movie.Dto.MovieMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GenreMapper {
    private MovieMapper movieMapper;

    public GenreMapper(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    public Genre fromDtoToGenre(GenreDto genreDto) {
        Genre genre = new Genre();
        genre.setName(genreDto.name());
        return genre;
    }

    public GenreResponseDto fromGenreToDto(Genre genre) {
        return new GenreResponseDto(
                genre.getId(),
                genre.getName(),
                genre.getMovies() != null ? // Check if movies is null
                        genre.getMovies().stream()
                                .map(movieMapper::fromMovieToDtoList)
                                .toList()
                        : Collections.emptyList()
        );
    }

    public GenreListResponseDto fromGenreToDtoList(Genre genre) {
        return new GenreListResponseDto(
                genre.getId(),
                genre.getName()
        );
    }
}
