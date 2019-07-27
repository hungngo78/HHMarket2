package com.hhmarket.restfulapi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductDetails {
	//`ProductDetailsId` INT   AUTO_INCREMENT,
    //PRIMARY KEY (`ProductDetailsId`),
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productDetailsId;
	
    //`Color`            VARCHAR (50)  NOT NULL,
	private String color;
	
    //`Size`             VARCHAR (20)  NOT NULL,
	private String size;
	
    //`Picture`          VARCHAR (200) NOT NULL,
	private String picture;
	
    //`Price`            DECIMAL (18)   NOT NULL,
	@Column(name="price", precision=10, scale=2)
	private float price;
	
    //`Amount`           SMALLINT       NOT NULL,
	private int amount;
	
    //`ProductId`        INT            NOT NULL,
    //FOREIGN KEY (`ProductId`) REFERENCES `Product` (`ProductId`)
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@JsonIgnore
	@OneToMany  (mappedBy = "productDetails", cascade = CascadeType.ALL)
	private List<OrderDetails> orderDetailsList;
	
	@JsonIgnore
	@OneToMany  (mappedBy = "productDetails", cascade = CascadeType.ALL)
	private List<CartDetails> cartDetailsList;
	
	public Product getProduct() {
		return this.product;
	}
	
	public void setProduct(Product pro) {
		this.product = pro;
	}
	
	public ProductDetails() {
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
    
    public int getAmount() {
        return this.amount;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }
    
    public List<OrderDetails> getOrderDetailsList() {
		return this.orderDetailsList;
	}
	
	public void setOrderDetailsList(List<OrderDetails> list) {
		this.orderDetailsList = list; 
	}
	
	public List<CartDetails> getCartDetailsList() {
		return this.cartDetailsList;
	}
	
	public void setCartDetailsList(List<CartDetails> list) {
		this.cartDetailsList = list; 
	}
}





