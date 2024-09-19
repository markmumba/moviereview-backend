package com.markian.moviereview.Review.Dto;

public record ReviewListResponseDto (
        Integer id,
        Integer rating,
        String reviewtext
) {
}
