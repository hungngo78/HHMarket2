package com.hhmarket.mobile.api.repository;

import java.util.List;
import java.util.function.Function;

import com.hhmarket.mobile.model.Category;

import retrofit2.Callback;

public interface CategoryRepository {

    public void getCategories(Callback<List<Category>> callback);

}
