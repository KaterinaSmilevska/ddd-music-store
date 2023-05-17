package com.example.productcatalog.domain.repository;

import com.example.productcatalog.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
