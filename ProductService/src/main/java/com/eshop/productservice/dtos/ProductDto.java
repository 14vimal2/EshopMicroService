package com.eshop.productservice.dtos;

import com.eshop.productservice.models.Category;
import com.eshop.productservice.models.Product;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

    public Product getProduct() {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        Category categoryObj = new Category();
        categoryObj.setName(category);
        product.setCategory(categoryObj);
        product.setDescription(description);
        return product;
    }

}
