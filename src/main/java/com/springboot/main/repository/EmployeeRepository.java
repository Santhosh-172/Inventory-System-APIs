package com.springboot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
