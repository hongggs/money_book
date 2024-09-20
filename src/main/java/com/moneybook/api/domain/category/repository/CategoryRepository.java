package com.moneybook.api.domain.category.repository;

import com.moneybook.api.domain.category.entity.Category;
import com.moneybook.api.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser(User user);

    Optional<Object> findByUserAndName(User user, String name);
}