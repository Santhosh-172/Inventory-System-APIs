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

import com.springboot.main.model.Customer;
import com.springboot.main.model.Product;
import com.springboot.main.model.Supplier;
import com.springboot.main.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@PostMapping("/add")
	public Supplier postSupplier(@RequestBody Supplier supplier) {
		return supplierService.insert(supplier);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<?> updateCustomer(@RequestBody Supplier supplier, @PathVariable("id") int id) {
		
		
		Supplier oldSupplier  = supplierService.getById(id);
		if(oldSupplier == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid ID given");
		}
		/* 2 techniques {old has id whereas new does not have id}
		 * 1. Transfer new values to old(that has id)
		 * 2. Transfer id from old to new.  
		 */
		supplier.setId(oldSupplier.getId());
	    supplier = supplierService.insert(supplier);
	    return ResponseEntity.status(HttpStatus.OK)
				.body(supplier);
	}
	
	@GetMapping("/all")
	public List<Supplier> getAll() {
		return supplierService.getAll();
	}
	
	@GetMapping("/one/{id}") //this id is called as path variable
	public ResponseEntity<?> getProduct(@PathVariable("id") int id) {
		Supplier supplier  = supplierService.getById(id);
		if(supplier == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid ID given");
		}
		return ResponseEntity.status(HttpStatus.OK).body(supplier); 
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		
			Supplier supplier = supplierService.getById(id);
			if(supplier == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid Id");
		
		
			supplierService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
