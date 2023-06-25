package com.springboot.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.model.Vendor;
import com.springboot.main.service.VendorService;

@RestController
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	private VendorService vendorService; 
	
	@PostMapping("/add")
	public Vendor postVendor(@RequestBody Vendor vendor) {
		vendor = vendorService.insert(vendor);
		return vendor; 
	}
}
