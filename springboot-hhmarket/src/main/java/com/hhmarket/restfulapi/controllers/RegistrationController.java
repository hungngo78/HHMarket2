package com.hhmarket.restfulapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhmarket.restfulapi.model.User;
import com.hhmarket.restfulapi.pojo.HttpUser;
import com.hhmarket.restfulapi.services.AccountService;

import java.util.Optional;

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

@RestController
@RequestMapping(path = "/account")
public class RegistrationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
    private AccountService accountService;
	
	@RequestMapping("/index")
    public String index() {
        return "Greetings from Spring Boot!";
    }
	
	@PostMapping(path= "/add", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> add(@RequestBody HttpUser httpUser) {
		try {
			if (httpUser != null) {
				Optional<User> user = accountService.findById(httpUser.UserId);
				if (!user.isPresent()) {
					//Send saved object back in response
					user.get().setUserName(httpUser.UserName);
					user.get().setPassword(httpUser.Password);
					user.get().setFirstName(httpUser.FirstName);
					user.get().setLastName(httpUser.LastName);
					user.get().setEmail(httpUser.Email);
					user.get().setAddress(httpUser.Address);
					user.get().setCity(httpUser.City);
					user.get().setState(httpUser.State);
					user.get().setZipCode(httpUser.ZipCode);
					
					return ResponseEntity.ok(accountService.save(user.get()));
		        }
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	//location: http://localhost:8080/account/get/123
	@GetMapping(path="/get/{userId}", produces = "application/json")
	public ResponseEntity<Object> get(@PathVariable int userId)
	{
		try {
			Optional<User> user = accountService.findById(userId);
			if (user != null) {
				ObjectMapper objectMapper = new ObjectMapper();
				String carJson = objectMapper.writeValueAsString(user.get());
				
				return new ResponseEntity<Object>(carJson, HttpStatus.OK);
			}
		} catch (JsonProcessingException ex) {
			LOGGER.error(ex.getMessage());
		}
		
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }
	
	@PutMapping(path= "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> update(@RequestBody HttpUser httpUser) {
		if (httpUser != null) {
			Optional<User> user = accountService.findById(httpUser.UserId);
			if (user.isPresent()) {
				//Send saved object back in response
				user.get().setUserName(httpUser.UserName);
				user.get().setPassword(httpUser.Password);
				user.get().setFirstName(httpUser.FirstName);
				user.get().setLastName(httpUser.LastName);
				user.get().setEmail(httpUser.Email);
				user.get().setAddress(httpUser.Address);
				user.get().setCity(httpUser.City);
				user.get().setState(httpUser.State);
				user.get().setZipCode(httpUser.ZipCode);
				
				return ResponseEntity.ok(accountService.save(user.get()));
			}
		} 
		
		return ResponseEntity.badRequest().build();
	}
}
