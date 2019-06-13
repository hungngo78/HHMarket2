package com.hhmarket.restfulapi.daos;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hhmarket.restfulapi.model.CartDetails;

public interface CartDetailsRepository extends JpaRepository<CartDetails, Integer> {

	@Query("select c from CartDetails c where c.cart.cartId = :cartId")
	List<CartDetails> findByCartId(@Param("cartId") int cartId);
	
	@Query("select c from CartDetails c where c.cart.cartId = :cartId and c.productDetails.productDetailsId = :productDetailsId")
	Optional<CartDetails> findByCartIdAndProductDetailsId(@Param("cartId") int cartId, 
														  @Param("productDetailsId") int productDetailsId);
	
	@Modifying
    @Transactional
	@Query("delete from CartDetails c where c.cartDetailsId = ?1")
	void deleteByCartDetailsId(int cartDetailsId);
}
