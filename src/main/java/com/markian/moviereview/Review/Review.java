package com.markian.moviereview.Review;

import com.markian.moviereview.Base.BaseEntity;
import com.markian.moviereview.Movie.Movie;
import com.markian.moviereview.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Review extends BaseEntity {

    private Integer rating;

    private String reviewtext;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
