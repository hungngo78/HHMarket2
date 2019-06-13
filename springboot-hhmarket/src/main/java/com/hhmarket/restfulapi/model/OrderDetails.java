package com.hhmarket.restfulapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderDetails {
	//`OrderDetailsId`   INT   AUTO_INCREMENT,
	//PRIMARY KEY (`OrderDetailsId`),
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderDetailsId;
	
    //`Amount`           SMALLINT     NOT NULL,
	private int amount;
	
    //`ExtendedPrice`    DECIMAL (18) NOT NULL,
	private float extendedPrice;
	
    //`OrderId`          INT          NOT NULL,
	//FOREIGN KEY (`OrderId`) REFERENCES `Order` (`OrderId`),
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
    //`ProductDetailsId` INT          NOT NULL,
    //FOREIGN KEY (`ProductDetailsId`) REFERENCES `ProductDetails` (`ProductDetailsId`)
	@JsonIgnore
	@ManyToOne 
	@JoinColumn(name = "product_details_id")
	private ProductDetails productDetails;
	
	public OrderDetails() {
	}
	
	public OrderDetails(int _amount, float _extendedPrice) {
		this.amount = _amount;
		this.extendedPrice = _extendedPrice;
	}
	
	public int getOrderDetailsId() {
		return this.orderDetailsId;
	}
	
	public void setOrderDetailsId(int id) {
		this.orderDetailsId = id;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public void setAmount(int _amount) {
		this.amount = _amount;
	}
	
	public float getExtendedPrice() {
		return this.extendedPrice;
	}
	
	public void setExtendedPrice(float price) {
		this.extendedPrice = price;
	}
	
	public Order getOrder() {
		return this.order;
	}
	
	public void setOrder(Order _order) {
		this.order = _order;
	}
	
	public ProductDetails getProductDetails() {
		return this.productDetails;
	}
	
	public void setProductDetails(ProductDetails _productDetails) {
		this.productDetails = _productDetails;
	}
}




