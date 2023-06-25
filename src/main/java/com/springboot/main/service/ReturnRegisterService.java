package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.model.ReturnRegister;
import com.springboot.main.repository.ReturnRegisterRepository;

@Service
public class ReturnRegisterService {

	@Autowired
	private ReturnRegisterRepository returnsRepository;
	
	public ReturnRegister insert(ReturnRegister returns) {
		return returnsRepository.save(returns);
	}
	
	public List<ReturnRegister> getAll() {
		return returnsRepository.findAll();
	}

	public ReturnRegister getById(int id) {
	    Optional<ReturnRegister> optional =  returnsRepository.findById(id);
	           if(!optional.isPresent()) {
	        	   return null;
	           }
	    return optional.get();
	}


	public void delete(int id) {
		returnsRepository.deleteById(id);
	}

}
