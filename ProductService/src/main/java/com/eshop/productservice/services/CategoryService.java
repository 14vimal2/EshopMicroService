package com.eshop.productservice.services;

import com.eshop.productservice.models.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category createCategoryByNameIfNotExist(String categoryName);


}
