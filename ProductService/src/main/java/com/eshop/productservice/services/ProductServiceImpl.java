package com.eshop.productservice.services;

import com.eshop.productservice.exceptions.ProductNotFoundException;
import com.eshop.productservice.models.Product;
import com.eshop.productservice.repositories.ProductElasticRepository;
import com.eshop.productservice.repositories.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductJpaRepository productJpaRepository;
    private final ProductElasticRepository productElasticRepository;

    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(
            ProductJpaRepository productJpaRepository,
            ProductElasticRepository productElasticRepository,
            CategoryService categoryService) {
        this.productJpaRepository = productJpaRepository;
        this.productElasticRepository = productElasticRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Page<Product> findAll(int pageNumber, int pageSize) {
//        return productRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return productElasticRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Product findById(Long id) throws ProductNotFoundException {

//        Optional<Product> product = productRepository.findById(id);
        Optional<Product> productOptional = productElasticRepository.findById(id);

        if (productOptional.isPresent()) {
            return productOptional.get();
        }

        throw new ProductNotFoundException(id);
    }

    @Override
    public Product save(Product product) {
        // check if category exist or not
        if(product.getCategory().getId() == null) {
            product.setCategory( categoryService.createCategoryByNameIfNotExist( product.getCategory().getName() ) );
        }
        Product savedProduct = productJpaRepository.save(product);

        productElasticRepository.save(savedProduct);
        return savedProduct;
    }

    @Override
    public Product update(Product product) {
        product = productJpaRepository.save(product);
        productElasticRepository.save(product);
        return product;
    }

    @Override
    public void deleteById(Long id)
    {
//        productRepository.deleteById(id);
        productJpaRepository.deleteById(id);
        productElasticRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAllByCategoryId(Long categoryId, int pageNumber, int pageSize) {
        return productElasticRepository.findProductsByCategory_Id(categoryId, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Product> findAllByCategoryName(String categoryName, int pageNumber, int pageSize) {
        return productElasticRepository.findProductsByCategory_Name(categoryName, PageRequest.of(pageNumber, pageSize));
    }
}
