package com.hhmarket.restfulapi.daos;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.hhmarket.restfulapi.model.Product;

public interface ProductRepository  {
	public List<Product> findProductByCriteria(int categoryId, Set<String> criteria);
	
	public List<Product> findAll();
	public Optional<Product> findById(int id);
	public List<Product> findByName(String name);
	public List<Product> findByCategoryId(int categoryId) ;
	public void save(Product entity);
}
