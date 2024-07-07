package com.pickple.server.api.user.controller;

import com.pickple.server.api.user.dto.AccessTokenGetSuccess;
import com.pickple.server.api.user.dto.LoginSuccessResponse;
import com.pickple.server.api.user.service.UserService;
import com.pickple.server.global.auth.client.dto.UserLoginRequest;
import com.pickple.server.global.auth.jwt.service.TokenService;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.SuccessCode;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserControllerDocs {

    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping("api/v1/user/login")
    @Override
    public ApiResponseDto<LoginSuccessResponse> login(
            @RequestParam final String authorizationCode,
            @RequestBody final UserLoginRequest loginRequest
    ) {
        return ApiResponseDto.success(SuccessCode.LOGIN_SUCCESS, userService.create(authorizationCode, loginRequest));
    }

    @GetMapping("api/v1/user/token-refresh")
    @Override
    public ApiResponseDto<AccessTokenGetSuccess> refreshToken(
            @RequestParam final String refreshToken
    ) {
        return ApiResponseDto.success(SuccessCode.ISSUE_ACCESS_TOKEN_SUCCESS, userService.refreshToken(refreshToken));
    }

    @DeleteMapping("api/v1/user/delete")
    @Override
    public ApiResponseDto deleteUser(
            final Principal principal
    ) {
        tokenService.deleteRefreshToken(Long.valueOf(principal.getName()));
        userService.deleteUser(Long.valueOf(principal.getName()));
        return ApiResponseDto.success(SuccessCode.USER_DELETE_SUCCESS);
    }

    @PostMapping("api/v1/user/logout")
    @Override
    public ApiResponseDto logout(
            final Principal principal
    ) {
        tokenService.deleteRefreshToken(Long.valueOf(principal.getName()));
        return ApiResponseDto.success(SuccessCode.LOGOUT_SUCCESS);
    }
}

