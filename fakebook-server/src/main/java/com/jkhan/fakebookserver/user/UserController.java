package com.jkhan.fakebookserver.user;

import com.jkhan.fakebookserver.common.CommonResponseBody;
import com.jkhan.fakebookserver.constant.ApiResult;
import com.jkhan.fakebookserver.dto.UserCreationDto;
import com.jkhan.fakebookserver.common.exception.DuplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<CommonResponseBody> signUp(@RequestBody UserCreationDto body) {
        userService.getUserByNicknameOrEmail("nickname", "asdf")
                .ifPresent(
                        (u) -> {
                            throw new DuplicationException();
                        }
                );
        userService.createNewUserAccount(body);

        return ResponseEntity.ok(
                CommonResponseBody.builder()
                        .result(ApiResult.SUCCESS)
                        .build()
        );
    }

}
