package com.springboot.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.model.Customer;
import com.springboot.main.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService; 
	
	@PostMapping("/add")
	public Customer postCustomer(@RequestBody Customer customer) {
		return customerService.insert(customer);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable("id") int id) {
		
		
		Customer oldCustomer  = customerService.getById(id);
		if(oldCustomer == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid ID given");
		}
		/* 2 techniques {old has id whereas new does not have id}
		 * 1. Transfer new values to old(that has id)
		 * 2. Transfer id from old to new.  
		 */
		customer.setId(oldCustomer.getId());
	    customer = customerService.insert(customer);
	    return ResponseEntity.status(HttpStatus.OK)
				.body(customer);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		
			Customer customer = customerService.getById(id);
			if(customer == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid Id");
		
		
		customerService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
