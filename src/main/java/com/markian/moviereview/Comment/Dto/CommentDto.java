package com.markian.moviereview.Comment.Dto;

import jakarta.validation.constraints.NotNull;

public record CommentDto (
        @NotNull(message = "Enter comment text")
        String commenttext
){
}
