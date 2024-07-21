package com.eshop.productservice.services;

import com.eshop.productservice.exceptions.ProductNotFoundException;
import com.eshop.productservice.models.Product;
import com.eshop.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) throws ProductNotFoundException {

        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        }

        throw new ProductNotFoundException(id);
    }

    @Override
    public Product save(Product product) {
        // check if category exist or not
        if(product.getCategory().getId() == null) {
            product.setCategory( categoryService.createCategoryByNameIfNotExist( product.getCategory().getName() ) );
        }
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllByCategoryId(Long categoryId) {
        return productRepository.findProductsByCategory_Id(categoryId);
    }

    @Override
    public List<Product> findAllByCategoryName(String categoryName) {
        return productRepository.findProductsByCategory_Name(categoryName);
    }
}
