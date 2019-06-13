package com.hhmarket.restfulapi.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hhmarket.restfulapi.model.MainCategory;

public interface MainCategoryRepository extends JpaRepository<MainCategory, Integer> {

}
