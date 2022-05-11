package com.fullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstack.model.MovieList;

@Repository
public interface MovieListRepository extends JpaRepository<MovieList, Long>{

}
