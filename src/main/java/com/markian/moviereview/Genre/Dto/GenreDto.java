package com.markian.moviereview.Genre.Dto;

import jakarta.validation.constraints.NotNull;

public record GenreDto(
        @NotNull(message = "Genre must have a name")
        String name
) {
}
