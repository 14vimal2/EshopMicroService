package com.eshop.productservice.controllers;

import com.eshop.productservice.models.Product;
import com.eshop.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void testProductsSameAsService() {

        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Product 1");
        products.add(product1);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Product 2");
        products.add(product2);

        Product product3 = new Product();
        product3.setId(3L);
        product3.setTitle("Product 3");
        products.add(product3);

        when(
                productService.findAll()
        ).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.getAllProducts("test_token");

//        assertNotNull(response);

        List<Product> productList = response.getBody();

        assertNotNull(productList);

        assertEquals(products.size(), productList.size());

    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getAllCategories() {
    }

    @Test
    void getAllCategoriesByName() {
    }
}