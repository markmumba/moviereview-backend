package com.markian.moviereview.Comment;

import com.markian.moviereview.Base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Comment extends BaseEntity {
    private String commenttext;
}
