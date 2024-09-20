package com.moneybook.api.domain.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateRequestDto {

    @Schema(description = "카테고리 이름", example = "외식", required = true)
    @NotBlank(message = "이름은 공백일 수 없습니다.")
    private String name;
}
