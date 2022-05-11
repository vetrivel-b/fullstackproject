package com.fullstack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.model.Admin;
import com.fullstack.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;
	
	public Admin addNewAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
}
