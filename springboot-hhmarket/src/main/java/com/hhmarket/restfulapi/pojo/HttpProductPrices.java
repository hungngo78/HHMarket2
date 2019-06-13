package com.hhmarket.restfulapi.pojo;

public class HttpProductPrices {
	private int productId;
	private float 	minPrice;
    private float 	maxPrice;
    
    public HttpProductPrices(int productId, float minPrice, float maxPrice) {
    	this.productId = productId;
    	this.minPrice = minPrice;
    	this.maxPrice = maxPrice;
    }
    public HttpProductPrices() {
    	
    }
    public int getProductId() {
		return this.productId;
	}
	public void setProductId(int id) {
		this.productId = id;
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
}
