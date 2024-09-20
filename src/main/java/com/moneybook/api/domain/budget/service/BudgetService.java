package com.moneybook.api.domain.budget.service;

import com.moneybook.api.domain.budget.dto.BudgetCreateRequestDto;
import com.moneybook.api.domain.budget.entity.Budget;
import com.moneybook.api.domain.budget.repository.BudgetRepository;
import com.moneybook.api.domain.category.entity.Category;
import com.moneybook.api.domain.category.repository.CategoryRepository;
import com.moneybook.api.global.CommonResponse;
import com.moneybook.api.global.error.ErrorCode;
import com.moneybook.api.global.exception.CustomException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 요청한 연도, 월, 카테고리에 설정된 예산을 생성하거나 수정하는 메서드
     *
     * @param request 연도, 월, 카테고리, 예산을 포함한 요청
     * @return CommonResponse<Long> 생성되거나 수정된 예산 아이디와 메세지 리턴
     */
    @Transactional
    public CommonResponse<Long> createOrUpdateBudget(BudgetCreateRequestDto request) {
        Category category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));

        // 카테고리, 연도, 월로 예산이 이미 존재하는지 확인
        Budget budget = budgetRepository.findByCategoryAndYearAndMonth(category, request.getYear(), request.getMonth())
            .map(existingBudget -> updateExistingBudget(existingBudget, request.getAmount()))
            .orElseGet(() -> createNewBudget(category, request));

        Budget savedBudget = budgetRepository.save(budget);
        return CommonResponse.ok("예산이 설정되었습니다.", savedBudget.getId());
    }

    // 기존 예산 수정
    private Budget updateExistingBudget(Budget budget, Integer amount) {
        budget.setAmount(amount);  // 필요 시 setter 메서드 추가
        return budget;
    }

    // 새 예산 생성
    private Budget createNewBudget(Category category, BudgetCreateRequestDto request) {
        return Budget.builder()
            .amount(request.getAmount())
            .year(request.getYear())
            .month(request.getMonth())
            .createdAt(LocalDateTime.now())
            .category(category)
            .build();
    }
}
