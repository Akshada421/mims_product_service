package com.mims.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mims.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByPrice(Long price);

	List<Product> findByNameAndBrand(String name, String brand);
	
	

}
