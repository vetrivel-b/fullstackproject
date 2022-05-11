package com.fullstack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fullstack.exception.ResourceNotFoundException;
import com.fullstack.model.MovieList;
import com.fullstack.model.Users;
import com.fullstack.repository.UserRepository;

@Service
public class UsersService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Users addNewUser(Users users) {
		return userRepo.save(users);
	}

	public Users updateExistingUser(long userid, Users users) {
		Users existingProfile =userRepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("user not found with this Id"+userid));
		existingProfile.setUsername(users.getUsername());
		existingProfile.setContactno(users.getContactno());
		String pwdd=users.getPassword();
		String encryptPwd=encoder.encode(pwdd);
		existingProfile.setPassword(encryptPwd);
		existingProfile.setEmail(users.getEmail());
		return userRepo.save(existingProfile);
	}

	public List<Users> getUsersList() {
		List<Users> users=userRepo.findAll();
		System.out.println("getting data from db: "+ users);
		return userRepo.findAll();
	}
	
	public Users viewById(long userid) {
		//Users selectUser=userRepo.getById(userid);
		return userRepo.findById(userid).orElse(null);
		
	}
	
	public ResponseEntity<Users> deleteById(long userid) {
		Users selectUser=userRepo.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with this Id"+userid));
		userRepo.delete(selectUser);
		
		return ResponseEntity.ok().build();
//		userRepo.deleteById(userid);
//		return "deleted";
	}

	

	

	

}
