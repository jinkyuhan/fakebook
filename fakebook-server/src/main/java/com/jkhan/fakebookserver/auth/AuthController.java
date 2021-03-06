package com.jkhan.fakebookserver.auth;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jkhan.fakebookserver.common.CommonResponseBody;
import com.jkhan.fakebookserver.common.exception.InvalidInputException;
import com.jkhan.fakebookserver.constant.ApiResult;
import com.jkhan.fakebookserver.user.UserAccount;
import com.jkhan.fakebookserver.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public CommonResponseBody<AuthTokenBundleDto> login(@RequestBody LoginDto loginInput) {
        UserAccount loginAttemptUserAccount = userService.getUserByEmail(loginInput.getEmail())
                .orElseThrow(() -> new InvalidInputException("User with this mail not found", "입력한 이메일로 가입된 계정이 없습니다."));

        if (!loginAttemptUserAccount.validatePassword(loginInput.getPassword())) {
            throw new InvalidInputException("Password is not matched", "비밀번호가 일치하지 않습니다.");
        }

        AuthTokenBundleDto newAccessTokenBundle = jwtProvider.issueNewLoginSession(loginAttemptUserAccount);

        return CommonResponseBody.<AuthTokenBundleDto>builder()
                .result(ApiResult.SUCCESS)
                .data(newAccessTokenBundle)
                .build();
    }

    // logout
//  @DeleteMapping("/logout")
//  public CommonResponseBody<Void> logout(Authentication authentication) {
//
//  }

    // refresh
    @PostMapping("/refresh")
    public CommonResponseBody<Map<String, AuthTokenDto>> refreshAuth(Authentication authentication) {
        return CommonResponseBody.<Map<String, AuthTokenDto>>builder().result(ApiResult.SUCCESS).build();
    }

}
