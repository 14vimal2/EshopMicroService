package com.eshop.productservice.services;

import com.eshop.productservice.dtos.ProductDto;
import com.eshop.productservice.exceptions.ProductNotFoundException;
import com.eshop.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//@Service
public class FakeStoreProductService {
//    RestTemplate restTemplate;
//
//    RedisTemplate<String, Object> redisTemplate;

//    @Autowired
//    public FakeStoreProductService(
//            RestTemplate restTemplate,
//            RedisTemplate<String, Object> redisTemplate
//    ) {
//        this.restTemplate = restTemplate;
//        this.redisTemplate = redisTemplate;
//    }

//    @Override
//    public List<Product> findAll() {
//
//
//
//        ProductDto[] productDtos = restTemplate.getForObject(
//                "https://fakestoreapi.com/products",
//                ProductDto[].class
//        );
//
//        List<Product> products = new ArrayList<>();
//        assert productDtos != null;
//        for (ProductDto productDto : productDtos) {
//            products.add(productDto.getProduct());
//        }
//        return products;
//    }
//
//    @Override
//    public Product findById(Long id) throws ProductNotFoundException {
//
//        Product redisProduct = (Product) redisTemplate.opsForHash().get("PRODUCTS" ,"PRODUCT_" + id);
//
//        if (redisProduct != null) {
//            return redisProduct;
//        }
//
//        ProductDto productDto = restTemplate.getForObject(
//                "https://fakestoreapi.com/products/" + id,
//                ProductDto.class
//        );
//
//        if (productDto == null) {
//            throw new ProductNotFoundException(id);
//        }
//
//        Product product = productDto.getProduct();
//        redisTemplate.opsForHash().put("PRODUCTS" ,"PRODUCT_" + id, product);
//        return product;
//
//
//    }
//
//    @Override
//    public Product save(Product product) {
//        return null;
//    }
//
//    @Override
//    public Product update(Product product) {
//        return null;
//    }
//
//    @Override
//    public void deleteById(Long id) {
//
//    }
//
//    @Override
//    public List<Product> findAllByCategoryId(Long categoryId, ) {
//        return List.of();
//    }
//
//    @Override
//    public List<Product> findAllByCategoryName(String categoryName) {
//        return List.of();
//    }
}
