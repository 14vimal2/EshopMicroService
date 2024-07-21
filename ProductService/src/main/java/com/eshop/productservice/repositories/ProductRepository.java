package com.eshop.productservice.repositories;

import com.eshop.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findProductsByCategory_Name(String string);

    List<Product> findProductsByCategory_Id(Long id);

}
