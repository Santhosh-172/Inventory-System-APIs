package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.model.Manager;
import com.springboot.main.repository.ManagerRepository;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository;
	
	public Manager insert(Manager manager) {
		return managerRepository.save(manager);
	}

	public Manager getById(int managerID) {
		Optional<Manager> optional = managerRepository.findById(managerID);
		
		if(!optional.isPresent())
			return null;
		
		return optional.get(); //returns manager
	}

	public List<Manager> getAll() {
		// TODO Auto-generated method stub
		return managerRepository.findAll();
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		managerRepository.deleteById(id);
	}

}
