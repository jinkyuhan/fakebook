package com.jkhan.fakebookserver.user;

import com.jkhan.fakebookserver.common.CommonResponseBody;
import com.jkhan.fakebookserver.common.exception.ResourceNotFoundException;
import com.jkhan.fakebookserver.constant.ApiResult;
import com.jkhan.fakebookserver.common.exception.DuplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/mail/duplicate")
    public CommonResponseBody<Map<String, Boolean>> checkIfMailDuplicate(@RequestParam(name = "email") String email) {
        return CommonResponseBody.<Map<String, Boolean>>builder()
                .result(ApiResult.SUCCESS)
                .data(userService.checkIfUserAccountAlreadyExists(() -> userService.getUserByEmail(email)))
                .build();
    }

    @GetMapping("/nickname/duplicate")
    public CommonResponseBody<Map<String, Boolean>> checkIfNicknameDuplicate(@RequestParam(name = "nickname") String nickname) {
        return CommonResponseBody.<Map<String, Boolean>>builder()
                .result(ApiResult.SUCCESS)
                .data(userService.checkIfUserAccountAlreadyExists(() -> userService.getUserByNickname(nickname)))
                .build();
    }

    @PostMapping()
    public CommonResponseBody<Void> signUp(@RequestBody UserCreationDto body) {
        userService.getUserByNicknameOrEmail(body.getNickname(), body.getEmail())
                .ifPresent((u) -> {
                    throw new DuplicationException("Duplication value for signup", "");
                });

        userService.createNewUserAccount(body);
        return CommonResponseBody.<Void>builder()
                .result(ApiResult.SUCCESS)
                .build();
    }

    @GetMapping("/me")
    public CommonResponseBody<UserInfoDto> myInfo(Authentication authentication) {
        String userId = String.valueOf(authentication.getPrincipal());
        UserAccount me = userService.getUserById(userId).orElseThrow(() -> {
            throw new ResourceNotFoundException("요청정보와 일치하는 유저 없음", "정보를 찾을 수 없습니다.");
        });

        return CommonResponseBody.<UserInfoDto>builder()
                .result(ApiResult.SUCCESS)
                .data(UserInfoDto.fromEntity(me))
                .build();
    }
}
