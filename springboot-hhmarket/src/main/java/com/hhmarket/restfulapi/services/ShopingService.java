package com.hhmarket.restfulapi.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhmarket.restfulapi.daos.CartDetailsRepository;
import com.hhmarket.restfulapi.daos.CartRepository;
import com.hhmarket.restfulapi.daos.OrderDetailsRepository;
import com.hhmarket.restfulapi.daos.OrderRepository;
import com.hhmarket.restfulapi.daos.ProductDetailsRepository;
import com.hhmarket.restfulapi.daos.UserRepository;
import com.hhmarket.restfulapi.model.Cart;
import com.hhmarket.restfulapi.model.CartDetails;
import com.hhmarket.restfulapi.model.Order;
import com.hhmarket.restfulapi.model.OrderDetails;
import com.hhmarket.restfulapi.model.ProductDetails;
import com.hhmarket.restfulapi.model.User;
import com.hhmarket.restfulapi.pojo.HttpCartItem;
import com.hhmarket.restfulapi.pojo.HttpCart;
import com.hhmarket.restfulapi.pojo.HttpShopingItem;

@Service
public class ShopingService {
	private final UserRepository userRepository;
	
	//private final CategoryRepository categoryRepository;
	//private final ProductRepository productRepository;
	private final ProductDetailsRepository productDetailsRepository;
	
	private final CartRepository cartRepository;
	private final CartDetailsRepository cartDetailsRepository;
	private final OrderRepository orderRepository;
	//private final OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	public ShopingService(CartRepository _cartRepository, CartDetailsRepository _cartDetailsRepository,
			OrderRepository _orderRepository, OrderDetailsRepository _orderDetailsRepository,
			UserRepository _userRepository, ProductDetailsRepository _productDetailsRepository) {
		this.userRepository = _userRepository;
		
		this.cartRepository = _cartRepository;
		this.cartDetailsRepository = _cartDetailsRepository;
		this.orderRepository = _orderRepository;
		//this.orderDetailsRepository = _orderDetailsRepository;
		
		this.productDetailsRepository = _productDetailsRepository;
	}
	
	/* ----------------------------------------- Cart, Cart Details ----------------------------------------- */
	@Transactional 
	public int getNumberOfCartItemsByUserId(int userId) {
		Optional<Cart> cart = this.cartRepository.findByUserId(userId);
		if (cart.isPresent()) {
			List<CartDetails> cartDetailsList = this.cartDetailsRepository.findByCartId(cart.get().getCartId());
			
			// get size of shopping cart which is the number of items in shopping cart
			return cartDetailsList.size();
		}
		
		return 0;
	}
	
	@Transactional 
	public Cart addCart(HttpCart httpCart) {
		Cart cart = new Cart();
		Optional<User> user = this.userRepository.findById(httpCart.UserId);
		cart.setUser(user.get());
		cart.setDateOpen(LocalDate.now());
		
		return this.cartRepository.save(cart);
	}
	
	@Transactional 
	public Optional<Cart> getCartByUserId(int userId) {
		return this.cartRepository.findByUserId(userId);
	}
	
	@Transactional 
	public List<HttpCartItem> getCartItemsByUserId(int userId) {
		List<HttpCartItem> cartItemDetailsList = new ArrayList<HttpCartItem>(); 
		
		// get shopping cart of this user
		Optional<Cart> cart = this.cartRepository.findByUserId(userId);
		if (cart.isPresent()) {
			int cartId = cart.get().getCartId();
			
			// get all items in cart
			List<CartDetails> cartDetailsList = this.cartDetailsRepository.findByCartId(cartId);
			if (cartDetailsList != null && cartDetailsList.size() > 0) {
				for (CartDetails details : cartDetailsList) {
					HttpCartItem item = new HttpCartItem();
					
					item.setCartId(cartId);
					item.setCartDetailsId(details.getCartDetailsId());
					item.setAmount(details.getAmount());
					item.setExtendedPrice(details.getExtendedPrice());
					item.setType(details.getType());
					item.setProductId(details.getProductDetails().getProduct().getProductId());
					item.setProductName(details.getProductDetails().getProduct().getName());
					item.setProductDetailsId(details.getProductDetails().getProductDetailsId());
					item.setColor(details.getProductDetails().getColor());
					item.setSize(details.getProductDetails().getSize());
					item.setPicture(details.getProductDetails().getPicture());
					item.setPrice(details.getProductDetails().getPrice());
					item.setotalAmountProduction(details.getProductDetails().getAmount());
					
					cartItemDetailsList.add(item);
				}
			}
		}
		
		return cartItemDetailsList;
	}
	
	@Transactional 
	public HttpCartItem getCartItem(int cartId, int productDetailsId) {
		HttpCartItem item = new HttpCartItem();
		Optional<CartDetails> cartDetails = this.cartDetailsRepository.findByCartIdAndProductDetailsId(cartId, productDetailsId);
		
		item.setCartId(cartId);
		item.setCartDetailsId(cartDetails.get().getCartDetailsId());
		item.setAmount(cartDetails.get().getAmount());
		item.setExtendedPrice(cartDetails.get().getExtendedPrice());
		item.setType(cartDetails.get().getType());
		item.setProductId(cartDetails.get().getProductDetails().getProduct().getProductId());
		item.setProductName(cartDetails.get().getProductDetails().getProduct().getName());
		item.setProductDetailsId(cartDetails.get().getProductDetails().getProductDetailsId());
		item.setColor(cartDetails.get().getProductDetails().getColor());
		item.setSize(cartDetails.get().getProductDetails().getSize());
		item.setPicture(cartDetails.get().getProductDetails().getPicture());
		item.setPrice(cartDetails.get().getProductDetails().getPrice());
		item.setotalAmountProduction(cartDetails.get().getProductDetails().getAmount());
		
		return item;
	}
	
	@Transactional 
	public CartDetails addToCart(HttpShopingItem shopingItem) {
		CartDetails cartDetails = null;
		int userId = shopingItem.UserId;
		
		// get shopping cart of this user
		Optional<Cart> cart = this.cartRepository.findByUserId(userId);
		
		// There is already a cart for this user
		if (cart.isPresent()) {
			// add new item into CartDetails table
			cartDetails = new CartDetails();
			cartDetails.setAmount(shopingItem.Amount);
			cartDetails.setExtendedPrice(shopingItem.ExtendedPrice);
			cartDetails.setType(0);
			cartDetails.setCart(cart.get());
			
			Optional<ProductDetails> productDetails = this.productDetailsRepository.findById(shopingItem.ProductDetailsId);
			if (productDetails.isPresent()) {
				cartDetails.setProductDetails(productDetails.get());
				
				this.cartDetailsRepository.save(cartDetails);
			}
		} else {  // there is no shopping cart of this user -> create new 
			// specify who ordered this shopping item
			Optional<User> user = this.userRepository.findById(userId);
			
			if (user.isPresent()) {
				// add new Cart 
				Cart newCart = new Cart();
				newCart.setDateOpen(LocalDate.now());
				newCart.setUser(user.get());
				List<CartDetails> cartDetailsList = new ArrayList<CartDetails>();
				newCart.setCartDetailsList(cartDetailsList);
				
				// add new item into CartDetails table
				cartDetails = new CartDetails();
				cartDetails.setAmount(shopingItem.Amount);
				cartDetails.setExtendedPrice(shopingItem.ExtendedPrice);
				cartDetails.setType(0);
				cartDetails.setCart(newCart);
				
				Optional<ProductDetails> productDetails = this.productDetailsRepository.findById(shopingItem.ProductDetailsId);
				if (productDetails.isPresent()) {
					cartDetails.setProductDetails(productDetails.get());
					//this.cartDetailsRepository.save(cartDetails);  // no need to save cart Detail if saved cart
					
					cartDetailsList.add(cartDetails);
					this.cartRepository.save(newCart);
				}
			}
		}
		
		return cartDetails;
	}
	
	@Transactional 
	public CartDetails updateQuantity(HttpShopingItem shopingItem) {
		// if amount is equal to 0, remove item from shopping cart
		if (shopingItem.Amount == 0) {
			this.cartDetailsRepository.deleteByCartDetailsId(shopingItem.CartDetailsId);
		} else { // update quantity of given item in shopping cart 
			Optional<CartDetails> currentCartDetails = this.cartDetailsRepository.findById(shopingItem.CartDetailsId);
			if (currentCartDetails.isPresent()) {
				currentCartDetails.get().setAmount(shopingItem.Amount);
				
				return this.cartDetailsRepository.save(currentCartDetails.get());
			}
		}
		
		return null;
	}
	
	/* ----------------------------------------- Order, OrderDetails ----------------------------------------- */
	@Transactional 
	public Order makeOrder(int userId) {
		Order order = new Order();
		order.setOrderDate(LocalDate.now());
		order.setStatus(0);
		order.setDeliveryDate(LocalDate.now());
		order.setNote("Note of user");
		
		// get user by userId
		Optional<User> user = this.userRepository.findById(userId);
		if (user.isPresent()) {
			order.setUser(user.get());
		
			List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
			order.setOrderDetailsList(orderDetailsList);
			
			Optional<Cart> cart = this.cartRepository.findByUserId(userId);
			if (cart.isPresent()) {
				List<ProductDetails> productDetailsList = new ArrayList<ProductDetails>();
				
				// get all items in cart added by this user
				List<CartDetails> cartDetailsList = this.cartDetailsRepository.findByCartId(cart.get().getCartId());
				
				// move all items in cart to order table
				for (CartDetails c: cartDetailsList) {
					OrderDetails orderDetails = new OrderDetails();
					orderDetails.setOrder(order);
					
					Optional<ProductDetails> productDetails = this.productDetailsRepository.findById(c.getProductDetails().getProductDetailsId());
					if (productDetails.isPresent()) {
						orderDetails.setProductDetails(productDetails.get());
						orderDetails.setAmount(c.getAmount());
						orderDetails.setExtendedPrice(c.getExtendedPrice());

						orderDetailsList.add(orderDetails);
						
						// update amount of this productDetail in store
						int currentAmount = productDetails.get().getAmount();
						if (currentAmount > c.getAmount()) {
							productDetails.get().setAmount(currentAmount - c.getAmount());
							productDetailsList.add(productDetails.get());
						} else 
							return null;
					}
				}
				
				// save order -> order_details 
				order = this.orderRepository.save(order);	
				
				// update amount of this productDetail in store
				for (ProductDetails productDetails: productDetailsList) {
					this.productDetailsRepository.save(productDetails);
				}
				
				// remove items in shopping cart
				for (CartDetails c: cartDetailsList) {
					this.cartDetailsRepository.deleteByCartDetailsId(c.getCartDetailsId());
				}
				
				return order;
			}
		}
		
		return null;
	}
}





