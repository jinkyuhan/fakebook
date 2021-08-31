package com.jkhan.fakebookserver.post;

import com.jkhan.fakebookserver.common.CommonResponseBody;
import com.jkhan.fakebookserver.common.constant.ApiResult;
import com.jkhan.fakebookserver.common.exception.InvalidInputException;
import com.jkhan.fakebookserver.post.dto.NewPostDto;
import com.jkhan.fakebookserver.post.dto.PostListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/mine")
    public CommonResponseBody<PostListDto> getMyPosts(
            Authentication authentication,
            @RequestParam(name = "idCursor") String idCursor,
            @RequestParam(name = "timeCursor") Long timeCursor,
            @RequestParam(name = "limit") Integer limit
    ) {
        UUID userId = UUID.fromString(String.valueOf(authentication.getPrincipal()));
        PostListDto responseData = new PostListDto();
        if (limit == null) {
            responseData.setPosts(postService.getAllPostsOfUser(userId));
        } else {
            if (idCursor == null && timeCursor == null) {
                // only limit

            } else if (idCursor != null && timeCursor != null) {

            } else {
                throw new InvalidInputException("Request Param[idCursor, timeCursor] is required", "");
            }
        }


        return CommonResponseBody.<PostListDto>builder()
                .data(responseData)
                .result(ApiResult.SUCCESS)
                .build();
    }

    @PostMapping()
    public CommonResponseBody<Void> addMyPost(@RequestBody NewPostDto newPost, Authentication authentication) {
        UUID userId = UUID.fromString(String.valueOf(authentication.getPrincipal()));
        postService.addNewPost(newPost, userId);
        return CommonResponseBody.<Void>builder()
                .result(ApiResult.SUCCESS)
                .build();
    }
}
