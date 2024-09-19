package com.markian.moviereview.Review.Dto;

import com.markian.moviereview.Review.Review;
import org.springframework.stereotype.Service;

@Service
public class ReviewMapper {


    public Review fromDtoToReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setRating(reviewDto.rating());
        review.setReviewtext(reviewDto.reviewtext());
        return review;
    }

    public ReviewResponseDto fromReviewToDto(Review review) {
        return new ReviewResponseDto(
                review.getId(),
                review.getRating(),
                review.getReviewtext(),
                review.getComments(),
                review.getLikes()
        );
    }

    public ReviewListResponseDto fromReviewListToDto(Review review) {
        return new ReviewListResponseDto(
                review.getId(),
                review.getRating(),
                review.getReviewtext()
        );
    }
}
