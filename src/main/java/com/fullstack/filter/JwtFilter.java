package com.fullstack.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fullstack.service.DefaultUser;
import com.fullstack.util.JwtUtil;

@Service
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	DefaultUser defaultUser;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt=request.getHeader("Authentication...");
		
		if(jwt !=null) {
			String username=jwtUtil.extractUsername(jwt);
			if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetail =defaultUser.loadUserByUsername(username);
				if(userDetail != null) {
					UsernamePasswordAuthenticationToken upat= new UsernamePasswordAuthenticationToken(userDetail, null, new ArrayList<>());
					upat.setDetails(new WebAuthenticationDetails(request));
					SecurityContextHolder.getContext().setAuthentication(upat);
				}
			}
			
		}
		filterChain.doFilter(request, response);
	}
	
	
	
	
}
