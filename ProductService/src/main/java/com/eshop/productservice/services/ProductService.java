package com.eshop.productservice.services;


import com.eshop.productservice.exceptions.ProductNotFoundException;
import com.eshop.productservice.models.Product;
import org.springframework.data.domain.Page;

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


    Page<Product> findAll(int pageNumber, int pageSize);

    Product findById(Long id) throws ProductNotFoundException;

    Product save(Product product);

    Product update(Product product);

    void deleteById(Long id);

    Page<Product> findAllByCategoryId(Long categoryId, int pageNumber, int pageSize);

    Page<Product> findAllByCategoryName(String categoryName, int pageNumber, int pageSize);



}
