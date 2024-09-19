package com.markian.moviereview.Review.Dto;

import com.markian.moviereview.Comment.Comment;
import com.markian.moviereview.Like.Like;

import java.util.List;

public record ReviewResponseDto(
        Integer id,
        Integer rating,
        String reviewtext,
        List<Comment> comments,
        List<Like> likes
) {
}
