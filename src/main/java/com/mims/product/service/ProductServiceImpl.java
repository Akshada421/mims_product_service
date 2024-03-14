package com.mims.product.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mims.product.entity.Product;
import com.mims.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductRepository productRepository;

	@Override
	public Product saveProduct(Product product) {
		logger.info("Saving Product with Product Details : " + product.toString());
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> getProduct(int productId) {
		logger.info("Fetching Product with Product Id : " + productId);
		return productRepository.findById(productId);
	}

	@Override
	public List<Product> getAllProducts() {
		logger.info("Fetching all the products -- ");
		return productRepository.findAll();
	}

	@Override
	public Product updateProduct(Product product) {
		logger.info("Updating Product with Product Details : " + product.toString());
		return productRepository.save(product);
	}

	@Override
	public boolean deleteProduct(int productId) {
		logger.info("Deleting Product with Product Id : " + productId);
		Optional<Product> product = null;
		product = productRepository.findById(productId);
		if (product.isPresent())
			productRepository.delete(product.get());
		product = productRepository.findById(productId);
		return product.isPresent();
	}

	@Override
	public List<Product> getProductByPrice(Long price) {
		logger.info("Fetching Product with product price  : " + price);
		return productRepository.findByPrice(price);
	}

	@Override
	public List<Product> getProductByNameAndBrand(String name, String brand) {
		logger.info("Fetching Products by Name :{} and Brand :{}", name, brand);
		return productRepository.findByNameAndBrand(name, brand);
	}

}
