package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.ResourceNotFoundException;
import com.springboot.main.model.Product;
import com.springboot.main.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product insert(Product product) {
		return productRepository.save(product);	 
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getproduct(int id) {
		//Scene 1: id is valid we get the product 
		//scene 2: id is not valid: we get null 
		Optional<Product> optional= 
				productRepository.
				findById(id);
		if(!optional.isPresent()) {
			return null; 
		}
		return optional.get();
	}

	public Product getproductAlternate(int id) throws ResourceNotFoundException{
		Optional<Product> optional= productRepository.findById(id);
		if(!optional.isPresent()) {
			throw new ResourceNotFoundException("Invalid ID Given.."); 
		}
		return optional.get();
	}

	public void deleteProduct(Product product) {
		productRepository.delete(product);
		
	}

	public Product getById(int productId) {
		 
		Optional<Product> optional= productRepository.findById(productId);
		if(!optional.isPresent()) {
			return null; 
		}
		return optional.get();
	}

}
