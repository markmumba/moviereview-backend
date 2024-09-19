package com.markian.moviereview.Review;

import com.markian.moviereview.Exceptions.MovieNotFoundException;
import com.markian.moviereview.Exceptions.ReviewNotFoundException;
import com.markian.moviereview.Review.Dto.ReviewDto;
import com.markian.moviereview.Review.Dto.ReviewListResponseDto;
import com.markian.moviereview.Review.Dto.ReviewResponseDto;

import java.util.List;

public interface ReviewService {
    List<ReviewListResponseDto> getAllReviews(Integer movieId)  ;

    ReviewResponseDto createReview(Integer movieId, ReviewDto reviewDto) throws MovieNotFoundException;

    ReviewResponseDto getReview(Integer movieId, Integer reviewId) throws MovieNotFoundException, ReviewNotFoundException;

    void updateReview(Integer movieId, Integer reviewId, ReviewDto updatedReviewDto) throws MovieNotFoundException, ReviewNotFoundException;

    void deleteReview(Integer movieId, Integer reviewId) throws MovieNotFoundException, ReviewNotFoundException;


}
