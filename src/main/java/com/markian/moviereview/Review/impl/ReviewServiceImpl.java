package com.markian.moviereview.Review.impl;

import com.markian.moviereview.Exceptions.MovieNotFoundException;
import com.markian.moviereview.Exceptions.ReviewNotFoundException;
import com.markian.moviereview.Movie.Movie;
import com.markian.moviereview.Movie.MovieService;
import com.markian.moviereview.Review.Dto.ReviewDto;
import com.markian.moviereview.Review.Dto.ReviewListResponseDto;
import com.markian.moviereview.Review.Dto.ReviewMapper;
import com.markian.moviereview.Review.Dto.ReviewResponseDto;
import com.markian.moviereview.Review.Review;
import com.markian.moviereview.Review.ReviewRepository;
import com.markian.moviereview.Review.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO add a check for rating to be between 1 to 5


@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieService movieService;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, MovieService movieService, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.movieService = movieService;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewListResponseDto> getAllReviews(Integer movieId) {
        List<Review> reviews = reviewRepository.findAllByMovieId(movieId);
        return reviews.stream()
                .map(reviewMapper::fromReviewListToDto)
                .toList();
    }

    @Override
    public ReviewResponseDto createReview(Integer movieId, ReviewDto reviewDto) {
        Movie movie = movieService.getMovieEntity(movieId);
        Review review = reviewMapper.fromDtoToReview(reviewDto);
        review.setMovie(movie);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.fromReviewToDto(savedReview);
    }

    @Override
    public ReviewResponseDto getReview(Integer movieId, Integer reviewId) throws MovieNotFoundException, ReviewNotFoundException {
        return reviewRepository.findByIdAndMovieId(reviewId, movieId)
                .map(reviewMapper::fromReviewToDto)
                .orElseThrow(() -> new ReviewNotFoundException("Review with Id " + reviewId + "Not found"));
    }

    @Override
    @Transactional
    public void updateReview(Integer movieId, Integer reviewId, ReviewDto updatedReviewDto) throws MovieNotFoundException, ReviewNotFoundException {
        Movie movie = movieService.getMovieEntity(movieId);
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new ReviewNotFoundException("Review with ID " + reviewId + " not found."));
        existingReview.setRating(updatedReviewDto.rating());
        existingReview.setReviewtext(updatedReviewDto.reviewtext());
        existingReview.setMovie(movie);

        reviewRepository.save(existingReview);
    }

    @Override
    @Transactional
    public void deleteReview(Integer movieId, Integer reviewId) {
        Movie movie = movieService.getMovieEntity(movieId);
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new ReviewNotFoundException("Review with ID " + reviewId + " not found."));

        if (!review.getMovie().getId().equals(movieId)) {
            throw new ReviewNotFoundException("Review with ID " + reviewId + " does not belong to movie with ID " + movieId);
        }
        reviewRepository.delete(review);
    }
}
