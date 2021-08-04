package com.jkhan.fakebookserver.auth;

import com.jkhan.fakebookserver.common.CommonResponseBody;
import com.jkhan.fakebookserver.constant.ApiResult;
import com.jkhan.fakebookserver.dto.SignInDto;
import com.jkhan.fakebookserver.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        userService.getUserByEmail(signInInput.getEmail())
                .ifPresentOrElse(
                        () -> {},
                        () -> { throw new Exception}
                );

        tokenManager.createToken();


        return CommonResponseBody.<Map<String, AuthTokenDto>>builder()
                .result(ApiResult.SUCCESS)
                .build();
    }

    // logout

    // refresh
    @PostMapping("/refresh")
    public CommonResponseBody<Map<String, AuthTokenDto>> refreshAuth(HttpServeltRequest request) {

        return CommonResponseBody.<Map<String, AuthTokenDto>>builder()
                .result(ApiResult.SUCCESS)
                .build();
    }
//    public

}
