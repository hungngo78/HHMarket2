package com.hhmarket.restfulapi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hhmarket.restfulapi.model.User;
import com.hhmarket.restfulapi.services.AccountService;

@RestController
@RequestMapping(path = "/account")
public class LoginController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
    private AccountService accountService;
	
	@GetMapping(path="/hello")
	public String hello() {
		return "Hello from HHMarket Rest API";
	}
	
	//https://www.journaldev.com/3358/spring-requestmapping-requestparam-pathvariable-example
	
	//@PathVariable
	  //@GetMapping(path="/{username}/{password}", produces = "application/json")
	  //public ResponseEntity<Object> authorize(@PathVariable String username, @PathVariable String password)
	  //location: http://localhost:8080/spring-rest/login/abc/something
	
	//@RequestParam
	  //https://www.baeldung.com/spring-requestmapping
	  // location: http://localhost:8080/login?username=hung&password=123
	@GetMapping(path="/login", produces = "application/json")
	public ResponseEntity<Object> authorize(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
		LOGGER.debug("authorize()");
		
		Optional<User> user = accountService.findByUsernameAndPassword(username, password);
		if (user != null) {
			return new ResponseEntity<Object>(user.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }
}
