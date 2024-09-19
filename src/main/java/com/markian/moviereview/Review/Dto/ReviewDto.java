package com.markian.moviereview.Review.Dto;


import jakarta.validation.constraints.NotNull;

public record ReviewDto(
        @NotNull(message = "should have a rating between 1-5")
        Integer rating,
        @NotNull(message = "Must have review text ")
        String reviewtext

) {
}
