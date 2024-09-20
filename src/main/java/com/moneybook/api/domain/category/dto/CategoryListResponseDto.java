package com.moneybook.api.domain.category.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListResponseDto {
    private List<CategoryResponseDto> categories;
}
