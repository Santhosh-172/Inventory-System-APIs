package com.springboot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
//all the methods of JpaRepository are now available in ProductRepository
	
	/* Notable Methods: 
	 * save(T):T : saves the object in DB 
	 * getAll(): List<T> : returns all the objects present in the DB
	 * findById(id): Optional<T> : Returns the Object on the basis of ID. 
	 * delete(id) : void : deletes the object based on id. 
	 */
	
	
}
