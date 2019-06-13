package com.hhmarket.restfulapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CartDetails {
	//`CartDetailsId`    INT  AUTO_INCREMENT,
	//PRIMARY KEY (`CartDetailsId`),
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartDetailsId;
	
    //`Amount`           SMALLINT     NOT NULL,
	private int amount;
	
    //`ExtendedPrice`    DECIMAL (18) NOT NULL,
	private float extendedPrice;
	
    //`Type`             SMALLINT     NOT NULL,
	private int type;

    //`CartId`           INT          NOT NULL,	
    //FOREIGN KEY (`CartId`) REFERENCES `Cart` (`CartId`),
	@JsonIgnore
	@ManyToOne
	@JoinColumn (name="cart_id")
	private Cart cart;
	
    //`ProductDetailsId` INT          NOT NULL,	
    //FOREIGN KEY (`ProductDetailsId`) REFERENCES `ProductDetails` (`ProductDetailsId`)
	@JsonIgnore
	@ManyToOne
	@JoinColumn (name="product_details_id")
	private ProductDetails productDetails;
	
	public CartDetails() {
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
	
	public Cart getCart() {
		return this.cart;
	}
	
	public void setCart(Cart _cart) {
		this.cart = _cart;
	}
	
	public ProductDetails getProductDetails() {
		return this.productDetails;
	}
	
	public void setProductDetails(ProductDetails _productDetails) {
		this.productDetails = _productDetails;
	}
}






