package com.markian.moviereview.Comment;

import com.markian.moviereview.Comment.Dto.CommentDto;
import com.markian.moviereview.Comment.Dto.CommentListResponseDto;
import com.markian.moviereview.Comment.Dto.CommentResponseDto;
import com.markian.moviereview.Exceptions.CommentCreationException;
import com.markian.moviereview.Exceptions.CommentNotFoundException;

import java.util.List;

public interface CommentService {
    List<CommentListResponseDto> getAllComment(Integer reviewId);

    CommentResponseDto getComment(Integer reviewId, Integer commentId) throws CommentCreationException;

    CommentResponseDto createComment(Integer reviewId, CommentDto commentDto) throws CommentCreationException;

    void updateComment(Integer reviewId, Integer commentId, CommentDto commentDto) throws CommentNotFoundException;

    void deleteComment(Integer reviewId, Integer commentId);


}
