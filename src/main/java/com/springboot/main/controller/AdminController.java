package com.springboot.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.exception.ResourceNotFoundException;
import com.springboot.main.model.User;
import com.springboot.main.service.MyUserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private PasswordEncoder encoder; 
	
	@Autowired
	private MyUserService userService;
	
	@PostMapping("/add")
	public User add(@RequestBody User user) {
		/*encode the password, set the role, save it in DB */
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole("ADMIN");
		return userService.insert(user);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody User newUser) {
		
			User user = userService.getById(id);
			if(user == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid Id");
		
		
		newUser.setId(id);
		newUser.setRole("ADMIN");
		newUser.setPassword(encoder.encode(newUser.getPassword()));
		return ResponseEntity.status(HttpStatus.OK).body(userService.insert(newUser));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		
			User user = userService.getById(id);
			if(user == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid Id");
		
		
		userService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
