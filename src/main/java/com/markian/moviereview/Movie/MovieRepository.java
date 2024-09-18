package com.markian.moviereview.Movie;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    boolean existsByTitle(String title);
}
