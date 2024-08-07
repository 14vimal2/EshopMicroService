package com.eshop.productservice.controllers;

import com.eshop.productservice.exceptions.ProductNotFoundException;
import com.eshop.productservice.models.Category;
import com.eshop.productservice.models.Product;
import com.eshop.productservice.services.AuthService;
import com.eshop.productservice.services.CategoryService;
import com.eshop.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    private final AuthService authService;




    @Autowired
    public ProductController(
            ProductService productService,
            CategoryService categoryService,
            AuthService authService
    ) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.authService = authService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping()
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize
    ) {
        return ResponseEntity.ok(productService.findAll(pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        product.setId(id);
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/categories/{name}/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<Product>> getAllCategoriesByName(
            @PathVariable("name") String name,
            @PathVariable("pageNumber") int pageNumber,
            @PathVariable("pageSize") int pageSize) {
        return new ResponseEntity<>(productService.findAllByCategoryName(name, pageNumber, pageSize), HttpStatus.OK);
    }

}
