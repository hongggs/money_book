package com.moneybook.api.domain.budget.repository;

import com.moneybook.api.domain.budget.entity.Budget;
import com.moneybook.api.domain.category.entity.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findAllByCategory(Category category);
    Optional<Budget> findByCategoryAndYearAndMonth(Category category, Integer year, Integer month);
}