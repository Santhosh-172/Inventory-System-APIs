package com.springboot.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.exception.ResourceNotFoundException;
import com.springboot.main.model.Product;
import com.springboot.main.model.Vendor;
import com.springboot.main.service.ProductService;
import com.springboot.main.service.VendorService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService; //injecting Service in Controller : DI(Dependency Injection)
	
	@Autowired
	private VendorService vendorService; 
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello Spring!!";
	}
	
	/* 
	 PATH: /product/add
	 Method: POST
	 RequestBody: Product product
	 response: product 
	 PathVariable: none
	 */
	@PostMapping("/add")
	public ResponseEntity<?> postProduct(
							   @RequestBody Product product) {
		product = productService.insert(product);
		return  ResponseEntity.status(HttpStatus.OK)
				.body(product);
	}
	
	/* 
	 PATH: /product/all
	 Method: GET
	 RequestBody: None
	 response: List<Product> 
	 PathVariable: None
	 */
	@GetMapping("/all")
	public List<Product> getAllProducts() {
		List<Product> list =  productService.getAllProducts();
		return list; 
	}
	
	/* 
	 PATH: /product/one
	 Method: GET
	 RequestBody: None
	 response: Product 
	 PathVariable: ID
	 */
	@GetMapping("/one/{id}") //this id is called as path variable
	public ResponseEntity<?> getProduct(@PathVariable("id") int id) {
		Product product  = productService.getproduct(id);
		if(product == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid ID given");
		}
		return ResponseEntity.status(HttpStatus.OK).body(product); 
	}
	//Not a professional way 
	@GetMapping("/one/alternate/{id}")
	public Object getProductAlternate(@PathVariable("id") int id) {
		try {
			Product product  = productService.getproductAlternate(id);
			return product; 
		} catch (ResourceNotFoundException e) {
			 return e.getMessage();
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable("id") int id, @RequestBody Product newProduct) {
		//Step 0 : validation for request body: newProduct
		if(newProduct.getTitle() == null || !newProduct.getTitle().trim().matches("[a-zA-Z0-9- *]+"))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Title has to have valid format [a-zA-Z0-9- ]");
		
		if(newProduct.getDescription() == null || newProduct.getDescription().equals(""))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Description cannot be nullor blank");
		
		if(newProduct.getPrice() == 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Price must have a value other than 0");
		
		//Step 1: Validate the id given 
		Product oldProduct  = productService.getproduct(id);
		if(oldProduct == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid ID given");
		}
		/* 2 techniques {old has id whereas new does not have id}
		 * 1. Transfer new values to old(that has id)
		 * 2. Transfer id from old to new.  
		 */
		newProduct.setId(oldProduct.getId());
	    newProduct = productService.insert(newProduct);
	    return ResponseEntity.status(HttpStatus.OK)
				.body(newProduct);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
		//Step 1: validate id
		Product product  = productService.getproduct(id);
		if(product == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid ID given");
		}
		
		productService.deleteProduct(product);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body("Product deleted..");

	}
}






