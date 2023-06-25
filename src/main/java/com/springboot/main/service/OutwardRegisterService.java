package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.model.InwardRegister;
import com.springboot.main.model.Manager;
import com.springboot.main.model.OutwardRegister;
import com.springboot.main.repository.OutwardRegisterRepository;

@Service
public class OutwardRegisterService {

	@Autowired
	private OutwardRegisterRepository outwardRegisterRepository;

	public OutwardRegister insert(OutwardRegister outwardRegister) {

		return outwardRegisterRepository.save(outwardRegister);
	}

//	public boolean checkQuantity(int productId, int quantityPuchased) {
//		InwardRegister inwardRegister = outwardRegisterRepository
//				.checkQuantity(productId,quantityPuchased);
//		if(inwardRegister == null)
//			return false;
//		return true;
//	}

	public List<OutwardRegister> getAll() {

		return outwardRegisterRepository.findAll();
	}

	public OutwardRegister getById(int id) {
		Optional<OutwardRegister> optional = outwardRegisterRepository.findById(id);

		if (!optional.isPresent())
			return null;

		return optional.get(); // returns manager
	}
}
