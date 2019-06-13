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
public class Product {
	//`ProductId`   INT	AUTO_INCREMENT,
	//PRIMARY KEY (`ProductId`),
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
	
    //`Name`        VARCHAR (100) NOT NULL,
	private String name;
	
    //`Description` TEXT,
    private String description;
    
	//`CategoryId`  INT            NOT NULL,
    //FOREIGN KEY (`CategoryId`) REFERENCES `Category` (`CategoryId`)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @JsonIgnore
    @OneToMany (mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductDetails> productDetailsList;
    
    //`UserId`      INT            NOT NULL,
    //FOREIGN KEY (`UserId`) REFERENCES `User` (`UserId`),
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @JsonIgnore
    @OneToMany (mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviewList;
   
    public Product() {
    }
    
    public Product(String name) {
    	this.name = name;
    }
    
    public int getProductId() {
		return this.productId;
	}
	
	public void setProductId(int id) {
		this.productId = id;
	}
	
	public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
    
    public User getUser() {
    	return this.user;
    }
    
    public void setUser(User u) {
    	this.user = u;
    }
    
    public Category getCategory() {
    	return this.category;
    }
    
    public void setCategory (Category cate) {
    	this.category = cate;
    }
    
    public List<ProductDetails> getProductDetailsList() {
    	return this.productDetailsList;
    }
    
    public void setProductDetailsList(List<ProductDetails> list) {
    	this.productDetailsList = list;
    }
    
    public List<Review> getReviewList() {
    	return this.reviewList;
    }
    
    public void setReviewList(List<Review> list) {
    	this.reviewList = list;
    }
}
