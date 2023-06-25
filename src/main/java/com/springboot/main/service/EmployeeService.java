package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.ResourceNotFoundException;
import com.springboot.main.model.Employee;
import com.springboot.main.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee insert(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	public Employee getById(int id) throws ResourceNotFoundException {
		Optional<Employee> optional = employeeRepository.findById(id);

		if (optional.isEmpty()) {
			throw new ResourceNotFoundException("Invalid ID given");
		}

		return optional.get();
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(id);
	}

}
