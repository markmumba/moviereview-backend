package com.markian.moviereview.User;

import com.markian.moviereview.Base.BaseEntity;
import com.markian.moviereview.Review.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class User extends BaseEntity {

    private String username;

    @Column(unique = true)
    private String email;

    private String passwordhash;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;
}
