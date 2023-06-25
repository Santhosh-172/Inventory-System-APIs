package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.model.Product;
import com.springboot.main.model.Supplier;
import com.springboot.main.repository.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository; 
	
	public Supplier insert(Supplier supplier) {
		return supplierRepository.save(supplier);
	}

	public Supplier getById(int supplierId) {
		Optional<Supplier> optional= supplierRepository.findById(supplierId);
		if(!optional.isPresent()) {
			return null; 
		}
		return optional.get();	}

	public List<Supplier> getAll() {
		// TODO Auto-generated method stub
		return supplierRepository.findAll();
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		supplierRepository.deleteById(id);
		
	}

}
