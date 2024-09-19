package com.markian.moviereview.Review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByMovieId(Integer movieId);
    Optional<Review> findByIdAndMovieId(Integer reviewId, Integer movieId);

}
