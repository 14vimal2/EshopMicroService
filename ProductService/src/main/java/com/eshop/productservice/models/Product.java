package com.eshop.productservice.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;


@Entity
@Getter
@Setter
@Document(indexName = "product")
public class Product extends BaseModel {
    private String title;
    private String description;
    @ManyToOne
    private Category category;
}
