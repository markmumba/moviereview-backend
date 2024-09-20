package com.markian.moviereview.Genre;


import com.markian.moviereview.Genre.Dto.GenreDto;
import com.markian.moviereview.Genre.Dto.GenreListResponseDto;
import com.markian.moviereview.Genre.Dto.GenreResponseDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<GenreListResponseDto>> getAllGenres() {
        List<GenreListResponseDto> genreListResponseDtos = genreService.getAllGenres();
        return new ResponseEntity<>(genreListResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDto> getGenre(@PathVariable("id") Integer id) {
        GenreResponseDto genreResponseDto = genreService.getGenre(id);
        return new ResponseEntity<>(genreResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenreResponseDto> createGenre(@RequestBody GenreDto genreDto) {
        GenreResponseDto genreResponseDto = genreService.createGenre(genreDto);
        return new ResponseEntity<>(genreResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGenre(
            @PathVariable("id") Integer id,
            @RequestBody GenreDto genreDto) {
        genreService.updateGenre(id, genreDto);
        return new ResponseEntity<>("Genre has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable("id") Integer id) {
        genreService.deleteGenre(id);
        return new ResponseEntity<>("Genre Deleted successfully", HttpStatus.OK);
    }

}
