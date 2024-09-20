package com.markian.moviereview.Movie;


import com.markian.moviereview.Movie.Dto.MovieDto;
import com.markian.moviereview.Movie.Dto.MovieListResponseDto;
import com.markian.moviereview.Movie.Dto.MovieResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieListResponseDto>> getAllMovies() {
        List<MovieListResponseDto> movieListResponseDtos = movieService.getAllMovies();
        return new ResponseEntity<>(movieListResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovie(@PathVariable("id") Integer id) {
        MovieResponseDto movieResponseDto = movieService.getMovie(id);
        return new ResponseEntity<>(movieResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovieResponseDto> createMovie(@Valid @RequestBody MovieDto movieDto) {
        MovieResponseDto movieResponseDto = movieService.createMovie(movieDto);
        return new ResponseEntity<>(movieResponseDto, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable("id") Integer id,
                                              @Valid @RequestBody MovieDto movieDto) {
        movieService.updateMovie(id, movieDto);
        var message = "Movie Updated successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{movieId}/genres")
    public ResponseEntity<Void> updateMovieGenres(
            @PathVariable Integer movieId,
            @RequestBody List<Integer> genreIds) {
        movieService.updateMovieGenres(movieId, genreIds);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") Integer id) {
        movieService.deleteMovie(id);
        var message = "Movie deleted successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
