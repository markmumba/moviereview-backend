package com.markian.moviereview.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByReviewId (Integer id);
    Optional<Comment> findByIdAndReviewId (Integer commentId,Integer reviewId );
}
