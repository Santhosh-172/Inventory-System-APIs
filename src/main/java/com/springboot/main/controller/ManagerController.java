package com.springboot.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.model.Manager;
import com.springboot.main.model.Product;
import com.springboot.main.model.User;
import com.springboot.main.service.ManagerService;
import com.springboot.main.service.MyUserService;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService; 
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MyUserService userService; 
	@PostMapping("/add")
	public Manager postManager(@RequestBody Manager manager) {
		/*Read user info given as input and attach it to user object.  */
		User user = manager.getUser();
		user.setRole("MANAGER");
		
		/* Encode the password before saving in DB */
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		/* Save user in DB and fetch saved object */
		user = userService.insert(user);
		
		/* attach user to manager */
		manager.setUser(user);
		
		/* Save manager in DB */
		return managerService.insert(manager);
	}
	
	@GetMapping("/all")
	public List<Manager> getAll() {
		return managerService.getAll();
	}
	
	@GetMapping("/one/{id}") //this id is called as path variable
	public ResponseEntity<?> getGodown(@PathVariable("id") int id) {
		Manager manager  = managerService.getById(id);
		if(manager == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid ID given");
		}
		return ResponseEntity.status(HttpStatus.OK).body(manager); 
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		
			Manager manager = managerService.getById(id);
			if(manager == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid Id");
		
		
		managerService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable("id") int id, @RequestBody Manager newManager){
		Manager oldManager  = managerService.getById(id);
		if(oldManager == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid ID given");
		}
		/* 2 techniques {old has id whereas new does not have id}
		 * 1. Transfer new values to old(that has id)
		 * 2. Transfer id from old to new.  
		 */
		newManager.setId(oldManager.getId());
		newManager = managerService.insert(newManager);
	    return ResponseEntity.status(HttpStatus.OK)
				.body(newManager);
	}
}
