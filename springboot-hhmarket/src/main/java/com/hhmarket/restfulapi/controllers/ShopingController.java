package com.hhmarket.restfulapi.controllers;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhmarket.restfulapi.model.Cart;
import com.hhmarket.restfulapi.model.CartDetails;
import com.hhmarket.restfulapi.model.Order;
import com.hhmarket.restfulapi.pojo.HttpCartItem;
import com.hhmarket.restfulapi.pojo.HttpCart;
import com.hhmarket.restfulapi.pojo.HttpShopingItem;
import com.hhmarket.restfulapi.services.ShopingService;

@RestController
@RequestMapping(path = "/shopping")
public class ShopingController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
    private ShopingService shopingService;
	
	
	@GetMapping(path="/get_cart/{userId}", produces = "application/json")
	public ResponseEntity<Object> get_cart(@PathVariable int userId)
	{
		Optional<Cart> cart = shopingService.getCartByUserId(userId);
		return new ResponseEntity<Object>(cart.get(), HttpStatus.OK);
	}
	
	@PostMapping(path= "/add_cart", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> add_cart(@RequestBody HttpCart httpcart) {
		Cart cart = shopingService.addCart(httpcart);
		return new ResponseEntity<Object>(cart, HttpStatus.OK);
	}
	
	//location: http://localhost:8080/shopping/get_cart_item_numer/123
	@GetMapping(path="/get_cart_item_numer/{userId}", produces = "application/json")
	public ResponseEntity<Object> get_cart_item_numer(@PathVariable int userId)
	{
		int cartItemNo = shopingService.getNumberOfCartItemsByUserId(userId);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("number_of_cart_items", cartItemNo);
		
		return new ResponseEntity<Object>(jsonObj.toString(), HttpStatus.OK);
    }
	
	@GetMapping(path="/get_cart_items/{userId}", produces = "application/json")
	public ResponseEntity<Object> get_cart_items(@PathVariable int userId)
	{
		List<HttpCartItem> cartItemList = shopingService.getCartItemsByUserId(userId);
		
		return new ResponseEntity<Object>(cartItemList, HttpStatus.OK);
	}
	
	@GetMapping(path="/get_cart_item/{cartId}/{productDetailsId}", produces = "application/json")
	public ResponseEntity<Object> get_cart_item(@PathVariable int cartId, @PathVariable int productDetailsId)
	{
		HttpCartItem cartItem = shopingService.getCartItem(cartId, productDetailsId);
		
		return new ResponseEntity<Object>(cartItem, HttpStatus.OK);
	}
	
	@PostMapping(path= "/add_shoping_item", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> add_shoping_item(@RequestBody HttpShopingItem shopingItem) {
		try {
			if (shopingItem != null) {
				CartDetails cartDetails = shopingService.addToCart(shopingItem);
				return ResponseEntity.ok(cartDetails);
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping(path= "/update_quantity", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> update_quantity(@RequestBody HttpShopingItem shopingItem) {
		try {
			if (shopingItem != null) {
				CartDetails cartDetails = shopingService.updateQuantity(shopingItem);
				return ResponseEntity.ok(cartDetails);
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping(path= "/order/{userId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> order(@PathVariable int userId) {
		try {
			Order order = shopingService.makeOrder(userId);
			return ResponseEntity.ok(order);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		
		return ResponseEntity.badRequest().build();
	}
}





