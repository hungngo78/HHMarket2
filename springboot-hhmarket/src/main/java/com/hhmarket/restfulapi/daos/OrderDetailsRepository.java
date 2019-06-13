package com.hhmarket.restfulapi.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hhmarket.restfulapi.model.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

}
