package com.moneybook.api.global.auth.service;

import com.moneybook.api.global.auth.util.TokenManager;
import com.moneybook.api.global.error.ErrorCode;
import com.moneybook.api.global.exception.CustomException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenManager tokenManager;

    public void reissueAccessToken(HttpServletRequest request, HttpServletResponse response) {
        //get refresh token
        Cookie[] cookies = request.getCookies();
        String refreshToken = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("refresh")).findFirst().orElseThrow(() -> new CustomException(ErrorCode.REFRESH_TOKEN_NOT_FOUND)).getValue();

        tokenManager.validateToken(refreshToken);

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        if (!tokenManager.isRefreshToken(refreshToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        tokenManager.validateRefreshToken(refreshToken);
        String username = tokenManager.getUsername(refreshToken);
        Long userId = tokenManager.getUserId(refreshToken);
        tokenManager.deleteRefreshToken(refreshToken);
        tokenManager.issueTokens(response, username, userId);
    }
}
