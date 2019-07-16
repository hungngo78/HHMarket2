package com.hhmarket.restfulapi.pojo;

public class HttpProduction {
	private int productId;
	private String productionName;
    private String description;
    
    private float 	minPrice;
    private float 	maxPrice;
    private String picture;
    private String color;
    
    private String overallRating = "0";
    private int reviewNumber;
    
    public HttpProduction() {
    	
    }
    
    public int getProductId() {
		return this.productId;
	}
	
	public void setProductId(int id) {
		this.productId = id;
	}
	
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
