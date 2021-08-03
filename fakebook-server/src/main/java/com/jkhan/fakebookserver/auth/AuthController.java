package com.jkhan.fakebookserver.auth;

import com.jkhan.fakebookserver.common.CommonResponseBody;
import com.jkhan.fakebookserver.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    public

}
