package com.moneybook.api.domain.user.controller;

import com.moneybook.api.domain.user.dto.UserSignupRequest;
import com.moneybook.api.domain.user.service.UserService;
import com.moneybook.api.global.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입 요청", description = "사용자가 계정(아이디), 비밀번호를 제공하여 회원가입을 요청합니다.")
    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<String>> signup(@Valid @RequestBody UserSignupRequest request) {
        CommonResponse<String> response = userService.signup(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
