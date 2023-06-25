package com.springboot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
