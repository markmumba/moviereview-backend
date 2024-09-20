package com.markian.moviereview.Review;

import com.markian.moviereview.Review.Dto.ReviewDto;
import com.markian.moviereview.Review.Dto.ReviewListResponseDto;
import com.markian.moviereview.Review.Dto.ReviewResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies/{movieId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewListResponseDto>> getAllReviews(
            @PathVariable("movieId") Integer movieId
    ) {
        List<ReviewListResponseDto> reviewListResponseDtos = reviewService.getAllReviews(movieId);
        return new ResponseEntity<>(reviewListResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewResponseDto> getReview(
            @PathVariable("movieId") Integer movieId,
            @PathVariable("reviewId") Integer reviewId
    ) {
        ReviewResponseDto reviewResponseDto = reviewService.getReview(movieId, reviewId);
        return new ResponseEntity<>(reviewResponseDto, HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<ReviewResponseDto> createReview(
            @PathVariable("movieId") Integer movieId,
            @RequestBody ReviewDto reviewDto
    ) {

        ReviewResponseDto reviewResponseDto = reviewService.createReview(movieId, reviewDto);
        return new ResponseEntity<>(reviewResponseDto, HttpStatus.OK);
    }

    public ResponseEntity<String> updateReview(
            @PathVariable("movieId") Integer movieId,
            @PathVariable("reviewId") Integer reviewId,
            @RequestBody ReviewDto updatedReviewDto
    ) {
        reviewService.updateReview(movieId, reviewId, updatedReviewDto);
        return new ResponseEntity<>("Review has been updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(

            @PathVariable("movieId") Integer movieId,
            @PathVariable("reviewId") Integer reviewId
    ) {
        reviewService.deleteReview(movieId, reviewId);
        return new ResponseEntity<>("Deleted Review Succesfully", HttpStatus.OK);
    }
}
