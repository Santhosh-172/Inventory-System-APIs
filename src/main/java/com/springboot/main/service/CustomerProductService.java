package com.springboot.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.model.Customer;
import com.springboot.main.model.CustomerProduct;
import com.springboot.main.repository.CustomerProductRepository;

@Service
public class CustomerProductService {

	@Autowired
	private CustomerProductRepository customerProductRepository;
	
	public CustomerProduct insert(CustomerProduct customerProduct) {
		 
		return customerProductRepository.save(customerProduct);
	}

	public List<CustomerProduct> getAll() {
		// TODO Auto-generated method stub
		return customerProductRepository.findAll();
	}

}
