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

import com.springboot.main.dto.OutwardRegisterDto;
import com.springboot.main.model.Godown;
import com.springboot.main.model.InwardRegister;
import com.springboot.main.model.OutwardRegister;
import com.springboot.main.model.Product;
import com.springboot.main.model.Supplier;
import com.springboot.main.service.CustomerProductService;
import com.springboot.main.service.GodownService;
import com.springboot.main.service.OutwardRegisterService;
import com.springboot.main.service.ProductService;
import com.springboot.main.service.SupplierService;

@RestController
@RequestMapping("/outward")
public class OutwardRegisterController {

	@Autowired
	private ProductService productService;

	@Autowired
	private GodownService godownService;

	@Autowired
	private CustomerProductService customerProductService;
	
	@Autowired
	OutwardRegisterService outwardRegisterService;

	@PostMapping("/add/{productId}/{godownId}")
	public ResponseEntity<?> postOutwardRegister(@RequestBody OutwardRegister outwardRegister,
			@PathVariable("productId") int productId, @PathVariable("godownId") int godownId) {

		/* Validate Ids and fetch Objects */

		Product product = productService.getById(productId);
		if (product == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid product ID given");

		Godown godown = godownService.getById(godownId);
		if (godown == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid godown ID given");

		
		/* Attach all objects to inwardRegister */
		outwardRegister.setProduct(product);
		outwardRegister.setGodown(godown);
		

		outwardRegister.setDateOfDelivery(LocalDate.now());

		/* save inwardRegister object */
		outwardRegister = outwardRegisterService.insert(outwardRegister);
		return ResponseEntity.status(HttpStatus.OK).body(outwardRegister);
	}

	@GetMapping("/all")
	public List<OutwardRegister> getAll() {
		return outwardRegisterService.getAll();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {

		OutwardRegister outwardRegister = outwardRegisterService.getById(id);
		if (outwardRegister == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Id");

		godownService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/report")
	public List<OutwardRegisterDto> outwardReport() {
		/* Go to db and fetch all Inward entries.. */
		List<OutwardRegister> list = outwardRegisterService.getAll();
		List<OutwardRegisterDto> listDto = new ArrayList<>();
		/* convert the response into UI format */
		list.stream().forEach(entry -> {
			OutwardRegisterDto dto = new OutwardRegisterDto(); // 100X 200X
			dto.setProductTitle(entry.getProduct().getTitle());
			dto.setGodownManager(entry.getGodown().getManager());
			dto.setGodownLocation(entry.getGodown().getLocation());
			dto.setProductQuantity(entry.getQuantity());
			dto.setRecieptNo(entry.getRecieptNo());
			listDto.add(dto); // 100X 200X
		});

		return listDto;
	}
}
