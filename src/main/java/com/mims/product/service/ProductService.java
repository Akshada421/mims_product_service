package com.mims.product.service;

import java.util.List;
import java.util.Optional;

import com.mims.product.entity.Product;

public interface ProductService {

	public Product saveProduct(Product product);

	public Optional<Product> getProduct(int productId);

	public List<Product> getAllProducts();

	public Product updateProduct(Product product);

	public boolean deleteProduct(int productId);

	List<Product> getProductByPrice(Long price);

	List<Product> getProductByNameAndBrand(String name, String brand);

}
