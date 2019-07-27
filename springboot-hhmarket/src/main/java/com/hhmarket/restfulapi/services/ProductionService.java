package com.hhmarket.restfulapi.services;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhmarket.restfulapi.daos.CategoryRepository;
import com.hhmarket.restfulapi.daos.ProductDetailsRepository;
import com.hhmarket.restfulapi.daos.ProductRepository;
import com.hhmarket.restfulapi.daos.ReviewRepository;
import com.hhmarket.restfulapi.daos.UserRepository;
import com.hhmarket.restfulapi.model.Category;
import com.hhmarket.restfulapi.model.Product;
import com.hhmarket.restfulapi.model.ProductDetails;
import com.hhmarket.restfulapi.model.Review;
import com.hhmarket.restfulapi.model.User;
import com.hhmarket.restfulapi.pojo.HttpReview;
import com.hhmarket.restfulapi.pojo.HttpProductPrices;
import com.hhmarket.restfulapi.pojo.HttpProduction;
import com.hhmarket.restfulapi.pojo.HttpRating;
import com.hhmarket.restfulapi.pojo.HttpSearchProduction;

@Service
public class ProductionService {
	private final UserRepository userRepository;
	
	//private final MainCategoryRepository mainCategoryRepository;
	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	private final ProductDetailsRepository productDetailsRepository;
	
	private final ReviewRepository reviewRepository;
	
	
	@Autowired
	public ProductionService(CategoryRepository _categoryRepository, ProductRepository _productRepository,
							ProductDetailsRepository _productDetailsRepository, ReviewRepository _reviewRepository,
							UserRepository _userRepository) {
	    this.categoryRepository = _categoryRepository;
	    this.productRepository = _productRepository;
	    this.productDetailsRepository = _productDetailsRepository;
	    this.reviewRepository = _reviewRepository;
	    this.userRepository = _userRepository;
	}
	
	/* Category */
	@Transactional 
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	@Transactional 
	public Category findCategoryById(int id) {
		return categoryRepository.findById(id);
	}
	
	@Transactional 
	public Category addCategory(Category cate) {
		this.categoryRepository.save(cate);
		return cate;
	}
	
	
	/*-------------------------------- Product --------------------------------*/
	@Transactional 
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	@Transactional 
	public Optional<Product> findProductById(int id) {
		return productRepository.findById(id);
	}
	
	@Transactional 
	public HttpProduction findProductionById(int id) {
		// get product of given productId
		Optional<Product> p = productRepository.findById(id);
		
		if (p.isPresent()) {
			// get fundamental information, such as name, description
			HttpProduction production = new HttpProduction();
			production.setProductId(p.get().getProductId());
			production.setProductionName(p.get().getName());
			production.setDescription(p.get().getDescription());
			
			// get min and max prices of this product
			HttpProductPrices productPrices = productDetailsRepository.getMinMaxPrice(p.get().getProductId());
			float minPrice = productPrices.getMinPrice();
			production.setMinPrice(minPrice);
			production.setMaxPrice(productPrices.getMaxPrice());
			
			// get default color and picture of this product
			ProductDetails productDetails = productDetailsRepository.findByProductIdAndPrice(p.get().getProductId(), minPrice);
			production.setColor(productDetails.getColor());
			production.setPicture(productDetails.getPicture());
			
			return production;
		}
		
		return null;
	}
	
	@Transactional 
	public List<HttpProduction> findProductsByCategoryId(int id) {
		List<HttpProduction> productions = new ArrayList<HttpProduction>();
		
		// get all products of given category
		List<Product> products = productRepository.findByCategoryId(id);
		
		// for each product, aggregate data to response to client
		for (Product p: products) {
			// get fundamental information, such as name, description
			HttpProduction production = new HttpProduction();
			production.setProductId(p.getProductId());
			production.setProductionName(p.getName());
			production.setDescription(p.getDescription());
			
			// get min and max prices of this product
			HttpProductPrices productPrices = productDetailsRepository.getMinMaxPrice(p.getProductId());
			float minPrice = 0;
			float maxPrice = 0;
			if (productPrices != null) {
				minPrice = productPrices.getMinPrice();
				maxPrice = productPrices.getMaxPrice();				
			} 
			production.setMinPrice(minPrice);
			production.setMaxPrice(maxPrice);
			
			// get default color and picture of this product
			ProductDetails productDetails = productDetailsRepository.findByProductIdAndPrice(p.getProductId(), minPrice);
			String color = "";
			String picture = "";
			if (productDetails != null) {
				color = productDetails.getColor();
				picture = productDetails.getPicture();
			}
			production.setColor(color);
			production.setPicture(picture);
			
			// add review info 
			List<Review> reviews = p.getReviewList();
			if (reviews != null && reviews.size() > 0) {
				HttpRating rating = aggregateRatingData(reviews);
				
				production.setOverrallRating(rating.overrallRating);
				production.setReviewNumber(reviews.size());
			}
			
			productions.add(production);
		}
		
		return productions;
	}
	
	@Transactional
	public HttpProduction findProductionByProductDetailsId(int productDetailsId) {
		HttpProduction production = new HttpProduction();
		
		// get all products of given category
		Optional<ProductDetails> productDetails = productDetailsRepository.findById(productDetailsId);
		if (productDetails.isPresent()) {
			Product product = productDetails.get().getProduct();
			if (product != null) {
				production.setProductId(product.getProductId());
				production.setProductionName(product.getName());
				production.setDescription(product.getDescription());
				
				// get min and max prices of this product
				HttpProductPrices productPrices = productDetailsRepository.getMinMaxPrice(product.getProductId());
				production.setMinPrice(productPrices.getMinPrice());
				production.setMaxPrice(productPrices.getMaxPrice());
				
				// get default color and picture of this product
				production.setColor(productDetails.get().getColor());
				production.setPicture(productDetails.get().getPicture());
				
				return production;
			}
		}
		
		return null;
	}
	
	@Transactional 
	public List<HttpSearchProduction> findProductsByCategoryIdAndSearchCriteria(int categoryId, String search) {
		List<HttpSearchProduction> searchProductions = new ArrayList<HttpSearchProduction>();
		String[] searchArr;
		
		searchArr = search.split(" ");
		Set<String> criteria = new HashSet<>(Arrays.asList(searchArr));
				
		List<Product> products = this.productRepository.findProductByCriteria(categoryId, criteria);
		for  (Product p: products) {
			HttpSearchProduction searchProduction = new HttpSearchProduction();
			searchProduction.setProductId(p.getProductId());
			searchProduction.setProductionName(p.getName());
			searchProduction.setDescription(p.getDescription());
			
			/*List<ProductDetails> productDetails = p.getProductDetailsList();
			if (productDetails != null && productDetails.size() > 0) {
				ProductDetails details = productDetails.get(0);
				searchProduction.setPrice(details.getPrice());
				searchProduction.setColor(details.getColor());
				searchProduction.setPicture(details.getPicture());
			}*/
			
			// get min and max prices of this product
			HttpProductPrices productPrices = productDetailsRepository.getMinMaxPrice(p.getProductId());
			float minPrice = 0;
			float maxPrice = 0;
			if (productPrices != null) {
				minPrice = productPrices.getMinPrice();
				maxPrice = productPrices.getMaxPrice();				
			} 
			searchProduction.setMinPrice(minPrice);
			searchProduction.setMaxPrice(maxPrice);
			
			// get default color and picture of this product
			ProductDetails productDetails = productDetailsRepository.findByProductIdAndPrice(p.getProductId(), minPrice);
			String color = "";
			String picture = "";
			if (productDetails != null) {
				color = productDetails.getColor();
				picture = productDetails.getPicture();
			}
			searchProduction.setColor(color);
			searchProduction.setPicture(picture);
			
			
			searchProduction.setCategoryName(p.getCategory().getName());
			searchProduction.setCategoryDescription(p.getCategory().getDescription());
			
			List<Review> reviews = p.getReviewList();
			if (reviews != null && reviews.size() > 0) {
				HttpRating rating = aggregateRatingData(reviews);
				
				searchProduction.setOverrallRating(rating.overrallRating);
				searchProduction.setReviewNumber(reviews.size());
			}
			
			searchProductions.add(searchProduction);
		}

		return searchProductions;
	}
	
	@Transactional 
	public Product addProduct(Product cate) {
		this.productRepository.save(cate);
		return cate;
	}
	
	/*-------------------------------- ProductDetails --------------------------------*/
	@Transactional 
	public List<ProductDetails> getAllProductDetails() {
		return productDetailsRepository.findAll();
	}
	
	@Transactional 
	public Optional<ProductDetails> findProductDetailsById(int id) {
		return productDetailsRepository.findById(id);
	}
	
	@Transactional 
	public List<ProductDetails> findProductDetailsByProductId(int id) {
		return productDetailsRepository.findByProductId(id);
	}
	
	@Transactional 
	public ProductDetails addProductDetails(ProductDetails proDetails) {
		this.productDetailsRepository.save(proDetails);
		return proDetails;
	}
	
	/*-------------------------------- Review --------------------------------*/
	@Transactional
	public Review addReview(HttpReview item) {
		// get user by userId
		Optional<User> user = this.userRepository.findById(item.UserId);
		
		// get product mentioned in the review item
		Optional<Product> product = this.productRepository.findById(item.ProductId);
		
		// new Review entity
		Review review = new Review();
		review.setTitle(item.Title);
		review.setContent(item.Content);
		review.setReviewDate(LocalDate.now());
		review.setOverallRating(item.OverallRating);
		
		if (user.isPresent() && product.isPresent()) {
			review.setUser(user.get());
			review.setProduct(product.get());

			return this.reviewRepository.save(review);
		}
		
		return null;
	}
	
	@Transactional
	public List<HttpReview> getReviewByProductId(int productId) {
		List<HttpReview> httpReviews = new ArrayList<HttpReview>();
		List<Review> reviews = this.reviewRepository.findByProductIdId(productId);
		if (reviews != null && reviews.size() > 0) {
			for (Review r: reviews) {
				HttpReview httpReview = new HttpReview();
				httpReview.ReviewId = r.getReviewId();
				httpReview.ProductId = r.getProduct().getProductId();
				httpReview.UserName = r.getUser().getUserName();
				httpReview.Title = r.getTitle();
				httpReview.Content = r.getContent();
				httpReview.OverallRating = r.getOverallRating();
				httpReview.ReviewDate = r.getReviewDate();
				
				httpReviews.add(httpReview);
			}
		}
		return httpReviews;
	}
	
	@Transactional
	public HttpRating getRatingByProductId(int productId) {
		HttpRating rating = new HttpRating();
		
		// get all reviews about this product
		List<Review> reviews = this.reviewRepository.findByProductIdId(productId);
		
		/* aggregate review data */
		rating = aggregateRatingData(reviews);
			
		return rating;
    } 
	
	private HttpRating aggregateRatingData(List<Review> reviews) {
		HttpRating rating = new HttpRating();
		
		// set number of reviews 
		//rating.setReviewNumber(reviews.size());
		
		// calculate overall rating (decimal)
		float overrall = 0;
		for (Review r: reviews) {
			overrall += r.getOverallRating();
		}		
		DecimalFormat df = new DecimalFormat("#.##"); 
		String formatted = df.format(overrall/reviews.size()); 
		rating.overrallRating = formatted;
		
		// calculate numbers of "1 star", "2 stars", "3 stars", "4 stars" and "5 stars" review
		Map<Integer, Long> counting = reviews.stream().collect(
                Collectors.groupingBy(Review::getOverallRating, Collectors.counting()));
		for (Map.Entry<Integer, Long> entry : counting.entrySet()) {
			if (entry.getKey() == 1) {
				rating.oneStarReviewNumber = Math.toIntExact(entry.getValue());
				rating.oneStarReviewPercent = df.format(rating.oneStarReviewNumber*100/reviews.size());
			} else if (entry.getKey() == 2) {
				rating.twoStarReviewNumber = Math.toIntExact(entry.getValue());
				rating.twoStarReviewPercent = df.format(rating.twoStarReviewNumber*100/reviews.size());
			} else if (entry.getKey() == 3) {
				rating.threeStarReviewNumber = Math.toIntExact(entry.getValue());
				rating.threeStarReviewPercent = df.format(rating.threeStarReviewNumber*100/reviews.size());
			} else if (entry.getKey() == 4) {
				rating.fourStarReviewNumber = Math.toIntExact(entry.getValue());
				rating.fourStarReviewPercent = df.format(rating.fourStarReviewNumber*100/reviews.size());
			} else {
				rating.fiveStarReviewNumber = Math.toIntExact(entry.getValue());
				rating.fiveStarReviewPercent = df.format(rating.fiveStarReviewNumber*100/reviews.size());
			}
		}
		
		return rating;
	}
}



