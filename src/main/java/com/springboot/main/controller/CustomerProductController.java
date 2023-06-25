package com.springboot.main.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.model.Customer;
import com.springboot.main.model.CustomerProduct;
import com.springboot.main.model.Product;
import com.springboot.main.service.CustomerProductService;
import com.springboot.main.service.CustomerService;
import com.springboot.main.service.InwardRegisterService;
import com.springboot.main.service.ProductService;

@RestController
@RequestMapping("/customer-product")
public class CustomerProductController {

	@Autowired
	private CustomerService customerService; 
	@Autowired
	private ProductService productService; 
	@Autowired
	private CustomerProductService customerProductService;
	@Autowired
	private InwardRegisterService inwardRegisterService;
	
	@PostMapping("/purchase/{customerId}/{productId}")
	public ResponseEntity<?> purchaseApi(@RequestBody CustomerProduct customerProduct,
							@PathVariable("customerId") int customerId, 
							@PathVariable("productId") int productId) {
		
		/* Step 1: validate Ids and fetch objects  */
		Customer customer  = customerService.getById(customerId);
		if(customer == null )
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid customer ID given");
		
		Product product = productService.getById(productId);
		if(product == null )
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid product ID given");
		
		/* Step 2: set customer and product to customerProduct*/
		customerProduct.setCustomer(customer);
		customerProduct.setProduct(product);
		customerProduct.setDateOfPurchase(LocalDate.now());
		
		/* Step 3: check if that product is available in proper quantity in InwardRegister*/
		boolean status = inwardRegisterService.checkQuantity(productId,customerProduct.getQuantityPuchased());
		if(status == false)
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body("Product out of stock");
		
		/* Step 4: Save customerProduct object in DB */
		customerProduct = customerProductService.insert(customerProduct);
		return ResponseEntity.status(HttpStatus.OK)
				.body(customerProduct);
	}
	
	@GetMapping("/report")
	public HashMap<String, Integer> customerReport() {
		
		List<CustomerProduct> list = customerProductService.getAll();
		HashMap<String,Integer> map = new HashMap<>();
		
		list.stream().forEach(entry->{
			map.put(entry.getCustomer().getName(),entry.getQuantityPuchased());
		});
		
		return map;
	}
}
