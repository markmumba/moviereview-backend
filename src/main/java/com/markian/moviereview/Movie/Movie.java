package com.markian.moviereview.Movie;

import com.markian.moviereview.Base.BaseEntity;
import com.markian.moviereview.Genre.Genre;
import com.markian.moviereview.Review.Review;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Movie extends BaseEntity {

    private String title;

    private LocalDateTime releasedate;

    private String director;

    private String synopsis;

    private String posterurl;

    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = {
                    @JoinColumn(name = "movie_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "genre_id")
            }
    )
    private List<Genre> genres;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Review> reviews;
}
