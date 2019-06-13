package com.hhmarket.restfulapi.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hhmarket.restfulapi.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
	@Query("select r from Review r where r.product.productId = :productId")
	List<Review> findByProductIdId(@Param("productId") int productId);
}
