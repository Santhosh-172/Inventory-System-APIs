package com.springboot.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.main.model.Customer;
import com.springboot.main.model.User;
import com.springboot.main.repository.UserRepository;

@Service
public class MyUserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository; 
	
	public User insert(User user) {
		 
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		User user  = userRepository.getUserByUsername(username);
		
		return user;
	}

	public User getById(int id) {
		// TODO Auto-generated method stub
		
		Optional<User> optional= userRepository.findById(id);
		if(!optional.isPresent()) {
			return null; 
		}
		return optional.get();
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}
	}

	


