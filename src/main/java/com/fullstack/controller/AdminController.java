package com.fullstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.model.Admin;
import com.fullstack.model.MovieList;
import com.fullstack.model.Users;
import com.fullstack.service.AdminService;
import com.fullstack.service.MovieListService;
import com.fullstack.service.UsersService;


@RestController
//@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	UsersService userService;
	
	@Autowired
	MovieListService movieService;
	
	@PostMapping("addAdmin")
	public Admin addAdmin(@RequestBody Admin admin) {
		return adminService.addNewAdmin(admin);
	}
	
	@GetMapping("/viewUsers")
	public List<Users> listOfUsers(){
		return userService.getUsersList();
	}
	
	@GetMapping("/viewById/{userid}")
	public Users getById(@PathVariable long userid) {
		return userService.viewById(userid);
	}
	
	@DeleteMapping("/deleteUsers/{userid}")
	public ResponseEntity<Users> userDelete(@PathVariable long userid) {
		return userService.deleteById(userid);
	}
	
	@PostMapping("/addMovie")
	public MovieList addOneMovie(@RequestBody MovieList movielist) {
		return movieService.addOneMovieData(movielist);
	}
	
	@PutMapping("/updateMovie/{movieid}")
	public MovieList updateusers(@PathVariable long movieid,@RequestBody MovieList movieList) {
		
		return movieService.updateExistingMovie(movieid,movieList);
		
	}
	
	@DeleteMapping("/deleteMovie/{movieid}")
	public ResponseEntity<MovieList> movieDelete(@PathVariable long movieid) {
		return movieService.deleteById(movieid);
	}
	
}
