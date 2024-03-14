package com.mims.product.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mims.product.entity.Product;
import com.mims.product.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;

	@PostMapping
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		logger.info("-- Request for saving the Product --");
		ResponseEntity<Product> responseEnitity = null;
		try {
			Product productResponse = productService.saveProduct(product);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Success", "Product has been saved");
			responseEnitity = new ResponseEntity<>(productResponse, headers, HttpStatus.CREATED);
		} catch (Exception ex) {
			responseEnitity = ResponseEntity.badRequest().header("Failure", ex.getMessage()).body(null);
		}
		return responseEnitity;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Product>> getProdusts() {
		logger.info("-- Request for fetching all the products --");
		ResponseEntity<List<Product>> responseEnitity = null;
		try {
			List<Product> products = productService.getAllProducts();
			if (products.size() == 0) {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Success", "Products not found");
				return new ResponseEntity<>(products, headers, HttpStatus.NOT_FOUND);
			}		
			responseEnitity = new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception ex) {
			responseEnitity = ResponseEntity.badRequest().header("Failure", ex.getMessage()).body(null);
		}
		return responseEnitity;
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") int product_Id) {
		logger.info("-- Request for fetching the product by productId --");
		ResponseEntity<Product> responseEnitity = null;
		try {
			Optional<Product> product = productService.getProduct(product_Id);
			if (!product.isPresent()) {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Success", "Product not found");
				return new ResponseEntity<>(product.get(), headers, HttpStatus.NOT_FOUND);
			}
			responseEnitity = new ResponseEntity<>(product.get(), HttpStatus.OK);
		} catch (Exception ex) {
			responseEnitity = ResponseEntity.badRequest().header("Failure", ex.getMessage()).body(null);
		}
		return responseEnitity;
	}

	@GetMapping("/price")
	public ResponseEntity<List<Product>> getProduct(@RequestParam long price) {
		logger.info("-- Request for fetching the product by price --");
		ResponseEntity<List<Product>> responseEnitity = null;
		try {
			List<Product> products = productService.getProductByPrice(price);
			if (products.size() == 0) {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Success", "Products not found");
				return new ResponseEntity<>(products, headers, HttpStatus.NOT_FOUND);
			}
			responseEnitity = new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception ex) {
			responseEnitity = ResponseEntity.badRequest().header("Failure", ex.getMessage()).body(null);
		}
		return responseEnitity;
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getProduct(@RequestParam String name,@RequestParam String brand) {
		logger.info("-- Request for fetching the product by name and brand --");
		ResponseEntity<List<Product>> responseEnitity = null;
		try {
			List<Product> products = productService.getProductByNameAndBrand(name, brand);
			if (products.size() == 0) {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Success", "Products not found");
				return new ResponseEntity<>(products, headers, HttpStatus.NOT_FOUND);
			}
			responseEnitity = new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception ex) {
			responseEnitity = ResponseEntity.badRequest().header("Failure", ex.getMessage()).body(null);
		}
		return responseEnitity;
	}

	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		logger.info("-- Request for updating the Product --");
		ResponseEntity<Product> responseEnitity = null;
		try {
			Product productResponse = productService.saveProduct(product);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Success", "Product has been updated");
			responseEnitity = new ResponseEntity<>(productResponse, headers, HttpStatus.CREATED);
		} catch (Exception ex) {
			responseEnitity = ResponseEntity.badRequest().header("Failure", ex.getMessage()).body(null);
		}
		return responseEnitity;
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<String> deleteUser(@PathVariable("productId") int product_Id) {
		logger.info("-- Request for deleting the Product --");
		ResponseEntity<String> responseEnitity = null;
		try {
			boolean productDeleted = productService.deleteProduct(product_Id);
			if (!productDeleted) {
				return ResponseEntity.notFound().header("Success", "Product not found with productId :" + product_Id).build();
			}
			HttpHeaders headers = new HttpHeaders();
			headers.add("Success", "Product has been deleted");
			responseEnitity = new ResponseEntity<>("Product with productId :" + product_Id + " has been deleted", headers, HttpStatus.OK);
		} catch (Exception ex) {
			responseEnitity = ResponseEntity.badRequest().header("Failure", ex.getMessage()).body(null);
		}
		return responseEnitity;

	}

}
