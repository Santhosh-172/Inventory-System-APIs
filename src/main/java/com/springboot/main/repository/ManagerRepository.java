package com.springboot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer>{

}
