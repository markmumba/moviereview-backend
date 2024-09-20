package com.markian.moviereview.Comment.Impl;

import com.markian.moviereview.Comment.Comment;
import com.markian.moviereview.Comment.CommentRepository;
import com.markian.moviereview.Comment.CommentService;
import com.markian.moviereview.Comment.Dto.CommentDto;
import com.markian.moviereview.Comment.Dto.CommentListResponseDto;
import com.markian.moviereview.Comment.Dto.CommentMapper;
import com.markian.moviereview.Comment.Dto.CommentResponseDto;
import com.markian.moviereview.Exceptions.CommentCreationException;
import com.markian.moviereview.Exceptions.CommentNotFoundException;
import com.markian.moviereview.Review.Review;
import com.markian.moviereview.Review.ReviewRepository;
import com.markian.moviereview.Review.ReviewService;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, ReviewService reviewService, ReviewRepository reviewRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.reviewRepository = reviewRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentListResponseDto> getAllComment(Integer reviewId) {
        List<Comment> comments = commentRepository.findAllByReviewId(reviewId);
        return comments.stream()
                .map(commentMapper::fromCommentListToDto)
                .toList();
    }

    @Override
    public CommentResponseDto getComment(Integer reviewId, Integer commentId) throws CommentCreationException {
        Optional<Comment> comment = commentRepository.findByIdAndReviewId(commentId, reviewId);
        return comment
                .map(commentMapper::fromCommentToDto)
                .orElseThrow(()
                        -> new CommentNotFoundException("This comment by id" + commentId + "not found"));
    }

    @Override
    public CommentResponseDto createComment(Integer reviewId, CommentDto commentDto) throws CommentCreationException {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new CommentCreationException("Review not found "));

        Comment comment = commentMapper.fromDtoToComment(commentDto);
        comment.setReview(review);

        return commentMapper.fromCommentToDto(commentRepository.save(comment));
    }


    @Override
    @Transactional
    public void updateComment(Integer reviewId, Integer commentId, CommentDto commentDto) throws CommentNotFoundException {
        Comment existingComment = commentRepository.findByIdAndReviewId(commentId, reviewId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        if (commentDto.commenttext() != null) {
            existingComment.setCommenttext(commentDto.commenttext());
        }

        commentRepository.save(existingComment);
    }


    @Override
    public void deleteComment(Integer reviewId, Integer commentId) {
        Comment existingComment = commentRepository.findByIdAndReviewId(commentId, reviewId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        commentRepository.delete(existingComment);

    }
}
