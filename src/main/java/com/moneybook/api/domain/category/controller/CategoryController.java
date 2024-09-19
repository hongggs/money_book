package com.moneybook.api.domain.category.controller;

import com.moneybook.api.domain.category.dto.CategoryCreateRequestDto;
import com.moneybook.api.domain.category.service.CategoryService;
import com.moneybook.api.global.CommonResponse;
import com.moneybook.api.global.auth.domain.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "카테고리 셍성", description = "새로운 카테고리를 생성합니다.")
    @PostMapping
    public ResponseEntity<CommonResponse> createCategory(@AuthenticationPrincipal CustomUserDetails userDetails, @Valid @RequestBody CategoryCreateRequestDto request) {
        CommonResponse response = categoryService.createCategory(userDetails.getUserId(), request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
