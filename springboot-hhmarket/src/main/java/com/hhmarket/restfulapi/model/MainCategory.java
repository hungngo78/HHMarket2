package com.hhmarket.restfulapi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MainCategory {
	//`MainCategoryId` INT AUTO_INCREMENT,
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int mainCategoryId;
	
    //`Name`           VARCHAR (100) NOT NULL,
	private String name;
	
    //`Description`    TEXT,
    private String description;
    
    @JsonIgnore
    @OneToMany (mappedBy = "mainCategory", cascade = CascadeType.ALL)
    private List<Category> categoryList;
    
    public MainCategory() {
    }
    
    public MainCategory(String _name) {
    	this.name = _name;
    }
    
    public int getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(final int id) {
        this.mainCategoryId = id;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getDescription() {
    	return this.description;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public List<Category> getCategoryList() {
    	return this.categoryList;
    }
    
    public void setCategoryList(List<Category> _categoryList) {
    	this.categoryList = _categoryList;
    }
}





