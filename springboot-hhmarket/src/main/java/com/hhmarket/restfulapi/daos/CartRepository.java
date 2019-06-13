package com.hhmarket.restfulapi.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hhmarket.restfulapi.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	@Query("select c from Cart c where c.user.userId = :userId")
	Optional<Cart> findByUserId(@Param("userId") int userId);
}
