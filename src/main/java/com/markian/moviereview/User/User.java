package com.markian.moviereview.User;

import com.markian.moviereview.Base.BaseEntity;
import com.markian.moviereview.Like.Like;
import com.markian.moviereview.Review.Review;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_table")
public class User extends BaseEntity {

    private String username;

    @Column(unique = true)
    private String email;

    private String passwordhash;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Like> likes;
}
