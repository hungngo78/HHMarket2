package com.hhmarket.restfulapi.daos;

import java.util.List;
import java.util.Set;

import com.hhmarket.restfulapi.model.Category;

public interface CategoryRepository {
	public List<Category> findCategoryByCriteria(Set<String> criteria);
	
	public List<Category> findAll();
	public Category findById(Integer id);
	public List<Category> findByName(String name);
	public void save(Category entity);
}
