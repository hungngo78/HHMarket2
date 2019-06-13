package com.hhmarket.restfulapi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	//`CategoryId`     INT AUTO_INCREMENT,
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int categoryId;
	
    //`Name`           VARCHAR (100) NOT NULL,
	private String name;
	
    //`Picture`        VARCHAR (200) NOT NULL,
	private String picture;
	
    //`Description`    TEXT,
	private String description;
	
    //`MainCategoryId` INT            NOT NULL,
	// FOREIGN KEY (`MainCategoryId`) REFERENCES `MainCategory` (`MainCategoryId`)
	@JsonIgnore
	@ManyToOne
	@JoinColumn (name="main_category_id")
	private MainCategory mainCategory;
	
	@JsonIgnore
	@OneToMany (mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> productList;
	
	public Category() {
	}
	
	public Category(String name) {
		this.name = name;
	}
	
	public int getCategoryId() {
		return this.categoryId;
	}
	
	public void setCategoryId(int id) {
		this.categoryId = id;
	}
	
	public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
    
    public String getPicture() {
        return this.picture;
    }

    public void setPicture(final String picture) {
        this.picture = picture;
    }
    
    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
    
    public MainCategory getMainCategory() {
		return this.mainCategory;
	}
	
	public void setMainCategory(MainCategory _mainCategory) {
		this.mainCategory = _mainCategory;
	}
	
	public List<Product> getProductList() {
		return this.productList;
	}
	
	public void setProductList(List<Product> list) {
		this.productList = list;
	}
}
