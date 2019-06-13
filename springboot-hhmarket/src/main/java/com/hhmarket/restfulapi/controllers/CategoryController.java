package com.hhmarket.restfulapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhmarket.restfulapi.model.Category;
import com.hhmarket.restfulapi.model.Product;
import com.hhmarket.restfulapi.model.ProductDetails;
import com.hhmarket.restfulapi.pojo.HttpProductionDetail;
import com.hhmarket.restfulapi.pojo.HttpProduction;
import com.hhmarket.restfulapi.pojo.HttpSearchProduction;
import com.hhmarket.restfulapi.services.ProductionService;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
    private ProductionService productionService;
	
	//location: http://localhost:8080/category/get_all_categories
	@GetMapping(path="/get_all_categories", produces = "application/json")
	public ResponseEntity<Object> getAllCategories()
	{
		try {
			List<Category> categories = productionService.getAllCategories();
			if (categories != null) {
				ObjectMapper objectMapper = new ObjectMapper();
				String categoriesJson = objectMapper.writeValueAsString(categories);
				
				return new ResponseEntity<Object>(categoriesJson, HttpStatus.OK);
			}
		} catch (JsonProcessingException ex) {
			LOGGER.error(ex.getMessage());
		}
		
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }
	
	//location: http://localhost:8080/category/get_products_by_category/{categoryId}
	@GetMapping(path="/get_products_by_category/{categoryId}", produces = "application/json")
	public ResponseEntity<Object> getProductsByCategory(@PathVariable int categoryId)
	{
		try {
			List<HttpProduction> products = productionService.findProductsByCategoryId(categoryId);
			if (products != null && products.size() > 0) {
				ObjectMapper objectMapper = new ObjectMapper();
				String categoriesJson = objectMapper.writeValueAsString(products);
				
				return new ResponseEntity<Object>(categoriesJson, HttpStatus.OK);
			}
		} catch (JsonProcessingException ex) {
			LOGGER.error(ex.getMessage());
		}
		
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }
	
	@GetMapping(path="/get_production_by_productId/{productId}", produces = "application/json")
	public ResponseEntity<Object> getProductionByProductId(@PathVariable int productId)
	{
		HttpProduction product = productionService.findProductionById(productId);
		return new ResponseEntity<Object>(product, HttpStatus.OK);
    }
	
	@GetMapping(path="/get_production_by_product_details_id/{productDetailsId}", produces = "application/json")
	public ResponseEntity<Object> getProductionByProductDetailsId(@PathVariable int productDetailsId)
	{
		HttpProduction product = productionService.findProductionByProductDetailsId(productDetailsId);
		return new ResponseEntity<Object>(product, HttpStatus.OK);
    }
	
	@GetMapping(path="/get_product_title_by_id/{productId}", produces = "application/json")
	public ResponseEntity<Object> getProductByProductId(@PathVariable int productId)
	{
		try {
			Optional<Product> product = productionService.findProductById(productId);
			if (product.isPresent()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("productId", productId);
				jsonObj.put("title", product.get().getCategory().getName() + " > " + product.get().getName());
				
				return new ResponseEntity<Object>(jsonObj.toString(), HttpStatus.OK);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}
	
	//location: http://localhost:8080/category/get_product_details_by_productId/{productId}
	@GetMapping(path="/get_product_details_by_productId/{productId}", produces = "application/json")
	public ResponseEntity<Object> getProductDetailsByProductId(@PathVariable int productId) {
		List<HttpProductionDetail> productionDetailsList = new ArrayList<HttpProductionDetail>();
		
		try {
			List<ProductDetails> productDetailsList = productionService.findProductDetailsByProductId(productId);
			if (productDetailsList != null && productDetailsList.size() > 0) {
				for (ProductDetails p: productDetailsList) {
					HttpProductionDetail productionDetails = new HttpProductionDetail();
					productionDetails.productDetailsId = p.getProductDetailsId();
					productionDetails.color = p.getColor();
					productionDetails.picture = p.getPicture();
					productionDetails.price = p.getPrice();
					productionDetails.size = p.getSize();
					productionDetails.amount = p.getAmount();
					productionDetails.productId = p.getProduct().getProductId();
					productionDetails.productionName = p.getProduct().getName();
					productionDetails.description = p.getProduct().getDescription();
					
					productionDetailsList.add(productionDetails);
				}
				
				//ObjectMapper objectMapper = new ObjectMapper();
				//String productDetailsListJson = objectMapper.writeValueAsString(productDetailsList);
				
				return new ResponseEntity<Object>(productionDetailsList, HttpStatus.OK);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path="/get_product_details_by_product_details_id/{productDetailsId}", produces = "application/json")
	public ResponseEntity<Object> getProductDetailsByProductDetailsId(@PathVariable int productDetailsId) {
		HttpProductionDetail productionDetails = new HttpProductionDetail();
		
		try {
			Optional<ProductDetails> p = productionService.findProductDetailsById(productDetailsId);
			if (p.isPresent()) {
				productionDetails.productDetailsId = p.get().getProductDetailsId();
				productionDetails.color = p.get().getColor();
				productionDetails.picture = p.get().getPicture();
				productionDetails.price = p.get().getPrice();
				productionDetails.size = p.get().getSize();
				productionDetails.amount = p.get().getAmount();
				productionDetails.productId = p.get().getProduct().getProductId();
				productionDetails.productionName = p.get().getProduct().getName();
				productionDetails.description = p.get().getProduct().getDescription();
					
				return new ResponseEntity<Object>(productionDetails, HttpStatus.OK);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path="/searching", produces = "application/json")
	public ResponseEntity<Object> search(@RequestParam(value = "categoryId", required = false) String strId, @RequestParam(value = "criteria") String search) {
		try {
			int categoryId = -1;
			if (strId != null) {
				categoryId = Integer.parseInt(strId);
			}
			
			List<HttpSearchProduction> searchProductions = this.productionService.findProductsByCategoryIdAndSearchCriteria(categoryId, search);
				
			return new ResponseEntity<Object>(searchProductions, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}
}



