package com.springboot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer>{

}
