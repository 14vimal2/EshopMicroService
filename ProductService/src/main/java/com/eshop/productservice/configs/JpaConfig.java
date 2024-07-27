package com.eshop.productservice.configs;

import com.eshop.productservice.repositories.ProductJpaRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = ProductJpaRepository.class)
public class JpaConfig {
}
