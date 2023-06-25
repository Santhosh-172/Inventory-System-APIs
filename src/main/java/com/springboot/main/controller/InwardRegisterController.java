package com.springboot.main.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.dto.InwardRegisterDto;
import com.springboot.main.model.Godown;
import com.springboot.main.model.InwardRegister;
import com.springboot.main.model.Product;
import com.springboot.main.model.Supplier;
import com.springboot.main.service.GodownService;
import com.springboot.main.service.InwardRegisterService;
import com.springboot.main.service.ProductService;
import com.springboot.main.service.SupplierService;

@RestController
@RequestMapping("/inwardregister")
public class InwardRegisterController {

	@Autowired
	private ProductService productService; 
	
	@Autowired
	private GodownService godownService;
	
	@Autowired
	private SupplierService supplierService; 
	
	@Autowired
	private InwardRegisterService inwardRegisterService;
	
	@PostMapping("/add/{productId}/{godownId}/{supplierId}")
	public ResponseEntity<?> postInwardRegister(@RequestBody InwardRegister inwardRegister,
								   @PathVariable("productId") int productId,
								   @PathVariable("godownId") int godownId,
								   @PathVariable("supplierId") int supplierId) {
		
		/* Validate Ids and fetch Objects */
		
		Product product = productService.getById(productId);
		if(product == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid product ID given");
		
		Godown godown = godownService.getById(godownId);
		if(godown == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid godown ID given");
		
		Supplier supplier  = supplierService.getById(supplierId);
		if(supplier == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid supplier ID given");
		
		/* Attach all objects to inwardRegister */
		inwardRegister.setProduct(product);
		inwardRegister.setGodown(godown);
		inwardRegister.setSupplier(supplier);
		
		inwardRegister.setDateOfSupply(LocalDate.now());
		
		/* save inwardRegister object  */
		inwardRegister = inwardRegisterService.insert(inwardRegister);
		return ResponseEntity.status(HttpStatus.OK)
				.body(inwardRegister);
	}
	
	@GetMapping("/all")
	public List<InwardRegister> getAll() {
		return inwardRegisterService.getAll();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		
			InwardRegister inwardRegister = inwardRegisterService.getById(id);
			if(inwardRegister == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid Id");
		
		
		godownService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/report")
	public List<InwardRegisterDto> inwardReport() {
		/* Go to db and fetch all Inward entries.. */
		List<InwardRegister> list = inwardRegisterService.getAll();
		List<InwardRegisterDto> listDto = new ArrayList<>();
		/* convert the response into UI format */
		 list.stream().forEach(entry->{
			 InwardRegisterDto dto = new InwardRegisterDto(); //100X 200X
			 dto.setProductTitle(entry.getProduct().getTitle());
			 dto.setProductQuantity(entry.getQuantity());
			 dto.setGodownLocation(entry.getGodown().getLocation());
			 dto.setGodownManager(entry.getGodown().getManager().getName());
			 dto.setSupplierName(entry.getSupplier().getName());
			 dto.setSupplierCity(entry.getSupplier().getCity());
			 listDto.add(dto); //100X 200X
		 });
		 
		return listDto; 
	}
}
