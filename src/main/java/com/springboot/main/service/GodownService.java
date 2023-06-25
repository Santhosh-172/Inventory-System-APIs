package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.model.Godown;
import com.springboot.main.repository.GodownRepository;

@Service
public class GodownService {

	@Autowired
	private GodownRepository goDownRepository; 
	
	public Godown insert(Godown godown) {
		 
		return goDownRepository.save(godown);
	}

	public Godown getById(int godownId) {
		Optional<Godown> optional= goDownRepository.findById(godownId);
		if(!optional.isPresent()) {
			return null; 
		}
		return optional.get();
	}

	public List<Godown> getAll() {
		// TODO Auto-generated method stub
		return goDownRepository.findAll();
	}

	public Godown getproduct(int id) {
		// TODO Auto-generated method stub
		Optional<Godown> optional =  goDownRepository.findById(id);
		if(optional == null) {
			return null;
		}
		return optional.get();
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		goDownRepository.deleteById(id);
	}

}
