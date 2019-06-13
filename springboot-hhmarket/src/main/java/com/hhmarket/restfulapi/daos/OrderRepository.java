package com.hhmarket.restfulapi.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hhmarket.restfulapi.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
