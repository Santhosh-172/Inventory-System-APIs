package com.springboot.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.exception.ResourceNotFoundException;
import com.springboot.main.model.Employee;
import com.springboot.main.model.Manager;
import com.springboot.main.model.User;
import com.springboot.main.service.EmployeeService;
import com.springboot.main.service.ManagerService;
import com.springboot.main.service.MyUserService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private MyUserService userService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ManagerService managerService;

	@PostMapping("/add/{managerId}")
	public ResponseEntity<?> addEmployee(@PathVariable("managerId") int managerId, @RequestBody Employee employee) {
		Manager manager = managerService.getById(managerId);
		if (manager == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Manager ID invalid");

		employee.setManager(manager);

		User user = employee.getUser();

		user.setPassword(encoder.encode(user.getPassword()));

		user.setRole("EMPLOYEE");

		user = userService.insert(user);

		employee.setUser(user);
		employee = employeeService.insert(employee);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	@GetMapping("/all")
	public List<Employee> getAll() {
		return employeeService.getAll();
	}

	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOne(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.getById(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/update/{id}/{managerId}")
	public ResponseEntity<?> update(@PathVariable int id, @PathVariable("managerId") int managerId,
			@RequestBody Employee employee) throws ResourceNotFoundException {
		try {
			employeeService.getById(id);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

		employee.setId(id);
		Manager manager;
		manager = managerService.getById(managerId);
		employee.setManager(manager);

		User user = employee.getUser();
		user.setRole("EMPLOYEE");
		user.setPassword(encoder.encode(user.getPassword()));
		user = userService.insert(user);
		employee.setUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.insert(employee));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		try {
			employeeService.getById(id);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

		employeeService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}