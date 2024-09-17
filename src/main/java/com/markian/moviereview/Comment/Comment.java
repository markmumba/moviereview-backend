package com.markian.moviereview.Comment;

import com.markian.moviereview.Base.BaseEntity;
import com.markian.moviereview.Review.Review;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Comment extends BaseEntity {

    private String commenttext;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;
}
