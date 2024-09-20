package com.markian.moviereview.Movie.Dto;

import com.markian.moviereview.Genre.Dto.GenreListResponseDto;
import com.markian.moviereview.Genre.Dto.GenreResponseDto;
import com.markian.moviereview.Genre.Genre;
import com.markian.moviereview.Review.Dto.ReviewListResponseDto;
import com.markian.moviereview.Review.Dto.ReviewResponseDto;
import com.markian.moviereview.Review.Review;

import java.time.LocalDateTime;
import java.util.List;

public record MovieResponseDto(
        Integer id,
        String title,
        LocalDateTime releasedate,
        String director,
        String synopsis,
        String posterurl,
        List<GenreListResponseDto> genres,
        List<ReviewListResponseDto> reviews
) {

}
