package com.moneybook.api.domain.budget.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BudgetCreateRequestDto {

    private Integer amount;

    private Long categoryId;

    private Integer year;

    private Integer month;
}
