package com.fullstack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import com.fullstack.model.Users;
import com.fullstack.repository.UserRepository;

@Service
public class DefaultUser implements UserDetailsService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Users> userOpt=userRepo.findByUsername(username);
		
		if(userOpt.isPresent()) {
			Users userObj=(Users) userOpt.get();
			return new User(userObj.getUsername(),userObj.getPassword(), new ArrayList<>());
		}
		else {
			throw new IllegalStateException("username does not found...");
		}
	}

	
}
