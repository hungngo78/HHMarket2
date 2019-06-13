package com.hhmarket.restfulapi.daos;

import java.util.List;
import java.util.Optional;

import com.hhmarket.restfulapi.model.ProductDetails;
import com.hhmarket.restfulapi.pojo.HttpProductPrices;

public interface ProductDetailsRepository {
	public List<ProductDetails> findAll();
	public Optional<ProductDetails> findById(Integer id);
	
	List<ProductDetails> findByProductId(int id);
	ProductDetails findByProductIdAndPrice(int id, float price);
	
	HttpProductPrices getMinMaxPrice(int productId);
	
	public void save(ProductDetails entity);
}
