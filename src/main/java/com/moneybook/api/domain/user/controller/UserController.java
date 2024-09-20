package com.moneybook.api.domain.user.controller;

import com.moneybook.api.domain.user.dto.UserLoginRequest;
import com.moneybook.api.domain.user.dto.UserSignupRequest;
import com.moneybook.api.domain.user.service.UserService;
import com.moneybook.api.global.CommonResponse;
import com.moneybook.api.global.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private final AuthService authService;

    @Operation(summary = "회원가입 요청", description = "사용자가 계정(아이디), 비밀번호를 제공하여 회원가입을 요청합니다.")
    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<String>> signup(@Valid @RequestBody UserSignupRequest request) {
        CommonResponse<String> response = userService.signup(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
//
//    @Operation(summary = "로그인", description = "사용자가 아이디, 비밀번호 제공하여 로그인을 요청합니다.")
//    @PostMapping("/login")
//    public ResponseEntity<CommonResponse<?>> login(@Valid @RequestBody UserLoginRequest request){
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    /**
     * Access Token 재발급, Refresh Token 갱신
     *ㅌㅌ
     * @return 200 Authorization Header AccessToken, Cookie RefreshToken 갱신
     * @throws CustomException cookie에 Refresh Token이 존재하지 않는 경우
     */
    @Operation(summary = "Access Token 재발급, Refresh Token 갱신", description = "기존 Refresh Token으로 Access Token 재발급하고 Authorization Header에 반환합니다. Refresh Token또한 갱신하여 Cookie에 저장됩니다.")
    @PostMapping("/reissue")
    public ResponseEntity<CommonResponse<?>> reissueAccessToken(HttpServletRequest request, HttpServletResponse response) {
        authService.reissueAccessToken(request, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
