package com.eshop.productservice.services;

import com.eshop.productservice.models.Category;
import com.eshop.productservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategoryByNameIfNotExist(String categoryName) {
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);

        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        }

        Category category = new Category();
        category.setName(categoryName);

        return categoryRepository.save(category);
    }
}
