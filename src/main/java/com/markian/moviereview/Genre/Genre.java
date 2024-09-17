package com.markian.moviereview.Genre;


import com.markian.moviereview.Base.BaseEntity;
import com.markian.moviereview.Movie.Movie;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Genre extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Movie> movies;

}
