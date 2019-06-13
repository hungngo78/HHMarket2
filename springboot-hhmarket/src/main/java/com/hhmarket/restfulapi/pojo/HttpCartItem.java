package com.hhmarket.restfulapi.pojo;

public class HttpCartItem {
	private int cartId;
	private int cartDetailsId;
	private int amount;
	private int type;
	private float extendedPrice;
	
	private int productId;
	private String productName;
	
	private int productDetailsId;
	private String color;
	private String size;
	private String picture;
	private float price;
	private int totalAmountProduction;		// pdetail.Amount
	
	public HttpCartItem() {
		
	}
	public int getCartId() {
		return this.cartId;
	}
	
	public void setCartId(int id) {
		this.cartId = id;
	}
	public int getCartDetailsId() {
		return this.cartDetailsId;
	}
	
	public void setCartDetailsId(int id) {
		this.cartDetailsId = id;
	} 
	
	public int getAmount() {
		return this.amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public float getExtendedPrice() {
		return this.extendedPrice;
	}
	
	public void setExtendedPrice(float price) {
		this.extendedPrice = price;
	}
	
	public int getType() {
		return this.type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getProductId() {
		return this.productId;
	}
	
	public void setProductId(int id) {
		this.productId = id;
	}
	
	public String getProductName() {
        return productName;
    }

    public void setProductName(final String name) {
        this.productName = name;
    }
    
    public int getProductDetailsId() {
		return this.productDetailsId;
	}
	
	public void setProductDetailsId(int id) {
		this.productDetailsId = id;
	}
	
	public String getColor() {
        return this.color;
    }

    public void setColor(final String color) {
        this.color = color;
    }
    
    public String getSize() {
        return this.size;
    }

    public void setSize(final String size) {
        this.size = size;
    }
    
    public String getPicture() {
        return this.picture;
    }

    public void setPicture(final String picture) {
        this.picture = picture;
    }
    
    public float getPrice() {
        return this.price;
    }

    public void setPrice(final float price) {
        this.price = price;
    }
    
    public int getTotalAmountProduction() {
        return this.totalAmountProduction;
    }

    public void setotalAmountProduction(final int amount) {
        this.totalAmountProduction = amount;
    }
}
