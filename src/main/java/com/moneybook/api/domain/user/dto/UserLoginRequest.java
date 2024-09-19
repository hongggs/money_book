package com.moneybook.api.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginRequest {
    @Schema(description = "아이디", example = "peaceB")
    @NotBlank(message = "계정(아이디)는 필수 입력 값 입니다.")
    private String username;

    @Schema(description = "비밀번호", example = "Password123!!")
    @NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
    @Size(min = 10, message = "비밀번호는 최소 10자 이상이어야 합니다.")
    private String password;
}
