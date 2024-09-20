package com.moneybook.api.domain.category.repository;

import com.moneybook.api.domain.category.entity.Category;
import com.moneybook.api.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser(User user);
}