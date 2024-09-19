package com.moneybook.api.domain.category.repository;

import com.moneybook.api.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}