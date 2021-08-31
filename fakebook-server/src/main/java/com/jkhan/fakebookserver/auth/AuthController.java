package com.jkhan.fakebookserver.auth;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.jkhan.fakebookserver.auth.dto.AuthTokenBundleDto;
import com.jkhan.fakebookserver.auth.dto.AuthTokenDto;
import com.jkhan.fakebookserver.auth.dto.LoginDto;
import com.jkhan.fakebookserver.auth.jwt.JwtProvider;
import com.jkhan.fakebookserver.common.CommonResponseBody;
import com.jkhan.fakebookserver.common.exception.InvalidInputException;
import com.jkhan.fakebookserver.common.constant.ApiResult;
import com.jkhan.fakebookserver.user.UserAccount;
import com.jkhan.fakebookserver.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public CommonResponseBody<AuthTokenBundleDto> login(@RequestBody LoginDto loginInput) {
        UserAccount loginAttemptUserAccount = userService.getUserByEmail(loginInput.getEmail())
                .orElseThrow(() -> new InvalidInputException("User with this mail not found", "입력한 이메일로 가입된 계정이 없습니다."));

        if (!passwordEncoder.matches(loginInput.getPassword(), loginAttemptUserAccount.getPassword())) {
            throw new InvalidInputException("Password is not matched", "비밀번호가 일치하지 않습니다.");
        }

        AuthTokenBundleDto newAccessTokenBundle = authService.issueNewLoginSession(loginAttemptUserAccount);

        return CommonResponseBody.<AuthTokenBundleDto>builder()
                .result(ApiResult.SUCCESS)
                .data(newAccessTokenBundle)
                .build();
    }

    // refresh
    @PostMapping("/refresh")
    public CommonResponseBody<AuthTokenBundleDto> refreshAuth(Authentication authentication) {
        LoginSession targetSession = (LoginSession) authentication.getDetails();
        AuthTokenBundleDto freshTokenBundle = authService.refreshLoginSession(targetSession);

        return CommonResponseBody.<AuthTokenBundleDto>builder()
                .data(freshTokenBundle)
                .result(ApiResult.SUCCESS)
                .build();
    }

    // logout
    @DeleteMapping("/logout")
    public CommonResponseBody<Void> logout(Authentication authentication) {
        LoginSession sessionToDisconnect = (LoginSession) authentication.getDetails();
        authService.deleteLoginSession(sessionToDisconnect);
        return CommonResponseBody.<Void>builder()
                .result(ApiResult.SUCCESS)
                .build();
    }

    @DeleteMapping("/all/logout")
    public CommonResponseBody<Void> logoutAllSession(Authentication authentication) {
        UUID userId = UUID.fromString(String.valueOf(authentication.getPrincipal()));
        authService.deleteAllLoginSessionsOfUser(userId);
        return CommonResponseBody.<Void>builder()
                .result(ApiResult.SUCCESS)
                .build();
    }


}
