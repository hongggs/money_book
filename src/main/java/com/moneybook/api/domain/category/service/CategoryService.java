package com.moneybook.api.domain.category.service;

import com.moneybook.api.domain.category.dto.CategoryCreateRequestDto;
import com.moneybook.api.domain.category.dto.CategoryResponseDto;
import com.moneybook.api.domain.category.entity.Category;
import com.moneybook.api.domain.category.repository.CategoryRepository;
import com.moneybook.api.domain.user.entity.User;
import com.moneybook.api.domain.user.repository.UserRepository;
import com.moneybook.api.global.CommonResponse;
import com.moneybook.api.global.error.ErrorCode;
import com.moneybook.api.global.exception.CustomException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    /**
     * 카테고리를 생성하는 메서드 입니다.
     *
     * @param userId 카테고리를 생성한 유저
     * @param request 리뷰 요청을 담은 DTO 객체
     * @return CommonResponse<String> 성공 메시지 반환
     */
    @Transactional
    public CommonResponse<Long> createCategory(Long userId, CategoryCreateRequestDto request) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        categoryRepository.findByUserAndName(user, request.getName()).ifPresent(x -> {
            throw new CustomException(ErrorCode.CATEGORY_NAME_DUPLICATE);
        });

        Category category = Category.builder()
            .name(request.getName())
            .user(user)
            .build();

        Category savedCategory = categoryRepository.save(category);
        return CommonResponse.ok("카테고리를 생성하였습니다.", savedCategory.getId());
    }

    /**
     * 사용자의 전체 카테고리를 반환하는 메서드 입니다.
     *
     * @param userId 요청한 유저
     * @return CommonResponse<List<CategoryResponseDto>> 객체로 사용자의 전체 카테고리 리스트
     */
    @Transactional(readOnly = true)
    public CommonResponse<List<CategoryResponseDto>> getAllCategories(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        List<Category> categories = categoryRepository.findByUser(user);
        List<CategoryResponseDto> response = categories.stream()
            .map(category -> new CategoryResponseDto(category.getId(), category.getName()))
            .toList();
        return CommonResponse.ok("사용자의 전체 카테고리를 반환합니다.", response);
    }

}
