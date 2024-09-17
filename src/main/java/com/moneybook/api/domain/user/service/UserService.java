package com.moneybook.api.domain.user.service;

import com.moneybook.api.domain.user.dto.UserSignupRequest;
import com.moneybook.api.domain.user.entity.User;
import com.moneybook.api.domain.user.repository.UserRepository;
import com.moneybook.api.domain.user.util.PasswordValidator;
import com.moneybook.api.global.CommonResponse;
import com.moneybook.api.global.error.ErrorCode;
import com.moneybook.api.global.exception.CustomException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * 회원가입 요청을 처리하는 메서드입니다.
     * 요청을 검증하고, 회원을 생성한 후 데이터베이스에 저장합니다.
     *
     * @param request 회원가입 요청을 담은 DTO 객체
     * @return CommonResponse<String> 성공 메시지 반환
     */
    @Transactional
    public CommonResponse<String> signup(UserSignupRequest request) {
        validateSignupRequest(request);
        String encryptedPassword = passwordEncoder.encode(request.getUsername());
        User user = User.builder()
            .username(request.getUsername())
            .password(encryptedPassword)
            .build();
        userRepository.save(user);
        return CommonResponse.ok("회원가입에 성공했습니다.", null);
    }

    /**
     * 회원가입 요청을 검증하는 메서드입니다.
     * 이메일과 계정 중복을 검사하고, 비밀번호 조건을 확인합니다.
     *
     * @param request 회원가입 요청을 담은 DTO 객체
     */
    private void validateSignupRequest(UserSignupRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());

        // 계정 중복 검사
        if (userOptional.isPresent()) {
            throw new CustomException(ErrorCode.ACCOUNT_ALREADY_EXISTS);
        }

        // 비밀번호 조건 검사
        PasswordValidator.validatePassword(request.getUsername(), request.getPassword());
    }
}
