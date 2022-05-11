package com.fullstack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.model.AuthenticationRequest;
import com.fullstack.model.AuthenticationResponse;
import com.fullstack.model.MovieList;
import com.fullstack.model.Users;
import com.fullstack.service.DefaultUser;
import com.fullstack.service.MovieListService;
import com.fullstack.service.UsersService;
import com.fullstack.util.JwtUtil;




@RestController
//@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5500")
public class UsersController {
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private MovieListService movieService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private DefaultUser defaultUser;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@PostMapping("/addUser")
	public Users addNewSingleUser(@RequestBody Users users) {
		String pwd=users.getPassword();
		String encryptPwd=encoder.encode(pwd);
		users.setPassword(encryptPwd);
		return userService.addNewUser(users);
	}
	
	@PutMapping("/updateUser/{userid}")
	public Users updateusers(@RequestHeader("Authentication...") String token ,@PathVariable long userid,@RequestBody Users users) {
		if(jwtUtil.validateToken(token)) {
		return userService.updateExistingUser(userid,users);
		}
		else {
			return null;
		}
	}
	
	//@RequestHeader("Authentication...") String token
	@GetMapping("/availablemovieList")
	public List<MovieList> listOfMovies(){
//		if(jwtUtil.validateToken(token)) {
//			
//		}
//		else {
//			return null;
//		}
		return movieService.getMovieList();
	}
	
	@GetMapping("/viewSingleMovie/{movieid}")
	public MovieList viewSingleMovie(@PathVariable long movieid) {
		return movieService.viewMovieById(movieid);
	}
	
	@PostMapping("/authenticate")
	private ResponseEntity<?> autenticate(@RequestBody AuthenticationRequest authenticationRequest){
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword(), new ArrayList<>()));
		} catch (Exception e) {
			throw new IllegalStateException("invalid username or password");
		}
		final UserDetails userDetails=defaultUser.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt=jwtUtil.generateToken(userDetails);
		System.out.println(jwt);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	
}
