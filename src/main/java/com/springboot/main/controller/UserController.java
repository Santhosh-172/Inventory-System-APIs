package com.springboot.main.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.service.MyUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private MyUserService userService; 
	
	@GetMapping("/login") //by the time we reach here, spring has already verified username/password
	public UserDetails login(Principal principal) { //injecting principal in method
		 //I need username from spring, so that i can go to db and fetch role and give to UI dev. 
	
		/* An Interface called as 'Principal' in Spring security can help us read username */
		String username = principal.getName();
		UserDetails user = userService.loadUserByUsername(username);
		return user; 
	}
}
