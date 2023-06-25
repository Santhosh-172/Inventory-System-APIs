package com.springboot.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.exception.ResourceNotFoundException;
import com.springboot.main.model.Customer;
import com.springboot.main.model.Godown;
import com.springboot.main.model.Manager;
import com.springboot.main.model.Product;
import com.springboot.main.model.ReturnRegister;
import com.springboot.main.service.CustomerService;
import com.springboot.main.service.GodownService;
import com.springboot.main.service.ManagerService;
import com.springboot.main.service.ProductService;
import com.springboot.main.service.ReturnRegisterService;

@RestController
@RequestMapping("/returnregister")
public class ReturnRegisterController {

	@Autowired
	private ProductService productService;

	@Autowired
	private GodownService godownService;
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ReturnRegisterService returnRegisterService;

	@PostMapping("/add/{productId}/{godownId}/{customerId}/{managerId}")
	public ResponseEntity<?> postInwardRegister(@RequestBody ReturnRegister returnRegister,
			@PathVariable("productId") int productId, @PathVariable("godownId") int godownId,
			@PathVariable("customerId") int customerId, @PathVariable("managerId") int managerId) throws ResourceNotFoundException {
		Product product;
		product = productService.getById(productId);
		
		Godown godown;
		godown = godownService.getById(godownId);
		
		Customer customer;
		customer = customerService.getById(customerId);
		
		Manager manager;
		manager = managerService.getById(managerId);
		
		returnRegister.setProduct(product);
		returnRegister.setGodown(godown);
		returnRegister.setReturnedBy(customer);
		returnRegister.setCheckedBy(manager);

		returnRegister = returnRegisterService.insert(returnRegister);
		return ResponseEntity.status(HttpStatus.OK).body(returnRegister);
	}
	
	@GetMapping("/all")
	public List<ReturnRegister> getAll() {
		return returnRegisterService.getAll();
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOne(@PathVariable int id) {
		ReturnRegister returnRegister = returnRegisterService.getById(id);
		if(returnRegister == null) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid Id");
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(returnRegister);
		
	}
	
	@PutMapping("/update/{id}/{productId}/{godownId}/{customerId}/{managerId}")
	public ResponseEntity<?> update(@PathVariable int id, @PathVariable("productId") int productId,
			@PathVariable("godownId") int godownId, @PathVariable("customerId") int customerId,
			@PathVariable("managerId") int managerId, @RequestBody ReturnRegister returnRegister) throws ResourceNotFoundException {
		
			 returnRegister = returnRegisterService.getById(id);
			if(returnRegister == null) {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Id");
			}
		
		
		returnRegister.setId(id);
		
		Product product;
		product = productService.getById(productId);
		
		Godown godown;
		godown = godownService.getById(godownId);
		
		Customer customer;
		customer = customerService.getById(customerId);
		
		Manager manager;
		manager = managerService.getById(managerId);
		
		returnRegister.setProduct(product);
		returnRegister.setGodown(godown);
		returnRegister.setReturnedBy(customer);
		returnRegister.setCheckedBy(manager);

		returnRegister = returnRegisterService.insert(returnRegister);
		return ResponseEntity.status(HttpStatus.OK).body(returnRegister);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		
			ReturnRegister returnRegister = returnRegisterService.getById(id);
			if(returnRegister == null) {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Invalid Id");
			}
		
		
		returnRegisterService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}