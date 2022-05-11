package com.fullstack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fullstack.exception.ResourceNotFoundException;
import com.fullstack.model.MovieList;
import com.fullstack.model.Users;
import com.fullstack.repository.MovieListRepository;

@Service
public class MovieListService {

	@Autowired
	MovieListRepository movierepo;
	
	
	public MovieList addOneMovieData(MovieList movielist) {
		return movierepo.save(movielist);
	}

	public List<MovieList> getMovieList() {
		List<MovieList> movielist=movierepo.findAll();
		System.out.println("getting data from db: "+ movielist);
		return movierepo.findAll();
	}

	public ResponseEntity<MovieList> deleteById(long movieid) {
		MovieList selectMovie=movierepo.findById(movieid)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with this Id"+movieid));
		movierepo.delete(selectMovie);
		
		return ResponseEntity.ok().build();
	}

	public MovieList updateExistingMovie(long movieid, MovieList movieList) {
		MovieList existingMovie=movierepo.findById(movieid).orElseThrow(() -> new ResourceNotFoundException("there is no movie in this movie id"+movieid));
		existingMovie.setMoviename(movieList.getMoviename());
		existingMovie.setPlace(movieList.getPlace());
		existingMovie.setBookingdate(movieList.getBookingdate());
		existingMovie.setAvailableseats(movieList.getAvailableseats());
		
		return movierepo.save(existingMovie);
	}

	public MovieList viewMovieById(long movieid) {
		return movierepo.findById(movieid).orElseThrow(()-> new ResourceNotFoundException("movie not found in this"+movieid));
	}

}
