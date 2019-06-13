package com.hhmarket.restfulapi.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "user")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
    private int userId;
	
	@Column(name="user_name")
	private String userName;	
	
	@Column(name="password")
    private String password;	
	
	@Column(name="email")
    private String email;	
	
	@Column(name="first_name")
    private String firstName;
	
	@Column(name="last_name")
    private String lastName;
	
	@Column(name="address")
    private String address;
	
	@Column(name="city")
    private String city;	
	
	@Column(name="state")
    private String state;
	
	@Column(name="zip_code")
    private String zipCode;
    
	@JsonIgnore
    @OneToOne(mappedBy = "user", cascade =  CascadeType.ALL)
    private Cart cart;
	
	@JsonIgnore
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orderList;
    
	@JsonIgnore
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList;
    
	@JsonIgnore
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Product> productList;
    
    public User() {
    }
    
    public User(String _userName, String _password) {
        this.userName = _userName;
        this.password = _password;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(final int id) {
        this.userId = id;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(final String name) {
        this.userName = name;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(final String pass) {
        this.password = pass;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }
    
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }
    
    /*
    public Cart getCart() {
        return this.cart;
    }

    public void setCart(Cart _cart) {
        this.cart = _cart;
    }*/
    
    public List<Order> getOrderList() {
        return this.orderList;
    }

    public void setOrderList(List<Order> _orderList) {
        this.orderList = _orderList;
    }
    
    public List<Review> getReviewList() {
        return this.reviewList;
    }

    public void setReviewList(List<Review> _reviewList) {
        this.reviewList = _reviewList;
    }
    
    public List<Product> getProductList() {
        return this.productList;
    }

    public void setProductList(List<Product> _productList) {
        this.productList = _productList;
    }
    
    @Override
    public String toString() {
        return String.format("User[id=%d, userName='%s', password='%s']", userId, userName, password);
    }
    
}
