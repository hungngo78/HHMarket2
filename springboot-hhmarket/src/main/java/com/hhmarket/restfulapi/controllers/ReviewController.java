package com.hhmarket.restfulapi.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhmarket.restfulapi.model.Review;
import com.hhmarket.restfulapi.pojo.HttpReview;
import com.hhmarket.restfulapi.pojo.HttpRating;
import com.hhmarket.restfulapi.services.ProductionService;

@RestController
@RequestMapping(path = "/review")
public class ReviewController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
    private ProductionService productionService;
	
	//location: http://localhost:8080/review/add_review_item
	@PostMapping(path= "/add_review_item", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> add_review_item(@RequestBody HttpReview reviewItem) {
		try {
			if (reviewItem != null) {
				Review review = this.productionService.addReview(reviewItem);
				return ResponseEntity.ok(review);
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	//location: http://localhost:8080/review/get_reviews_by_productId/1001
	@GetMapping(path="/get_reviews_by_productId/{productId}", produces = "application/json")
	public ResponseEntity<Object> get_reviews_by_productId(@PathVariable int productId)
	{
		List<HttpReview> reviews = productionService.getReviewByProductId(productId);

		return new ResponseEntity<Object>(reviews, HttpStatus.OK);
    }
	
	//location: http://localhost:8080/review/get_reviews_by_productId/1001
	@GetMapping(path="/get_rating_by_productId/{productId}", produces = "application/json")
	public ResponseEntity<Object> get_rating_by_productId(@PathVariable int productId)
	{
		HttpRating rating = productionService.getRatingByProductId(productId);

		return new ResponseEntity<Object>(rating, HttpStatus.OK);
    }
}



