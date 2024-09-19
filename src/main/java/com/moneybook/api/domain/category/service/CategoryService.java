package com.moneybook.api.domain.category.service;

import com.moneybook.api.domain.category.dto.CategoryCreateRequestDto;
import com.moneybook.api.domain.category.entity.Category;
import com.moneybook.api.domain.category.repository.CategoryRepository;
import com.moneybook.api.domain.user.entity.User;
import com.moneybook.api.domain.user.repository.UserRepository;
import com.moneybook.api.domain.user.service.UserService;
import com.moneybook.api.global.CommonResponse;
import com.moneybook.api.global.error.ErrorCode;
import com.moneybook.api.global.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        Category category = Category.builder()
            .name(request.getName())
            .user(user)
            .build();

        Category savedCategory = categoryRepository.save(category);
        return CommonResponse.ok("카테고리를 생성하였습니다.", savedCategory.getId());
    }
}
