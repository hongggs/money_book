package com.moneybook.api.domain.budget.controller;

import com.moneybook.api.domain.budget.dto.BudgetCreateRequestDto;
import com.moneybook.api.domain.budget.service.BudgetService;
import com.moneybook.api.global.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    /**
     * 예산 생성 또는 수정하는 API
     *
     * @param request
     * @return
     */
    @PostMapping()
    public CommonResponse<Long> createOrUpdateBudget(@RequestBody BudgetCreateRequestDto request) {
        return budgetService.createOrUpdateBudget(request);
    }
}
