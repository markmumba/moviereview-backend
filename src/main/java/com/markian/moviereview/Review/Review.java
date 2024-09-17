package com.markian.moviereview.Review;

import com.markian.moviereview.Base.BaseEntity;
import com.markian.moviereview.Comment.Comment;
import com.markian.moviereview.Like.Like;
import com.markian.moviereview.Movie.Movie;
import com.markian.moviereview.User.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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

    @OneToMany(mappedBy = "review",cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Like> likes;
}
