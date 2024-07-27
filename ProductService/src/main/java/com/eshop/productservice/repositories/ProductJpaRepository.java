package com.eshop.productservice.repositories;

import com.eshop.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    Page<Product> findAll(Pageable pageable);

    Page<Product> findProductsByCategory_Name(String string, Pageable pageable);

    Page<Product> findProductsByCategory_Id(Long id, Pageable pageable);

}
