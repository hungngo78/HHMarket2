package com.hhmarket.restfulapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhmarket.restfulapi.daos.UserRepository;
import com.hhmarket.restfulapi.model.User;

@Service
public class AccountService {
	private final UserRepository userRepository;
	
	@Autowired
	public AccountService(UserRepository userRepository) {
	    this.userRepository = userRepository;
	}
	
	@Transactional 
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Transactional 
	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}
	
	@Transactional 
	public Optional<User> findByUsernameAndPassword(String u, String p) {
		return userRepository.findByUserNameAndPassword(u, p);
	}
	
	@Transactional
	public User save(User acc) {
		return userRepository.save(acc);
	}
}
