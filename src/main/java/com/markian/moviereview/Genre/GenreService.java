package com.markian.moviereview.Genre;

import com.markian.moviereview.Exceptions.GenreCreationException;
import com.markian.moviereview.Exceptions.GenreNotFoundException;
import com.markian.moviereview.Genre.Dto.GenreDto;
import com.markian.moviereview.Genre.Dto.GenreListResponseDto;
import com.markian.moviereview.Genre.Dto.GenreResponseDto;

import java.util.List;

public interface GenreService {
    List<GenreListResponseDto> getAllGenres();

    GenreResponseDto getGenre(Integer id) throws GenreNotFoundException;

    GenreResponseDto createGenre(GenreDto genreDto) throws GenreCreationException;

    void updateGenre(Integer id, GenreDto genreDto) throws GenreNotFoundException;

    void deleteGenre(Integer id);
}
