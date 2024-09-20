package com.markian.moviereview.Genre.Impl;

import com.markian.moviereview.Exceptions.GenreCreationException;
import com.markian.moviereview.Exceptions.GenreNotFoundException;
import com.markian.moviereview.Exceptions.MovieNotFoundException;
import com.markian.moviereview.Genre.Dto.GenreDto;
import com.markian.moviereview.Genre.Dto.GenreListResponseDto;
import com.markian.moviereview.Genre.Dto.GenreMapper;
import com.markian.moviereview.Genre.Dto.GenreResponseDto;
import com.markian.moviereview.Genre.Genre;
import com.markian.moviereview.Genre.GenreRepository;
import com.markian.moviereview.Genre.GenreService;
import com.markian.moviereview.Movie.Movie;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public List<GenreListResponseDto> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(genreMapper::fromGenreToDtoList)
                .toList();
    }

    @Override
    public GenreResponseDto getGenre(Integer id) throws GenreNotFoundException {
        return genreRepository.findById(id)
                .map(genreMapper::fromGenreToDto)
                .orElseThrow(() ->
                        new GenreNotFoundException("Genre with id " + id + " not found"));
    }

    @Transactional
    @Override
    public GenreResponseDto createGenre(GenreDto genreDto) throws GenreCreationException {
        if (genreRepository.existsByName(genreDto.name())) {
            throw new GenreCreationException("Genre already exist");
        }
        Genre genre = genreMapper.fromDtoToGenre(genreDto);
        return genreMapper.fromGenreToDto(genreRepository.save(genre));
    }

    @Transactional
    @Override
    public void updateGenre(Integer id, GenreDto updatedGenreDto) throws GenreNotFoundException {
        Genre existingGenre= genreRepository.findById(id)
                .orElseThrow(() ->
                        new GenreNotFoundException("with id" + id + "not found "));
        if (updatedGenreDto.name() != null) {
            existingGenre.setName(updatedGenreDto.name());
        }
        genreRepository.save(existingGenre);
    }

    @Override
    public void deleteGenre(Integer id) {
        genreRepository.deleteById(id);
    }
}
