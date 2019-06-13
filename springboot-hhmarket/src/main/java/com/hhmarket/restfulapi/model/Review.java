package com.hhmarket.restfulapi.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review {
	//`ReviewId`      INT  AUTO_INCREMENT,
	//PRIMARY KEY (`ReviewId`),
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reviewId;
	
	//[Title]         NCHAR (50)     NOT NULL,
	private String title;
	
	//`Content`       TEXT,
	private String content;
	
    //`OverallRating` SMALLINT       NOT NULL,
	private int overallRating;
	
	//`ReviewDate`    DATETIME       NOT NULL,
	private LocalDate reviewDate;
	
    //`UserId`        INT            NOT NULL,
	//FOREIGN KEY (`UserId`) REFERENCES `User` (`UserId`)
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
    //`ProductId`     INT            NOT NULL,
    //FOREIGN KEY (`ProductId`) REFERENCES `Product` (`ProductId`)
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public Review() {
	}
	
	public int getReviewId() {
		return this.reviewId;
	}
	
	public void setReviewId(int id) {
		this.reviewId = id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String _title) {
		this.title = _title;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String _content) {
		this.content = _content;
	}
	
	public int getOverallRating() {
		return this.overallRating;
	}
	
	public void setOverallRating(int rating) {
		this.overallRating = rating;
	}
	
	public LocalDate getReviewDate() {
		return this.reviewDate;
	}
	
	public void setReviewDate(LocalDate date) {
		this.reviewDate = date;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User u) {
		this.user = u;
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	public void setProduct(Product pro) {
		this.product = pro;
	}
}






