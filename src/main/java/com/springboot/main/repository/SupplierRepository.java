package com.springboot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer>{

}
