package com.eshop.productservice.services;


import com.eshop.productservice.exceptions.ProductNotFoundException;
import com.eshop.productservice.models.Product;

import java.util.List;

public interface ProductService {

    /*
    * 1. get all products
    * 2. get product by id
    * 3. get all products by category id, name
    * 4. save product
    * 5. update product
    * 6. delete product by id
    * */


    List<Product> findAll();

    Product findById(Long id) throws ProductNotFoundException;

    Product save(Product product);

    Product update(Product product);

    void deleteById(Long id);

    List<Product> findAllByCategoryId(Long categoryId);

    List<Product> findAllByCategoryName(String categoryName);



}
