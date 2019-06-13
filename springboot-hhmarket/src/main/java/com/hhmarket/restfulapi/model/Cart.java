package com.hhmarket.restfulapi.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cart {
	//`CartId`   INT  AUTO_INCREMENT,
	//PRIMARY KEY (`CartId`),
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartId;
	
    //`DateOpen` DATETIME,
	private LocalDate dateOpen;
    
	//`UserId`   INT      NULL,
    //FOREIGN KEY (`UserId`) REFERENCES `User` (`UserId`)
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@JsonIgnore
	@OneToMany (mappedBy = "cart", cascade = CascadeType.ALL)
	private List<CartDetails> cartDetailsList;
	
	public Cart() {
	}
	
	public int getCartId() {
		return this.cartId;
	}
	
	public void setCartId(int id) {
		this.cartId = id;
	} 
	
	public LocalDate getDateOpen() {
		return this.dateOpen;
	}
	
	public void setDateOpen(LocalDate date) {
		this.dateOpen = date;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User _user) {
		this.user = _user;
	}
	
	public List<CartDetails> getCartDetailsList() {
		return this.cartDetailsList;
	}
	
	public void setCartDetailsList(List<CartDetails> _cartDetailsList) {
		this.cartDetailsList = _cartDetailsList;
	}
}





