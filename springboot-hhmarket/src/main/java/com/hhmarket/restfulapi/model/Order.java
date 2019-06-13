package com.hhmarket.restfulapi.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="shopping_order")
public class Order {
	//`OrderId`      INT   AUTO_INCREMENT,
	//PRIMARY KEY (`OrderId`),
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
	
    //`OrderDate`    DATETIME,
	private LocalDate orderDate;
	
    //`Status`       SMALLINT       NOT NULL,
	private int status;
	
    //`DeliveryDate` DATETIME,
	private LocalDate deliveryDate;
	
    //`DeliveryFee`  DECIMAL (18)   NOT NULL,
	private float deliveryFee;
	
    //`Note`         TEXT,
	private String note;
	
    //`UserId`       INT            NOT NULL,
    //FOREIGN KEY (`UserId`) REFERENCES `User` (`UserId`)
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@JsonIgnore
	@OneToMany (mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetails> orderDetailsList;
	
	public Order() {
	}
	
	public int getOrderId() {
		return this.orderId;
	}
	
	public void setOrderId(int id) {
		this.orderId = id;
	}
	
	public LocalDate getOrderDate() {
		return this.orderDate;
	}
	
	public void setOrderDate(LocalDate date) {
		this.orderDate = date;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public LocalDate getDeliveryDate() {
		return this.deliveryDate;
	}
	
	public void setDeliveryDate(LocalDate date) {
		this.deliveryDate = date;
	}
	
	public float getDeliveryFee() {
		return this.deliveryFee;
	}
	
	public void setDeliveryFee(float fee) {
		this.deliveryFee = fee;
	}
	
	public String getNote() {
		return this.note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User _user) {
		this.user = _user;
	}
	
	public List<OrderDetails> getOrderDetailsList() {
		return this.orderDetailsList;
	}
	
	public void setOrderDetailsList (List<OrderDetails> list) {
		this.orderDetailsList = list;
	}
}










