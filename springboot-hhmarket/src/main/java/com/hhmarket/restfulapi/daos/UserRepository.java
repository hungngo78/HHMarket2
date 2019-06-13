package com.hhmarket.restfulapi.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hhmarket.restfulapi.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	//Optional<User> findByUserId(int userId);
	Optional<User> findByUserName(String userName);
	
	@Query("select u from User u where u.userName = :userName and u.password = :password ")
	Optional<User> findByUserNameAndPassword(@Param("userName") String u, 
								@Param("password") String p);
	
	@Query("select u from User u where u.email = ?1")
	Optional<User> findByEmail(String email);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.password = :password WHERE u.userId = :userId")
    void updatePassword(@Param("userId") int userId, @Param("password") String password);
}
