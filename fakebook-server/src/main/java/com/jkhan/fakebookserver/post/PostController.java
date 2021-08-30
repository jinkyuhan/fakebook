package com.jkhan.fakebookserver.post;

import com.jkhan.fakebookserver.common.CommonResponseBody;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/api/posts")
public class PostController {

    private PostService s

    @GetMapping("/mine")
    public CommonResponseBody<PostListDto> getMyPosts(Authentication authentication) {
        UUID userId = UUID.fromString(String.valueOf(authentication.getPrincipal()));

    }


}
