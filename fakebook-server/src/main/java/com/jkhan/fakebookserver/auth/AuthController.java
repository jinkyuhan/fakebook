package com.jkhan.fakebookserver.auth;

import com.jkhan.fakebookserver.common.CommonResponseBody;
import com.jkhan.fakebookserver.common.exception.InvalidInputException;
import com.jkhan.fakebookserver.constant.ApiResult;
import com.jkhan.fakebookserver.dto.SignInDto;
import com.jkhan.fakebookserver.user.UserAccount;
import com.jkhan.fakebookserver.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController()
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService tokenManager;

    @PostMapping("/login")
    public CommonResponseBody<Map<String, AuthTokenDto>> login(@RequestBody SignInDto signInInput) {
        UserAccount loginAttemptUserAccount = userService.getUserByEmail(signInInput.getEmail()).orElseThrow(
                () -> new InvalidInputException("User with this mail not found", "입력한 이메일로 가입된 계정이 없습니다.")
        );
//        tokenManager.createToken(login());


        return CommonResponseBody.<Map<String, AuthTokenDto>>builder()
                .result(ApiResult.SUCCESS)
                .build();
    }

    // logout

    // refresh
    @PostMapping("/refresh")
    public CommonResponseBody<Map<String, AuthTokenDto>> refreshAuth(HttpServletRequest request) {

        return CommonResponseBody.<Map<String, AuthTokenDto>>builder()
                .result(ApiResult.SUCCESS)
                .build();
    }
//    public

}
