package com.hhmarket.restfulapi.pojo;

public class HttpSearchProduction {
	private int productId;
	//private String name;
	private String productionName;	
    private String description;
    
    private String picture;
    private String color;
    //private float price;
    private float 	minPrice;
    private float 	maxPrice;
    
    private String categoryName;
    private String categoryDescription;
    
    private String overallRating;
    private int reviewNumber;
    
    public HttpSearchProduction() {
    }
    
    public int getProductId() {
		return this.productId;
	}
	
	public void setProductId(int id) {
		this.productId = id;
	}
	
	/*public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }*/
	public String getProductionName() {
        return productionName;
    }

    public void setProductionName(final String name) {
        this.productionName = name;
    }
    
    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
    public String getPicture() { 
    	return this.picture;
    }
    public void setPicture(String picture) {
    	this.picture = picture;
    }
    public String getColor() { 
    	return this.color;
    }
    public void setColor(String color) {
    	this.color = color;
    }
    /*public float getPrice() {
    	return this.price;
    }
    public void setPrice(float price) {
    	this.price = price;
    }*/
    public float getMinPrice() {
    	return this.minPrice;
    }
    public void setMinPrice(float price) {
    	this.minPrice = price;
    }
    public float getMaxPrice() {
    	return this.maxPrice;
    }
    public void setMaxPrice(float price) {
    	this.maxPrice = price;
    }
    
    public String getCategoryName() {
    	return this.categoryName;
    }
    public void setCategoryName(String _categoryName) {
    	this.categoryName = _categoryName;
    }
    public String getCategoryDescription() {
    	return this.categoryDescription;
    }
    public void setCategoryDescription(String _categoryDescription) {
    	this.categoryDescription = _categoryDescription;
    }
    
    public String getOverrallRating() {
		return this.overallRating;
	}
	public void setOverrallRating(String number) {
		this.overallRating = number;
	}
	public int getReviewNumber() {
		return this.reviewNumber;
	}
	public void setReviewNumber(int number) {
		this.reviewNumber = number;
	}
}
